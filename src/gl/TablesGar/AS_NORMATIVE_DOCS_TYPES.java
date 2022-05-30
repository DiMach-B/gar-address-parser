package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;
import java.time.LocalDate;

public class AS_NORMATIVE_DOCS_TYPES extends AbstractGarTableStructure {
    public AS_NORMATIVE_DOCS_TYPES() {
        super();
    }

    private Integer ID;
    private String NAME;
    private LocalDate STARTDATE;
    private LocalDate ENDDATE;


    private String XmlNodeName = "NDOCTYPE";
    private String sql = "insert into fias2.NDOCTYPE (ID, NAME, STARTDATE, ENDDATE)\n" +
            "values (:ID, :NAME, :STARTDATE, :ENDDATE)\n";

    private String sqlUpdate = "UPDATE fias2.NDOCTYPE SET ID=:ID, NAME=:NAME, STARTDATE=:STARTDATE, ENDDATE=:ENDDATE where ID=:ID";

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
        STARTDATE = StrToDate(attributes.getValue("STARTDATE"));
        ENDDATE = StrToDate(attributes.getValue("ENDDATE"));

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterStr(dbps, "NAME", NAME);
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
            SetParameterStr(dbpsUpdate, "NAME", NAME);
            SetParameterDate(dbpsUpdate, "STARTDATE", LocalDateToSqlDate(STARTDATE));
            SetParameterDate(dbpsUpdate, "ENDDATE", LocalDateToSqlDate(ENDDATE));
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
