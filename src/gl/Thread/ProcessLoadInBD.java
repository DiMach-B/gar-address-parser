package gl.Thread;

import gl.BD.DatabaseHandler;
import gl.Tables.DownloadProcessTable;
import gl.TablesGar.*;
import gl.utils.GarSaxParserSetHandler;
import gl.utils.OutputInformation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ProcessLoadInBD {


    public static Task loadThread(String pathname, Button loadDirectoryButton, TableView viewLoadFile, TableColumn FileName, TableColumn Inserting
            , TableColumn Region, TableColumn Updating, TableColumn Errors, TableView viewFile, TableColumn Status, TableColumn Date, Label info
    ) {
        Task<Integer> loadThreadTask = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                try {

                    readFile(pathname, viewLoadFile, FileName, Inserting
                            , Region, Updating, Errors, viewFile, Status, Date);

                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        info.setText("Индексация...");
//                    }
//                });
//
//                DatabaseHandler databaseHandler = new DatabaseHandler();
//
//                try {
//                    databaseHandler.indexTable();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        loadDirectoryButton.setDisable(false);
                        info.setText("Файл загружен!");
                    }
                });
                return 0;
            }
        };
        return loadThreadTask;
    }

    public static void parse_FTS(AbstractGarTableStructure fts, String FileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GarSaxParserSetHandler GarHandler = new GarSaxParserSetHandler(FileName);
            GarHandler.fts = fts;
            saxParser.parse(new File(FileName), GarHandler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String pathname, TableView viewLoadFile, TableColumn FileName, TableColumn Inserting
            , TableColumn Region, TableColumn Updating, TableColumn Errors, TableView viewFile, TableColumn Status, TableColumn Date) throws SQLException, ParseException {

        ObservableList<DownloadProcessTable> list = FXCollections.observableArrayList();

        File dirGlobal = new File(pathname);
        for (File itemGlobal : dirGlobal.listFiles()) {
            File dir = new File(pathname + "\\" + itemGlobal.getName());
            if (itemGlobal.isDirectory()) {
                for (File item : dir.listFiles()) {
                    if (item.getName().contains("ADDR_OBJ_2")) {
                        parse_FTS(new AS_ADDR_OBJ(), pathname + "\\" + itemGlobal.getName() + "\\" + item.getName());

                        ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                                , OutputInformation.Updating, OutputInformation.Errors
                                , viewLoadFile, FileName, Inserting
                                , Region, Updating, Errors
                        );

                    } else if (item.getName().contains("ADM_HIERARCHY_2")) {
                        parse_FTS(new AS_ADM_HIERARCHY(), pathname + "\\" + itemGlobal.getName() + "\\" + item.getName());

                        ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                                , OutputInformation.Updating, OutputInformation.Errors
                                , viewLoadFile, FileName, Inserting
                                , Region, Updating, Errors
                        );

                    } else if (item.getName().contains("APARTMENTS_2")) {
                        parse_FTS(new AS_APARTMENTS(), pathname + "\\" + itemGlobal.getName() + "\\" + item.getName());

                        ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                                , OutputInformation.Updating, OutputInformation.Errors
                                , viewLoadFile, FileName, Inserting
                                , Region, Updating, Errors
                        );

                    }
//                    else if (item.getName().contains("AS_APARTMENTS_PARAMS_2")) {
//                        parse_FTS(new AS_PARAM(), pathname + "\\" + itemGlobal.getName() + "\\" + item.getName());
//
//                        ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
//                                , OutputInformation.Updating, OutputInformation.Errors
//                                , viewLoadFile, FileName, Inserting
//                                , Region, Updating, Errors
//                        );
//
//                    }
                    else if (item.getName().contains("HOUSES_2")) {
                        parse_FTS(new AS_HOUSES(), pathname + "\\" + itemGlobal.getName() + "\\" + item.getName());

                        ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                                , OutputInformation.Updating, OutputInformation.Errors
                                , viewLoadFile, FileName, Inserting
                                , Region, Updating, Errors
                        );

                    } else if (item.getName().contains("AS_HOUSES_PARAMS_2")) {
                        parse_FTS(new AS_PARAM(), pathname + "\\" + itemGlobal.getName() + "\\" + item.getName());

                        ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                                , OutputInformation.Updating, OutputInformation.Errors
                                , viewLoadFile, FileName, Inserting
                                , Region, Updating, Errors
                        );
                    }
                }
            }

            if (itemGlobal.isFile()) {
                if (itemGlobal.getName().contains("ADDR_OBJ_TYPES_2")) {
                    parse_FTS(new AS_ADDR_OBJ_TYPES(), pathname + "\\" + itemGlobal.getName());

                    ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                            , OutputInformation.Updating, OutputInformation.Errors
                            , viewLoadFile, FileName, Inserting
                            , Region, Updating, Errors
                    );

                } else if (itemGlobal.getName().contains("APARTMENT_TYPES_2")) {
                    parse_FTS(new AS_APARTMENT_TYPES(), pathname + "\\" + itemGlobal.getName());

                    ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                            , OutputInformation.Updating, OutputInformation.Errors
                            , viewLoadFile, FileName, Inserting
                            , Region, Updating, Errors
                    );

                } else if (itemGlobal.getName().contains("ADDHOUSE_TYPES_2")) {
                    parse_FTS(new AS_ADDHOUSE_TYPES(), pathname + "\\" + itemGlobal.getName());

                    ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                            , OutputInformation.Updating, OutputInformation.Errors
                            , viewLoadFile, FileName, Inserting
                            , Region, Updating, Errors
                    );

                } else if (itemGlobal.getName().contains("HOUSE_TYPES_2")) {
                    parse_FTS(new AS_HOUSE_TYPES(), pathname + "\\" + itemGlobal.getName());

                    ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                            , OutputInformation.Updating, OutputInformation.Errors
                            , viewLoadFile, FileName, Inserting
                            , Region, Updating, Errors
                    );

                } else if (itemGlobal.getName().contains("PARAM_TYPES_2")) {
                    parse_FTS(new AS_PARAM_TYPES(), pathname + "\\" + itemGlobal.getName());

                    ViewResult(list, OutputInformation.FileName, OutputInformation.Region, OutputInformation.Inserting
                            , OutputInformation.Updating, OutputInformation.Errors
                            , viewLoadFile, FileName, Inserting
                            , Region, Updating, Errors
                    );

                }
            }
        }
        ViewResult(list, "Итог", "-", OutputInformation.TotleInserting
                , OutputInformation.TotleUpdating, OutputInformation.TotleErrors
                , viewLoadFile, FileName, Inserting
                , Region, Updating, Errors
        );
        OutputInformation.TotleInserting = 0;
        OutputInformation.TotleUpdating = 0;
        OutputInformation.TotleErrors = 0;
        fixingTheDate(dirGlobal.getName(), viewFile, Status, Date);
    }

    static public void ViewResult(ObservableList<DownloadProcessTable> list, String FileName, String Region, Integer Inserting
            , Integer Updating, Integer Errors, TableView viewLoadFile, TableColumn FileNameTC, TableColumn InsertingTC
            , TableColumn RegionTC, TableColumn UpdatingTC, TableColumn ErrorsTC) {

        DownloadProcessTable downloadProcess = new DownloadProcessTable(FileName
                , Region, Inserting
                , Updating, Errors);
        list.add(downloadProcess);

        FileNameTC.setCellValueFactory(new PropertyValueFactory<DownloadProcessTable, String>("FileName"));
        RegionTC.setCellValueFactory(new PropertyValueFactory<DownloadProcessTable, Integer>("Region"));
        InsertingTC.setCellValueFactory(new PropertyValueFactory<DownloadProcessTable, Integer>("Inserting"));
        UpdatingTC.setCellValueFactory(new PropertyValueFactory<DownloadProcessTable, Integer>("Updating"));
        ErrorsTC.setCellValueFactory(new PropertyValueFactory<DownloadProcessTable, Integer>("Errors"));
        viewLoadFile.setItems(list);
    }

    public static void fixingTheDate(String paskage, TableView viewFile, TableColumn Status, TableColumn Date) throws SQLException, ParseException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        if (OutputInformation.Errors == 0) {
            databaseHandler.insertHistory(paskage, "Успешно");
        } else {
            databaseHandler.insertHistory(paskage, "С ошибками");
        }
        OutputInformation.uploadedFiles(viewFile, Status, Date);
    }
}