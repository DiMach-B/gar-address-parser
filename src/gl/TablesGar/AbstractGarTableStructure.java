package gl.TablesGar;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import oracle.jdbc.OraclePreparedStatement;
import org.xml.sax.Attributes;

public abstract class AbstractGarTableStructure {
    public AbstractGarTableStructure() {
        super();
    }

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate StrToDate(String AStr) {

        if (AStr == null) {
            return null;
        } else {
            return LocalDate.parse(AStr, dateFormat);
        }
    }

    public java.sql.Date LocalDateToSqlDate(LocalDate ADate) {
        if (ADate == null) {
            return null;
        } else {
            return java.sql.Date.valueOf(ADate);
        }
    }

    public Integer StrToInt(String AStr) {
        if (AStr == null) {
            return null;
        } else {
            return Integer.parseInt(AStr);
        }
    }

    public String StrToStr(String AStr) {
        if (AStr == null) {
            return "Not";
        } else {
            return AStr;
        }
    }

    public static void SetParameterStr(OraclePreparedStatement dbps, String APName, String APValue) throws SQLException {
        if (APValue == null) {
            dbps.setNullAtName(APName, 0);
        } else {
            dbps.setStringAtName(APName, APValue);
        }
    }

    public static void SetParameterInt(OraclePreparedStatement dbps, String APName, Integer APValue) throws SQLException {
        if (APValue == null) {
            dbps.setNullAtName(APName, 0);
        } else {
            dbps.setIntAtName(APName, APValue);
        }
    }

    public static void SetParameterDate(OraclePreparedStatement dbps, String APName, java.sql.Date APValue) throws SQLException {
        if (APValue == null) {
            dbps.setNullAtName(APName, 0);
        } else {
            dbps.setDateAtName(APName, APValue);
        }
    }

    abstract public String GetXmlNodeName();

    abstract public String GetSql();

    abstract public String GetSqlUpdate();

    abstract public int SetAttributes(Attributes attributes);

    abstract public int SetPS(OraclePreparedStatement dbps);

    abstract public int SetPSUpdate(OraclePreparedStatement dbpsUpdate, Integer IdElement);
}
