package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_OBJECT_LEVELS extends AbstractGarTableStructure {
    public AS_OBJECT_LEVELS() {
        super();
    }

    private Integer THE_LEVEL;
    private String NAME;
    private String SHORTNAME;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private String ISACTIVE;


    private String XmlNodeName = "OBJECTLEVEL";
    private String sql = "insert into fias2.OBJECTLEVEL (THE_LEVEL, NAME, SHORTNAME, UPDATEDATE, STARTDATE, ENDDATE, ISACTIVE)\n" +
            "values (:THE_LEVEL, :NAME, :SHORTNAME, :UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.OBJECTLEVEL SET THE_LEVEL=:THE_LEVEL, NAME=:NAME, SHORTNAME=:SHORTNAME, UPDATEDATE=:UPDATEDATE, " +
            "STARTDATE=:STARTDATE, ENDDATE=:ENDDATE, ISACTIVE=:ISACTIVE where ID=:ID";

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
        THE_LEVEL = Integer.parseInt(attributes.getValue("LEVEL"));
        NAME = attributes.getValue("NAME");
        SHORTNAME = attributes.getValue("SHORTNAME");
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        STARTDATE = StrToDate(attributes.getValue("STARTDATE"));
        ENDDATE = StrToDate(attributes.getValue("ENDDATE"));
        ISACTIVE = attributes.getValue("ISACTIVE");

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "THE_LEVEL", THE_LEVEL);
            SetParameterStr(dbps, "NAME", NAME);
            SetParameterStr(dbps, "SHORTNAME", SHORTNAME);
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
            SetParameterInt(dbpsUpdate, "THE_LEVEL", THE_LEVEL);
            SetParameterStr(dbpsUpdate, "NAME", NAME);
            SetParameterStr(dbpsUpdate, "SHORTNAME", SHORTNAME);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterStr(dbpsUpdate, "ISACTIVE", ISACTIVE);
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
