package gl.ControllerFXML;

import gl.BD.DatabaseHandler;
import gl.BD.GarHistory;
import gl.Thread.ProcessLoadFromFIAS;
import gl.Thread.ProcessLoadInBD;
import gl.Tables.DownloadHistoryGarTable;
import gl.Tables.DownloadProcessTable;
import gl.utils.OutputInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class MainWindowController {

    @FXML
    private TableColumn<DownloadProcessTable, String> FileName;

    @FXML
    private TableColumn<DownloadProcessTable, Integer> Inserting;

    @FXML
    private TableColumn<DownloadProcessTable, String> Region;

    @FXML
    private TableColumn<DownloadProcessTable, Integer> Updating;

    @FXML
    private TableColumn<DownloadProcessTable, Integer> Errors;

    @FXML
    private TableColumn<DownloadHistoryGarTable, java.sql.Date> Date;

    @FXML
    private TableColumn<DownloadHistoryGarTable, String> Status;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button loadArchivesButton;

    @FXML
    private Button loadDirectoryButton;

    @FXML
    private TextArea loadingArchives;

    @FXML
    private Label showPathNameLabel;

    @FXML
    private TableView<DownloadHistoryGarTable> viewFile;

    @FXML
    private TableView<DownloadProcessTable> viewLoadFile;

    @FXML
    private Label info;

    @FXML
    private Label inform;

    @FXML
    void initialize() throws SQLException {

        DirectoryChooser directoryChooser = new DirectoryChooser();

        AtomicReference<String> GarPathName = new AtomicReference<>("");
        AtomicReference<String> GarDirectoryName = new AtomicReference<>("");
        AtomicReference<File> f = new AtomicReference<>();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Удалить");
        contextMenu.getItems().add(menuItem);
        OutputInformation.uploadedFiles( viewFile, Status, Date);

        viewFile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY){
                    contextMenu.show(viewFile, event.getScreenX(), event.getScreenY());
                    DownloadHistoryGarTable hg = viewFile.getSelectionModel().getSelectedItem();

                    menuItem.setOnAction(e -> {
                        System.out.println("Удаление");

                        try {
                            databaseHandler.daleteHistory(hg.getUploaddate());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }

                        try {
                            OutputInformation.uploadedFiles( viewFile, Status, Date);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                }
            }
        });

        chooseFileButton.setOnAction(event -> {

            directoryChooser.setInitialDirectory(new File(OutputInformation.downloadPath));
            f.set(directoryChooser.showDialog(null));

            if (f.get() != null) {
                showPathNameLabel.setText(f.get().getAbsolutePath());
                GarPathName.set(f.get().getAbsolutePath());
                GarDirectoryName.set(f.get().getName());
            }
        });

        loadDirectoryButton.setOnAction(event -> {

            try {
                if (f.get() != null) {
                if (!checkUploadFiles(f.get().getName())) {

                    loadDirectoryButton.setDisable(true);
                    info.setText("Загрузка...");

                    final Task LoadBDTask = ProcessLoadInBD
                            .loadThread(new File(showPathNameLabel.getText()).getAbsolutePath()
                                    , loadDirectoryButton
                                    , viewLoadFile
                                    , FileName
                                    , Inserting
                                    , Region
                                    , Updating
                                    , Errors
                                    , viewFile
                                    , Status
                                    , Date
                                    , info
                            );

                    Thread thread = new Thread(LoadBDTask);
                    thread.setDaemon(true);
                    thread.start();

                } else {
                    info.setText("Файл уже загружен!");
                }
                } else {
                    info.setText("Выберете папку!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        loadArchivesButton.setOnAction(event -> {

            loadArchivesButton.setDisable(true);
            inform.setText("Загрузка...");
            final Task LoadFIASTask = ProcessLoadFromFIAS
                    .loadThreadURL(loadingArchives, loadArchivesButton, inform);

            Thread thread = new Thread(LoadFIASTask);
            thread.setDaemon(true);
            thread.start();
        });

    }
    public boolean checkUploadFiles (String fileName) throws SQLException, ParseException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSetHistory = null;
        Boolean checkHistory = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date d = sdf.parse(fileName);
        sdf.applyPattern("yyyy.MM.dd");

        try {
            resultSetHistory = databaseHandler.readUploadeFiles();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (resultSetHistory.next()) {

            if (resultSetHistory.getInt("ID") == Integer.parseInt( sdf.format(d).replaceAll("\\.", "") )) {

                checkHistory = true;
            }
        }

        return checkHistory;
    }
}
