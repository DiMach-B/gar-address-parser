package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;

public class AS_NORMATIVE_DOCS_KINDS extends AbstractGarTableStructure {
    public AS_NORMATIVE_DOCS_KINDS() {
        super();
    }

    private Integer ID;
    private String NAME;


    private String XmlNodeName = "NDOCKIND";
    private String sql = "insert into fias2.NDOCKIND (ID, NAME)\n" +
            "values (:ID, :NAME)\n";

    private String sqlUpdate = "UPDATE fias2.NDOCKIND SET ID=:ID, NAME=:NAME where ID=:ID";

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

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterStr(dbps, "NAME", NAME);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "ID", ID);
            SetParameterStr(dbpsUpdate, "NAME", NAME);
            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
