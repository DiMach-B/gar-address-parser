package gl.utils;

import gl.BD.DatabaseHandler;
import gl.Tables.DownloadHistoryGarTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OutputInformation {

    public static String FileName;
    public static String Region;
    public static Integer Inserting;
    public static Integer Updating;
    public static Integer Errors;
    public static Integer TotleInserting = 0;
    public static Integer TotleUpdating = 0;
    public static Integer TotleErrors = 0;
    public static Boolean Flag = false;
    public static String downloadPath = System.getProperty("user.home") + "\\Downloads\\";

    public static void uploadedFiles(TableView viewFile, TableColumn Status, TableColumn Date) throws SQLException {

        ObservableList<DownloadHistoryGarTable> list = FXCollections.observableArrayList();

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.readUploadeFiles();

        while (result.next()) {

            DownloadHistoryGarTable uploadFile = new DownloadHistoryGarTable(
                    new SimpleDateFormat("dd.MM.yyyy")
                            .format(result.getDate("UPLOADDATE"))
                    , result.getString("STATUS"));
            list.add(uploadFile);

        }
        Date.setCellValueFactory(new PropertyValueFactory<DownloadHistoryGarTable, Date>("Uploaddate"));
        Status.setCellValueFactory(new PropertyValueFactory<DownloadHistoryGarTable, String>("Status"));
        viewFile.setItems(list);
    }

}


