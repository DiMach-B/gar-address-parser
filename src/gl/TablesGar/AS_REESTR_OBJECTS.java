package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_REESTR_OBJECTS extends AbstractGarTableStructure {
    public AS_REESTR_OBJECTS() {
        super();
    }

    private Integer OBJECTID;
    private LocalDate CREATEDATE;
    private Integer CHANGEID;
    private Integer LEVELID;
    private LocalDate UPDATEDATE;
    private String OBJECTGUID;
    private Integer ISACTIVE;


    private String XmlNodeName = "OBJECT";
    private String sql = "insert into fias2.REESTROBJECTS (OBJECTID, CREATEDATE, CHANGEID, LEVELID, UPDATEDATE, OBJECTGUID, ISACTIVE)\n" +
            "values (:OBJECTID, :CREATEDATE, :CHANGEID, :LEVELID, :UPDATEDATE, :OBJECTGUID, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.REESTROBJECTS SET OBJECTID=:OBJECTID, CREATEDATE=:CREATEDATE, CHANGEID=:CHANGEID, LEVELID=:LEVELID, " +
            "UPDATEDATE=:UPDATEDATE, OBJECTGUID=:OBJECTGUID, ISACTIVE=:ISACTIVE where ID=:ID";

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
        OBJECTID = Integer.parseInt(attributes.getValue("OBJECTID"));
        CREATEDATE = StrToDate(attributes.getValue("CREATEDATE"));
        CHANGEID = Integer.parseInt(attributes.getValue("CHANGEID"));
        LEVELID = Integer.parseInt(attributes.getValue("LEVELID"));
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        OBJECTGUID = attributes.getValue("OBJECTGUID");
        ISACTIVE = Integer.parseInt(attributes.getValue("ISACTIVE"));

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "OBJECTID", OBJECTID);
            SetParameterDate(dbps, "CREATEDATE", LocalDateToSqlDate(CREATEDATE));
            SetParameterInt(dbps, "CHANGEID", CHANGEID);
            SetParameterInt(dbps, "LEVELID", LEVELID);
            SetParameterDate(dbps, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterStr(dbps, "OBJECTGUID", OBJECTGUID);
            SetParameterInt(dbps, "ISACTIVE", ISACTIVE);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "OBJECTID", OBJECTID);
            SetParameterDate(dbpsUpdate, "CREATEDATE", LocalDateToSqlDate(CREATEDATE));
            SetParameterInt(dbpsUpdate, "CHANGEID", CHANGEID);
            SetParameterInt(dbpsUpdate, "LEVELID", LEVELID);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterStr(dbpsUpdate, "OBJECTGUID", OBJECTGUID);
            SetParameterInt(dbpsUpdate, "ISACTIVE", ISACTIVE);
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
