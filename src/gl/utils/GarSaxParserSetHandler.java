package gl.utils;

import gl.TablesGar.AbstractGarTableStructure;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.driver.OracleConnection;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GarSaxParserSetHandler extends DefaultHandler {

    private final int batchSize = 10000;
    private int count = 0;
    private int countUpdate = 0;
    private int countInsert = 0;
    private int countErr = 0;
    private String FileName;

    public GarSaxParserSetHandler(String FileName) {

        super();
        this.FileName = FileName;
    }

    OracleConnection dbconnection = null;
    String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=db2.miit.ru)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=ctest.miit.ru)))";
    String dbuser = "fias2";
    String dbpassword = "dfg336yfh";
    OraclePreparedStatement dbps;
    OraclePreparedStatement dbpsUpdate;


    public AbstractGarTableStructure fts;

    @Override
    public void startDocument() throws SAXException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbconnection = (OracleConnection) DriverManager.getConnection(dburl, dbuser, dbpassword);
            dbps = (OraclePreparedStatement) dbconnection.prepareStatement(fts.GetSql());
            dbpsUpdate = (OraclePreparedStatement) dbconnection.prepareStatement(fts.GetSqlUpdate());


        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        int rs;
        int check = 0; //флаг

        if (qName.equalsIgnoreCase(fts.GetXmlNodeName())) {
            try {
                if (fts.GetXmlNodeName().equals("PARAM")
                    &&
                    !(
                     attributes.getValue("TYPEID").equals("5")
                     || attributes.getValue("TYPEID").equals("6")
                     || attributes.getValue("TYPEID").equals("7")
                    )
                ) return;

                rs = fts.SetAttributes(attributes);
                if (rs == 1) {
                    ++countErr;
                }
//              Для обновления данных
                rs = fts.SetPSUpdate(dbpsUpdate, Integer.parseInt(attributes.getValue("ID")));
                if (rs == 1) {
                    ++countErr;
                }

                try {
                    check = dbpsUpdate.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    ++countErr;
                }

                if (check == 0) {
                    rs = fts.SetPS(dbps);
                    if (rs == 1) {
                        ++countErr;
                    }
                    dbps.addBatch();
//                  Подсчёт количества вставок новых строк
                    countInsert++;
                } else {
//                  Подсчёт количества обнавлённых строк
                    countUpdate++;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                ++countErr;
            }
            if (++count % batchSize == 0) {
                try {
                    dbps.executeBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                    ++countErr;
                }
            }

        }
    }

    @Override
    public void endDocument() {
        try {
            dbpsUpdate.close();
        } catch (Exception e) {
        }

        try {
            dbps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            ++countErr;
        }
        System.out.println("FileName: " + FileName + "\n" +
                "Inserting rows: " + countInsert + "\n" +
                "Updating rows: " + countUpdate + "\n" +
                "Error rows: " + countErr + "\n" +
                "End");

        OutputInformation.FileName = new File(FileName).getName();
        Pattern pattern = Pattern.compile("\\\\\\d+\\\\");
        Matcher matcher = pattern.matcher(FileName);

        while (matcher.find())
            OutputInformation.Region = matcher.group().replace('\\', ' ');

        OutputInformation.Inserting = countInsert;
        OutputInformation.Updating = countUpdate;
        OutputInformation.Errors = countErr;
        OutputInformation.TotleInserting += countInsert;
        OutputInformation.TotleUpdating += countUpdate;
        OutputInformation.TotleErrors += countErr;

        try {
            dbconnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbconnection = null;
    }
}

