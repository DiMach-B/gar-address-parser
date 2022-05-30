package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_ADDR_OBJ extends AbstractGarTableStructure {
    public AS_ADDR_OBJ() {
        super();
    }

    private Integer ID;
    private Integer OBJECTID;
    private String OBJECTGUID;
    private Integer CHANGEID;
    private String NAME;
    private String TYPENAME;
    private String THE_LEVEL;
    private Integer OPERTYPEID;
    private Integer PREVID;
    private Integer NEXTID;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private Integer ISACTUAL;
    private Integer ISACTIVE;

    private String XmlNodeName = "OBJECT";
    private String sql = "insert into fias2.ADDROBJ (ID, OBJECTID, OBJECTGUID, CHANGEID, NAME, TYPENAME, THE_LEVEL, OPERTYPEID, PREVID, NEXTID, UPDATEDATE, STARTDATE, ENDDATE, ISACTUAL, ISACTIVE)\n" +
            "values (:ID, :OBJECTID, :OBJECTGUID, :CHANGEID, :NAME, :TYPENAME, :THE_LEVEL, :OPERTYPEID, :PREVID, :NEXTID, :UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTUAL, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.ADDROBJ SET ID=:ID, OBJECTID=:OBJECTID, OBJECTGUID=:OBJECTGUID, CHANGEID=:CHANGEID," +
            " NAME=:NAME, TYPENAME=:TYPENAME, THE_LEVEL=:THE_LEVEL, OPERTYPEID=:OPERTYPEID, PREVID=:PREVID," +
            " NEXTID=:NEXTID, UPDATEDATE=:UPDATEDATE, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE," +
            " ISACTUAL=:ISACTUAL, ISACTIVE=:ISACTIVE where ID=:ID";

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
        OBJECTGUID = attributes.getValue("OBJECTGUID");
        CHANGEID = Integer.parseInt(attributes.getValue("CHANGEID"));
        NAME = StrToStr(attributes.getValue("NAME"));
        TYPENAME = attributes.getValue("TYPENAME");
        THE_LEVEL = attributes.getValue("LEVEL");
        OPERTYPEID = Integer.parseInt(attributes.getValue("OPERTYPEID"));
        PREVID = StrToInt(attributes.getValue("PREVID"));
        NEXTID = StrToInt(attributes.getValue("NEXTID"));
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        STARTDATE = StrToDate(attributes.getValue("STARTDATE"));
        ENDDATE = StrToDate(attributes.getValue("ENDDATE"));
        ISACTUAL = Integer.parseInt(attributes.getValue("ISACTUAL"));
        ISACTIVE = Integer.parseInt(attributes.getValue("ISACTIVE"));
        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterInt(dbps, "OBJECTID", OBJECTID);
            SetParameterStr(dbps, "OBJECTGUID", OBJECTGUID);
            SetParameterInt(dbps, "CHANGEID", CHANGEID);
            SetParameterStr(dbps, "NAME", NAME);
            SetParameterStr(dbps, "TYPENAME", TYPENAME);
            SetParameterStr(dbps, "THE_LEVEL", THE_LEVEL);
            SetParameterInt(dbps, "OPERTYPEID", OPERTYPEID);
            SetParameterInt(dbps, "PREVID", PREVID);
            SetParameterInt(dbps, "NEXTID", NEXTID);
            SetParameterDate(dbps, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbps, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbps, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbps, "ISACTUAL", ISACTUAL);
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
            SetParameterStr(dbpsUpdate, "OBJECTGUID", OBJECTGUID);
            SetParameterInt(dbpsUpdate, "CHANGEID", CHANGEID);
            SetParameterStr(dbpsUpdate, "NAME", NAME);
            SetParameterStr(dbpsUpdate, "TYPENAME", TYPENAME);
            SetParameterStr(dbpsUpdate, "THE_LEVEL", THE_LEVEL);
            SetParameterInt(dbpsUpdate, "OPERTYPEID", OPERTYPEID);
            SetParameterInt(dbpsUpdate, "PREVID", PREVID);
            SetParameterInt(dbpsUpdate, "NEXTID", NEXTID);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbpsUpdate, "ISACTUAL", ISACTUAL);
            SetParameterInt(dbpsUpdate, "ISACTIVE", ISACTIVE);
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
