package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_PARAM extends AbstractGarTableStructure {
    public AS_PARAM() {
        super();
    }

    private Integer ID;
    private Integer OBJECTID;
    private Integer CHANGEID;
    private Integer CHANGEIDEND;
    private Integer TYPEID;
    private String VALUE;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;


    private String XmlNodeName = "PARAM";
    private String sql = "insert into fias2.PARAM (ID, OBJECTID, CHANGEID, CHANGEIDEND, TYPEID, VALUE, UPDATEDATE, STARTDATE, ENDDATE)\n" +
            "values (:ID, :OBJECTID, :CHANGEID, :CHANGEIDEND, :TYPEID, :VALUE,:UPDATEDATE, :STARTDATE, :ENDDATE)\n";

    private String sqlUpdate = "UPDATE fias2.PARAM SET ID=:ID, OBJECTID=:OBJECTID, CHANGEID=:CHANGEID, CHANGEIDEND=:CHANGEIDEND, TYPEID=:TYPEID, " +
            "VALUE=:VALUE, UPDATEDATE=:UPDATEDATE, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE where ID=:ID";

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
        CHANGEID = Integer.parseInt(attributes.getValue("CHANGEID"));
        CHANGEIDEND = Integer.parseInt(attributes.getValue("CHANGEIDEND"));
        TYPEID = Integer.parseInt(attributes.getValue("TYPEID"));
        VALUE = attributes.getValue("VALUE");
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        STARTDATE = StrToDate(attributes.getValue("STARTDATE"));
        ENDDATE = StrToDate(attributes.getValue("ENDDATE"));

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterInt(dbps, "OBJECTID", OBJECTID);
            SetParameterInt(dbps, "CHANGEID", CHANGEID);
            SetParameterInt(dbps, "CHANGEIDEND", CHANGEIDEND);
            SetParameterInt(dbps, "TYPEID", TYPEID);
            SetParameterStr(dbps, "VALUE", VALUE);
            SetParameterDate(dbps, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbps, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbps, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "ID", ID);
            SetParameterInt(dbpsUpdate, "OBJECTID", OBJECTID);
            SetParameterInt(dbpsUpdate, "CHANGEID", CHANGEID);
            SetParameterInt(dbpsUpdate, "CHANGEIDEND", CHANGEIDEND);
            SetParameterInt(dbpsUpdate, "TYPEID", TYPEID);
            SetParameterStr(dbpsUpdate, "VALUE", VALUE);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
