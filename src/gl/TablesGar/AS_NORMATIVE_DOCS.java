package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_NORMATIVE_DOCS extends AbstractGarTableStructure {
    public AS_NORMATIVE_DOCS() {
        super();
    }

    private Integer ID;
    private String NAME;
    private LocalDate THE_DATE;
    private String THE_NUMBER;
    private Integer THE_TYPE;
    private Integer KIND;
    private LocalDate UPDATEDATE;
    private String ORGNAME;
    private String REGNUM;
    private LocalDate REGDATE;
    private LocalDate ACCDATE;
    private String THE_COMMENT;


    private String XmlNodeName = "NORMDOC";
    private String sql = "insert into fias2.NORMDOC (ID, NAME, THE_DATE, THE_NUMBER, THE_TYPE, KIND, UPDATEDATE, ORGNAME, REGNUM, REGDATE, ACCDATE, THE_COMMENT)\n" +
            "values (:ID, :NAME, :THE_DATE, :THE_NUMBER, :THE_TYPE, :KIND, :UPDATEDATE, :ORGNAME, :REGNUM, :REGDATE, :ACCDATE, :THE_COMMENT)\n";

    private String sqlUpdate = "UPDATE fias2.NORMDOC SET ID=:ID, NAME=:NAME, THE_DATE=:THE_DATE, THE_NUMBER=:THE_NUMBER, THE_TYPE=:THE_TYPE, KIND=:KIND, UPDATEDATE=:UPDATEDATE, " +
            "ORGNAME=:ORGNAME, REGNUM=:REGNUM, REGDATE=:REGDATE, ACCDATE=:ACCDATE, THE_COMMENT=:THE_COMMENT where ID=:ID";

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
        NAME = attributes.getValue("NAME");
        THE_DATE = StrToDate(attributes.getValue("DATE"));
        THE_NUMBER = attributes.getValue("NUMBER");
        THE_TYPE = StrToInt(attributes.getValue("TYPE"));
        KIND = StrToInt(attributes.getValue("KIND"));
        UPDATEDATE = StrToDate(attributes.getValue("UPDATEDATE"));
        ORGNAME = attributes.getValue("ORGNAME");
        REGNUM = attributes.getValue("REGNUM");
        REGDATE = StrToDate(attributes.getValue("REGDATE"));
        ACCDATE = StrToDate(attributes.getValue("ACCDATE"));
        THE_COMMENT = attributes.getValue("COMMENT");
        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterStr(dbps, "NAME", NAME);
            SetParameterDate(dbps, "THE_DATE", LocalDateToSqlDate(THE_DATE));
            SetParameterStr(dbps, "THE_NUMBER", THE_NUMBER);
            SetParameterInt(dbps, "THE_TYPE", THE_TYPE);
            SetParameterInt(dbps, "KIND", KIND);
            SetParameterDate(dbps, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterStr(dbps, "ORGNAME", ORGNAME);
            SetParameterStr(dbps, "REGNUM", REGNUM);
            SetParameterDate(dbps, "REGDATE", LocalDateToSqlDate(REGDATE));
            SetParameterDate(dbps, "ACCDATE", LocalDateToSqlDate(ACCDATE));
            SetParameterStr(dbps, "THE_COMMENT", THE_COMMENT);

            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "ID", ID);
            SetParameterStr(dbpsUpdate, "NAME", NAME);
            SetParameterDate(dbpsUpdate, "THE_DATE", LocalDateToSqlDate(THE_DATE));
            SetParameterStr(dbpsUpdate, "THE_NUMBER", THE_NUMBER);
            SetParameterInt(dbpsUpdate, "THE_TYPE", THE_TYPE);
            SetParameterInt(dbpsUpdate, "KIND", KIND);
            SetParameterDate(dbpsUpdate, "UPDATEDATE", LocalDateToSqlDate(UPDATEDATE));
            SetParameterStr(dbpsUpdate, "ORGNAME", ORGNAME);
            SetParameterStr(dbpsUpdate, "REGNUM", REGNUM);
            SetParameterDate(dbpsUpdate, "REGDATE", LocalDateToSqlDate(REGDATE));
            SetParameterDate(dbpsUpdate, "ACCDATE", LocalDateToSqlDate(ACCDATE));
            SetParameterStr(dbpsUpdate, "THE_COMMENT", THE_COMMENT);
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
