package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_ADDR_OBJ_TYPES extends AbstractGarTableStructure {
    public AS_ADDR_OBJ_TYPES() {
        super();
    }

    private Integer ID;
    private Integer THE_LEVEL;
    private String SHORTNAME;
    private String NAME;
    private String THE_DESC;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private String ISACTIVE;


    private String XmlNodeName = "ADDRESSOBJECTTYPE";
    private String sql = "insert into fias2.ADDRESSOBJECTTYPE (ID, THE_LEVEL, SHORTNAME, NAME, THE_DESC, UPDATEDATE, STARTDATE, ENDDATE, ISACTIVE)\n" +
            "values (:ID, :THE_LEVEL, :SHORTNAME, :NAME, :THE_DESC, :UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.ADDRESSOBJECTTYPE SET ID=:ID, THE_LEVEL=:THE_LEVEL, SHORTNAME=:SHORTNAME, NAME=:NAME," +
            " THE_DESC=:THE_DESC, UPDATEDATE=:UPDATEDATE, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE," +
            " ISACTIVE=:ISACTIVE where ID=:ID";

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
        ID = StrToInt(attributes.getValue("ID"));
        THE_LEVEL = StrToInt(attributes.getValue("LEVEL"));
        SHORTNAME = attributes.getValue("SHORTNAME");
        NAME = attributes.getValue("NAME");
        THE_DESC = attributes.getValue("DESC");
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        STARTDATE = StrToDate(attributes.getValue("STARTDATE"));
        ENDDATE = StrToDate(attributes.getValue("ENDDATE"));
        ISACTIVE = attributes.getValue("ISACTIVE");

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterInt(dbps, "THE_LEVEL", THE_LEVEL);
            SetParameterStr(dbps, "SHORTNAME", SHORTNAME);
            SetParameterStr(dbps, "NAME", NAME);
            SetParameterStr(dbps, "THE_DESC", THE_DESC);
            SetParameterDate(dbps, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbps, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbps, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterStr(dbps, "ISACTIVE", ISACTIVE);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "ID", ID);
            SetParameterInt(dbpsUpdate, "THE_LEVEL", THE_LEVEL);
            SetParameterStr(dbpsUpdate, "SHORTNAME", SHORTNAME);
            SetParameterStr(dbpsUpdate, "NAME", NAME);
            SetParameterStr(dbpsUpdate, "THE_DESC", THE_DESC);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterStr(dbpsUpdate, "ISACTIVE", ISACTIVE);
            dbpsUpdate.setIntAtName("ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
