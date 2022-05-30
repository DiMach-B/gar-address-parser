package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_HOUSES extends AbstractGarTableStructure {
    public AS_HOUSES() {
        super();
    }

    private Integer ID;
    private Integer OBJECTID;
    private String OBJECTGUID;
    private Integer CHANGEID;
    private String HOUSENUM;
    private String ADDNUM1;
    private String ADDNUM2;
    private Integer HOUSETYPE;
    private String ADDTYPE1;
    private String ADDTYPE2;
    private Integer OPERTYPEID;
    private Integer PREVID;
    private Integer NEXTID;
    private LocalDate UPDATEDATE;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;
    private Integer ISACTUAL;
    private Integer ISACTIVE;


    private String XmlNodeName = "HOUSE";
    private String sql = "insert into fias2.HOUSE (ID, OBJECTID, OBJECTGUID, CHANGEID, HOUSENUM, ADDNUM1, ADDNUM2, HOUSETYPE, ADDTYPE1, " +
            "ADDTYPE2, OPERTYPEID, PREVID, NEXTID, UPDATEDATE, STARTDATE, ENDDATE, ISACTUAL, ISACTIVE)\n" +
            "values (:ID, :OBJECTID, :OBJECTGUID, :CHANGEID, :HOUSENUM, :ADDNUM1, :ADDNUM2, :HOUSETYPE, :ADDTYPE1, " +
            ":ADDTYPE2, :OPERTYPEID, :PREVID, :NEXTID,:UPDATEDATE, :STARTDATE, :ENDDATE, :ISACTUAL, :ISACTIVE)\n";

    private String sqlUpdate = "UPDATE fias2.HOUSE SET ID=:ID, OBJECTID=:OBJECTID, OBJECTGUID=:OBJECTGUID, CHANGEID=:CHANGEID," +
            " HOUSENUM=:HOUSENUM, ADDNUM1=:ADDNUM1, ADDNUM2=:ADDNUM2, HOUSETYPE=:HOUSETYPE, ADDTYPE1=:ADDTYPE1, " +
            " ADDTYPE2=:ADDTYPE2, OPERTYPEID=:OPERTYPEID, PREVID=:PREVID," +
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
        HOUSENUM = attributes.getValue("HOUSENUM");
        ADDNUM1 = attributes.getValue("ADDNUM1");
        ADDNUM2 = attributes.getValue("ADDNUM2");
        HOUSETYPE = StrToInt(attributes.getValue("HOUSETYPE"));
        ADDTYPE1 = attributes.getValue("ADDTYPE1");
        ADDTYPE2 = attributes.getValue("ADDTYPE2");
        OPERTYPEID = Integer.parseInt(attributes.getValue("OPERTYPEID"));
        PREVID = Integer.parseInt(attributes.getValue("PREVID"));
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
            SetParameterStr(dbps, "HOUSENUM", HOUSENUM);
            SetParameterStr(dbps, "ADDNUM1", ADDNUM1);
            SetParameterStr(dbps, "ADDNUM2", ADDNUM2);
            SetParameterInt(dbps, "HOUSETYPE", HOUSETYPE);
            SetParameterStr(dbps, "ADDTYPE1", ADDTYPE1);
            SetParameterStr(dbps, "ADDTYPE2", ADDTYPE2);
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
            SetParameterStr(dbpsUpdate, "HOUSENUM", HOUSENUM);
            SetParameterStr(dbpsUpdate, "ADDNUM1", ADDNUM1);
            SetParameterStr(dbpsUpdate, "ADDNUM2", ADDNUM2);
            SetParameterInt(dbpsUpdate, "HOUSETYPE", HOUSETYPE);
            SetParameterStr(dbpsUpdate, "ADDTYPE1", ADDTYPE1);
            SetParameterStr(dbpsUpdate, "ADDTYPE2", ADDTYPE2);
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
