package gl.Thread;

import gl.BD.DatabaseHandler;
import gl.BD.GarHistory;
import gl.utils.OutputInformation;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import net.lingala.zip4j.*;
import net.lingala.zip4j.exception.ZipException;

public class ProcessLoadFromFIAS {

    public static Task loadThreadURL(TextArea textArea, Button loadArchivesButton, Label inform) {
        Task<Integer> loadThreadTaskURL = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                try {

                    loadArchives(textArea);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        inform.setText("Индексация данных...");
                        inform.setText("Файлы загружены!");
                        loadArchivesButton.setDisable(false);
                    }
                });

                return 0;
            }
        };
        return loadThreadTaskURL;
    }

    private static void loadArchives(TextArea textArea) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Boolean checkHistory = false;
        Boolean checkReester = false;
        String text = "";

        try {

            URL url = new URL("https://fias.nalog.ru/WebServices/Public/GetAllDownloadFileInfo");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JSONParser parse = new JSONParser();
                JSONArray arr = (JSONArray) parse.parse(inline);

                for (int i = 0; i < arr.size(); i++) {

                    JSONObject objArr = (JSONObject) arr.get(i);

                    ResultSet resultSetHistory = null;
                    ResultSet resultSetReester = null;
                    try {
                        resultSetHistory = databaseHandler.readUploadeFiles();
                        resultSetReester = databaseHandler.readReestrFiles();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    while (resultSetHistory.next()) {

                        if (resultSetHistory.getInt("ID") == objArr.get("VersionId").hashCode()) {

                            checkHistory = true;
                        }
                    }

                    while (resultSetReester.next()) {

                        if (resultSetReester.getInt("VersionId") == objArr.get("VersionId").hashCode()) {

                            checkReester = true;
                        }
                    }

                    if (!checkHistory) {
                        if (!objArr.get("GarXMLDeltaURL").toString().isEmpty()) {
                            text += "Загружаем архив от " + objArr.get("Date") + "\n";
                            textArea.setText(text);
                            //Класс URLпредставляет собой Единый локатор ресурсов, указатель на "ресурс" во Всемирной паутине.
                            URL website = new URL(objArr.get("GarXMLDeltaURL").toString());
                            //Создает канал, который читает байты из данного потока.
                            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                            FileOutputStream outputStream = new FileOutputStream(OutputInformation.downloadPath + objArr.get("Date").toString() + ".zip");

                            try {
                                // Передает байты из файла этого канала в указанный доступный для записи байтовый канал.
                                outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                                rbc.close();
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            text += "Разархивация...\n";
                            textArea.setText(text);
                            try {
                                ZipFile zipFile = new ZipFile(OutputInformation.downloadPath + objArr.get("Date").toString() + ".zip");
                                zipFile.extractAll(OutputInformation.downloadPath + objArr.get("Date").toString());
                            } catch (ZipException e) {
                                System.out.println("Файл был разархивирован с ошибкой:\n" + e.getMessage() + "\n");
                                e.printStackTrace();
                            }
                        }
                    }

                    if (!checkReester) {

                        databaseHandler.insertReester(objArr);
                    }

                    checkHistory = false;
                    checkReester = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        text += "Загрузка окончена!\n" +
                "Файлы расположены в папке \"Загрузки\"";
        textArea.setText(text);
    }
}
