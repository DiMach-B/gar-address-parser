package gl.TablesGar;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

import java.sql.SQLException;

public class AS_ADDR_OBJ_DIVISION extends AbstractGarTableStructure {
    public AS_ADDR_OBJ_DIVISION() {
        super();
    }

    private Integer ID;
    private Integer PARENTID;
    private Integer CHILDID;
    private Integer CHANGEID;

    private String XmlNodeName = "ITEM";
    private String sql = "insert into fias2.ADDROBJDIVISION (ID, PARENTID, CHILDID, CHANGEID)\n" +
            "values (:ID, :PARENTID, :CHILDID, :CHANGEID)\n";

    private String sqlUpdate = "UPDATE fias2.ADDROBJDIVISION SET ID=:ID, PARENTID=:PARENTID, CHILDID=:CHILDID, CHANGEID=:CHANGEID where ID=:ID";

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
        PARENTID = Integer.parseInt(attributes.getValue("PARENTID"));
        CHILDID = Integer.parseInt(attributes.getValue("CHILDID"));
        CHANGEID = Integer.parseInt(attributes.getValue("CHANGEID"));

        return 0;
    }

    public int SetPS(OraclePreparedStatement dbps) {
        try {
            SetParameterInt(dbps, "ID", ID);
            SetParameterInt(dbps, "PARENTID", PARENTID);
            SetParameterInt(dbps, "CHILDID", CHILDID);
            SetParameterInt(dbps, "CHANGEID", CHANGEID);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }

    public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement) {
        try {
            SetParameterInt(dbpsUpdate, "ID", ID);
            SetParameterInt(dbpsUpdate, "PARENTID", PARENTID);
            SetParameterInt(dbpsUpdate, "CHILDID", CHILDID);
            SetParameterInt(dbpsUpdate, "CHANGEID", CHANGEID);

            SetParameterInt(dbpsUpdate, "ID", IdElement);
            return 0;
        } catch (SQLException e) {
            return 1;
        }
    }
}
