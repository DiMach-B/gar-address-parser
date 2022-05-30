package gl.BD;

import oracle.jdbc.OraclePreparedStatement;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GarHistory {

    private Integer VERSIONID;
    private String TEXTVERSION;
    private String FIASCOMPLETEDBFURL;
    private String FIASCOMPLETEXMLURL;
    private String FIASDELTADBFURL;
    private String FIASDELTAXMLURL;
    private String KLADR4ARJURL;
    private String KLADR47ZURL;
    private String GARXMLFULLURL;
    private String GARXMLDELTAURL;
    private LocalDate UPLOADDATE;


    private String sqlInsertReester = "insert into fias2.REESTERUPLOADINGGAR " +
            "(VERSIONID, TEXTVERSION, FIASCOMPLETEDBFURL, FIASCOMPLETEXMLURL, FIASDELTADBFURL, FIASDELTAXMLURL" +
            ", KLADR4ARJURL, KLADR47ZURL, GARXMLFULLURL, GARXMLDELTAURL, UPLOADDATE)\n" +
            "values " +
            "(:VERSIONID, :TEXTVERSION, :FIASCOMPLETEDBFURL, :FIASCOMPLETEXMLURL, :FIASDELTADBFURL, :FIASDELTAXMLURL" +
            ", :KLADR4ARJURL, :KLADR47ZURL, :GARXMLFULLURL, :GARXMLDELTAURL, :UPLOADDATE)\n";

    private String sqlInsertHistory = "insert into fias2.DOWNLOADHISTORYGAR " +
            "(ID, UPLOADDATE, STATUS)\n" +
            "values " +
            "(:ID, :UPLOADDATE, :STATUS)\n";

    private String sqlSelectHistory = "SELECT * FROM fias2.DOWNLOADHISTORYGAR ORDER BY UPLOADDATE DESC";

    private String sqlSelectReester = "SELECT * FROM fias2.REESTERUPLOADINGGAR";

    private String sqlDeleteHistory = "DELETE FROM fias2.DOWNLOADHISTORYGAR WHERE UPLOADDATE = :UPLOADDATE";

    public String getSqlInsertReester() {
        return sqlInsertReester;
    }

    public String getSqlInsertHistory() {
        return sqlInsertHistory;
    }

    public String getSqlSelectHistory() {
        return sqlSelectHistory;
    }

    public String getSqlSelectReester() {
        return sqlSelectReester;
    }

    public String getSqlDeleteHistory() {
        return sqlDeleteHistory;
    }

    public int SetAttributesInsert(JSONObject jsonObject) {
        VERSIONID = Integer.parseInt(jsonObject.get("VersionId").toString());
        TEXTVERSION = jsonObject.get("TextVersion").toString();
        FIASCOMPLETEDBFURL = jsonObject.get("FiasCompleteDbfUrl").toString();
        FIASCOMPLETEXMLURL = jsonObject.get("FiasCompleteXmlUrl").toString();
        FIASDELTADBFURL = jsonObject.get("FiasDeltaDbfUrl").toString();
        FIASDELTAXMLURL = jsonObject.get("FiasDeltaXmlUrl").toString();
        KLADR4ARJURL = jsonObject.get("Kladr4ArjUrl").toString();
        KLADR47ZURL = jsonObject.get("Kladr47ZUrl").toString();
        GARXMLFULLURL = jsonObject.get("GarXMLFullURL").toString();
        GARXMLDELTAURL = jsonObject.get("GarXMLDeltaURL").toString();
        UPLOADDATE = LocalDate.parse(jsonObject.get("Date").toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return 0;
    }

    public int SetAttributesInsertHistory(String name) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date d = sdf.parse(name);
        sdf.applyPattern("yyyy.MM.dd");

        VERSIONID = Integer.parseInt( sdf.format(d).replaceAll("\\.", "") );
        UPLOADDATE = LocalDate.parse(name, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return 0;
    }

    public int SetPSInsertReester(OraclePreparedStatement dbps) {

        try {
            dbps.setIntAtName("VERSIONID", VERSIONID);
            dbps.setStringAtName("TEXTVERSION", TEXTVERSION);
            dbps.setStringAtName("FIASCOMPLETEDBFURL", FIASCOMPLETEDBFURL);
            dbps.setStringAtName("FIASCOMPLETEXMLURL", FIASCOMPLETEXMLURL);
            dbps.setStringAtName("FIASDELTADBFURL", FIASDELTADBFURL);
            dbps.setStringAtName("FIASDELTAXMLURL", FIASDELTAXMLURL);
            dbps.setStringAtName("KLADR4ARJURL", KLADR4ARJURL);
            dbps.setStringAtName("KLADR47ZURL", KLADR47ZURL);
            dbps.setStringAtName("GARXMLFULLURL", GARXMLFULLURL);
            dbps.setStringAtName("GARXMLDELTAURL", GARXMLDELTAURL);
            dbps.setDateAtName("UPLOADDATE", Date.valueOf(UPLOADDATE));
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }


    public int SetPSInsertHistory(OraclePreparedStatement dbps, String status) {

        try {
            dbps.setIntAtName("ID", VERSIONID);
            dbps.setDateAtName("UPLOADDATE", Date.valueOf(UPLOADDATE));
            dbps.setStringAtName("STATUS", status);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSDeleteHistory(OraclePreparedStatement dbps, String date) {
        try {
            dbps.setDateAtName("UPLOADDATE", Date.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
