package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_ROOM_TYPES extends AbstractGarTableStructure {
    public AS_ROOM_TYPES() {
        super();
    }

    private Integer ID;
    private String NAME;
    private String SHORTNAME;
    private String THE_DESC;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private String ISACTIVE;


    private String XmlNodeName = "ROOMTYPE";
    private String sql = "insert into fias2.ROOMTYPE (ID, NAME, SHORTNAME, THE_DESC, UPDATEDATE, STARTDATE, ENDDATE, ISACTIVE)\n" +
            "values (:ID, :NAME, :SHORTNAME, :THE_DESC, :UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.ROOMTYPE SET ID=:ID, NAME=:NAME, SHORTNAME=:SHORTNAME, THE_DESC=:THE_DESC, " +
            " UPDATEDATE=:UPDATEDATE, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE," +
            " ISACTIVE=:ISACTIVE where ID=:ID";
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
        NAME = attributes.getValue("NAME");
        SHORTNAME = attributes.getValue("SHORTNAME");
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
            SetParameterStr(dbps, "NAME", NAME);
            SetParameterStr(dbps, "SHORTNAME", SHORTNAME);
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
            dbpsUpdate.setIntAtName("ID", ID);
            dbpsUpdate.setStringAtName("NAME", NAME);
            dbpsUpdate.setStringAtName("SHORTNAME", SHORTNAME);
            dbpsUpdate.setStringAtName("THE_DESC", THE_DESC);
            dbpsUpdate.setDateAtName("UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            dbpsUpdate.setDateAtName("STARTDATE", LocalDateToSqlDate(STARTDATE));
            dbpsUpdate.setDateAtName("ENDDATE", LocalDateToSqlDate(ENDDATE));
            dbpsUpdate.setStringAtName("ISACTIVE", ISACTIVE);
            dbpsUpdate.setIntAtName("ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
