package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_CHANGE_HISTORY extends AbstractGarTableStructure {
    public AS_CHANGE_HISTORY() {
        super();
    }

    private Integer CHANGEID;
    private Integer OBJECTID;
    private String ADROBJECTID;
    private Integer OPERTYPEID;
    private Integer NDOCID;
    private LocalDate CHANGEDATE;


    private String XmlNodeName = "ITEM";
    private String sql = "insert into fias2.CHANGEHISTORY (CHANGEID, OBJECTID, ADROBJECTID, OPERTYPEID, NDOCID, CHANGEDATE)\n" +
            "values (:CHANGEID, :OBJECTID, :ADROBJECTID, :OPERTYPEID, :NDOCID, :CHANGEDATE)\n";

    private String sqlUpdate = "UPDATE fias2.CHANGEHISTORY SET CHANGEID=:CHANGEID, OBJECTID=:OBJECTID, ADROBJECTID=:ADROBJECTID, " +
            "OPERTYPEID=:OPERTYPEID, NDOCID=:NDOCID, CHANGEDATE=:CHANGEDATE where ID=:ID";

    public String GetXmlNodeName() {
        return XmlNodeName;
    }

    public String GetSql() {
        return sql;
    }

    public String GetSqlUpdate() {
        return sqlUpdate;
    }

    public int SetAttributes(Attributes attributes) {
        CHANGEID = Integer.parseInt(attributes.getValue("CHANGEID"));
        OBJECTID = Integer.parseInt(attributes.getValue("OBJECTID"));
        ADROBJECTID = attributes.getValue("ADROBJECTID");
        OPERTYPEID = Integer.parseInt(attributes.getValue("OPERTYPEID"));
        NDOCID = StrToInt(attributes.getValue("NDOCID"));
        CHANGEDATE = StrToDate(attributes.getValue("CHANGEDATE"));

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "CHANGEID", CHANGEID);
            SetParameterInt(dbps, "OBJECTID", OBJECTID);
            SetParameterStr(dbps, "ADROBJECTID", ADROBJECTID);
            SetParameterInt(dbps, "OPERTYPEID", OPERTYPEID);
            SetParameterInt(dbps, "NDOCID", NDOCID);
            SetParameterDate(dbps, "CHANGEDATE", LocalDateToSqlDate(CHANGEDATE));
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "CHANGEID", CHANGEID);
            SetParameterInt(dbpsUpdate, "OBJECTID", OBJECTID);
            SetParameterStr(dbpsUpdate, "ADROBJECTID", ADROBJECTID);
            SetParameterInt(dbpsUpdate, "OPERTYPEID", OPERTYPEID);
            SetParameterInt(dbpsUpdate, "NDOCID", NDOCID);
            SetParameterDate(dbpsUpdate, "CHANGEDATE", LocalDateToSqlDate(CHANGEDATE));
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
