package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_MUN_HIERARCHY extends AbstractGarTableStructure {
    public AS_MUN_HIERARCHY() {
        super();
    }

    private Integer ID;
    private Integer OBJECTID;
    private Integer PARENTOBJID;
    private Integer CHANGEID;
    private String OKTMO;
    private Integer PREVID;
    private Integer NEXTID;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private Integer ISACTIVE;


    private String XmlNodeName = "ITEM";
    private String sql = "insert into fias2.MUNHIERARCHY (ID, OBJECTID, PARENTOBJID, CHANGEID, OKTMO, PREVID, NEXTID, UPDATEDATE, STARTDATE, ENDDATE, ISACTIVE)\n" +
            "values (:ID, :OBJECTID, :PARENTOBJID, :CHANGEID, :OKTMO, :PREVID, :NEXTID, :UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.MUNHIERARCHY SET ID=:ID, OBJECTID=:OBJECTID, PARENTOBJID=:PARENTOBJID, CHANGEID=:CHANGEID," +
            " OKTMO=:OKTMO, PREVID=:PREVID, NEXTID=:NEXTID, UPDATEDATE=:UPDATEDATE, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE," +
            "  ISACTIVE=:ISACTIVE where ID=:ID";
    ;

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
        ID = Integer.parseInt(attributes.getValue("ID"));
        OBJECTID = Integer.parseInt(attributes.getValue("OBJECTID"));
        PARENTOBJID = StrToInt(attributes.getValue("PARENTOBJID"));
        CHANGEID = Integer.parseInt(attributes.getValue("CHANGEID"));
        OKTMO = attributes.getValue("OKTMO");
        PREVID = Integer.parseInt(attributes.getValue("PREVID"));
        NEXTID = Integer.parseInt(attributes.getValue("NEXTID"));
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        STARTDATE = StrToDate(attributes.getValue("STARTDATE"));
        ENDDATE = StrToDate(attributes.getValue("ENDDATE"));
        ISACTIVE = Integer.parseInt(attributes.getValue("ISACTIVE"));

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterInt(dbps, "OBJECTID", OBJECTID);
            SetParameterInt(dbps, "PARENTOBJID", PARENTOBJID);
            SetParameterInt(dbps, "CHANGEID", CHANGEID);
            SetParameterStr(dbps, "OKTMO", OKTMO);
            SetParameterInt(dbps, "PREVID", PREVID);
            SetParameterInt(dbps, "NEXTID", NEXTID);
            SetParameterDate(dbps, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbps, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbps, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbps, "ISACTIVE", ISACTIVE);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "ID", ID);
            SetParameterInt(dbpsUpdate, "OBJECTID", OBJECTID);
            SetParameterInt(dbpsUpdate, "PARENTOBJID", PARENTOBJID);
            SetParameterInt(dbpsUpdate, "CHANGEID", CHANGEID);
            SetParameterStr(dbpsUpdate, "OKTMO", OKTMO);
            SetParameterInt(dbpsUpdate, "PREVID", PREVID);
            SetParameterInt(dbpsUpdate, "NEXTID", NEXTID);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbpsUpdate, "ISACTIVE", ISACTIVE);
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
