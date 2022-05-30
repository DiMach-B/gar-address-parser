package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_ADM_HIERARCHY extends AbstractGarTableStructure {
    public AS_ADM_HIERARCHY() {
        super();
    }

    private Integer ID;
    private Integer OBJECTID;
    private Integer PARENTOBJID;
    private Integer CHANGEID;
    private String REGIONCODE;
    private String AREACODE;
    private String CITYCODE;
    private String PLACECODE;
    private String PLANCODE;
    private String STREETCODE;
    private Integer PREVID;
    private Integer NEXTID;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private Integer ISACTIVE;


    private String XmlNodeCHANGEID = "ITEM";
    private String sql = "insert into fias2.ADMHIERARCHY (ID, OBJECTID, PARENTOBJID, CHANGEID, REGIONCODE, AREACODE, CITYCODE, PLACECODE, PLANCODE, STREETCODE, PREVID, NEXTID, UPDATEDATE, STARTDATE, ENDDATE, ISACTIVE)\n" +
            "values (:ID, :OBJECTID, :PARENTOBJID, :CHANGEID, :REGIONCODE, :AREACODE, :CITYCODE, :PLACECODE, :PLANCODE, :STREETCODE, :PREVID, :NEXTID, :UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.ADMHIERARCHY SET ID=:ID, OBJECTID=:OBJECTID, PARENTOBJID=:PARENTOBJID, CHANGEID=:CHANGEID, REGIONCODE=:REGIONCODE," +
            " AREACODE=:AREACODE, CITYCODE=:CITYCODE, PLACECODE=:PLACECODE, PLANCODE=:PLANCODE, STREETCODE=:STREETCODE, " +
            " PREVID=:PREVID, NEXTID=:NEXTID, UPDATEDATE=:UPDATEDATE, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE, " +
            " ISACTIVE=:ISACTIVE where ID=:ID";

    public String GetXmlNodeName() {
        return XmlNodeCHANGEID;
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
        AREACODE = attributes.getValue("REGIONCODE");
        REGIONCODE = attributes.getValue("AREACODE");
        CITYCODE = attributes.getValue("CITYCODE");
        PLACECODE = attributes.getValue("PLACECODE");
        PLANCODE = attributes.getValue("PLANCODE");
        STREETCODE = attributes.getValue("STREETCODE");
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
            SetParameterStr(dbps, "REGIONCODE", REGIONCODE);
            SetParameterStr(dbps, "AREACODE", AREACODE);
            SetParameterStr(dbps, "CITYCODE", CITYCODE);
            SetParameterStr(dbps, "PLACECODE", PLACECODE);
            SetParameterStr(dbps, "PLANCODE", PLANCODE);
            SetParameterStr(dbps, "STREETCODE", STREETCODE);
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
            SetParameterStr(dbpsUpdate, "REGIONCODE", REGIONCODE);
            SetParameterStr(dbpsUpdate, "AREACODE", AREACODE);
            SetParameterStr(dbpsUpdate, "CITYCODE", CITYCODE);
            SetParameterStr(dbpsUpdate, "PLACECODE", PLACECODE);
            SetParameterStr(dbpsUpdate, "PLANCODE", PLANCODE);
            SetParameterStr(dbpsUpdate, "STREETCODE", STREETCODE);
            SetParameterInt(dbpsUpdate, "PREVID", PREVID);
            SetParameterInt(dbpsUpdate, "NEXTID", NEXTID);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbpsUpdate, "ISACTIVE", ISACTIVE);
            dbpsUpdate.setIntAtName("ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
