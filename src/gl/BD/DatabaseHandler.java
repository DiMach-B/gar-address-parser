package gl.BD;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.driver.OracleConnection;
import org.json.simple.JSONObject;

import java.sql.*;
import java.text.ParseException;

public class DatabaseHandler {

    OracleConnection dbConnection = null;

    public OracleConnection getDbConnection() {
        String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=db2.miit.ru)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=ctest.miit.ru)))";
        String dbuser = "fias2";
        String dbpassword = "dfg336yfh";


        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnection = (OracleConnection) DriverManager.getConnection(dburl, dbuser, dbpassword);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    public ResultSet readUploadeFiles() throws SQLException {

        ResultSet resultSet = null;
        GarHistory garHistory = new GarHistory();
        OraclePreparedStatement dbpsSelectHistory;


        try {
            dbpsSelectHistory = (OraclePreparedStatement) getDbConnection().prepareStatement(garHistory.getSqlSelectHistory());
            resultSet = dbpsSelectHistory.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getDbConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet readReestrFiles() throws SQLException {

        ResultSet resultSet = null;
        GarHistory garHistory = new GarHistory();
        OraclePreparedStatement dbpsSelectReester;


        try {
            dbpsSelectReester = (OraclePreparedStatement) getDbConnection().prepareStatement(garHistory.getSqlSelectReester());
            resultSet = dbpsSelectReester.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getDbConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void insertReester(JSONObject jsonObject) throws SQLException {

        GarHistory garHistory = new GarHistory();
        OraclePreparedStatement dbpsInsertReester;
        garHistory.SetAttributesInsert(jsonObject);

        try {
            dbpsInsertReester = (OraclePreparedStatement) getDbConnection().prepareStatement(garHistory.getSqlInsertReester());
            garHistory.SetPSInsertReester(dbpsInsertReester);
            dbpsInsertReester.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getDbConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertHistory(String fileName, String status) throws SQLException, ParseException {

        GarHistory garHistory = new GarHistory();
        OraclePreparedStatement dbpsInsertHistory;
        garHistory.SetAttributesInsertHistory(fileName);

        try {
            dbpsInsertHistory = (OraclePreparedStatement) getDbConnection().prepareStatement(garHistory.getSqlInsertHistory());
            garHistory.SetPSInsertHistory(dbpsInsertHistory, status);
            dbpsInsertHistory.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getDbConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void daleteHistory(String date) throws SQLException, ParseException {

        GarHistory garHistory = new GarHistory();
        OraclePreparedStatement dbpsDeleteHistory;

        try {
            dbpsDeleteHistory = (OraclePreparedStatement) getDbConnection().prepareStatement(garHistory.getSqlDeleteHistory());
            garHistory.SetPSDeleteHistory(dbpsDeleteHistory, date);
            dbpsDeleteHistory.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getDbConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void indexTable() throws SQLException, ParseException {

        try {
            CallableStatement updateTable = getDbConnection().prepareCall("{call indexadrtable}");
            updateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        };

        try {
            getDbConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
