package by.grechny.importCSV.dao.mysql;

import by.grechny.importCSV.dao.ContactDao;
import by.grechny.importCSV.dao.DaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ���������� ������������� �� MySQL
 */
public class MysqlDaoFactory extends DaoFactory{

    /**
     * ����� ��� �������� ���������� � ��
     * @return ������� ���������� � ������ ������, null � ������ �������
     */
    protected static Connection getConnection () {
        Properties properties = new Properties();
        try {
            InputStream propertiesInputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("mysqlConfig.properties");
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            System.err.println("Properties file not found");
            return null;
        }

        String DRIVER = properties.getProperty("DRIVER");
        String DBURL = properties.getProperty("DBURL");
        String DBUSER = properties.getProperty("DBUSER");
        String DBPASS = properties.getProperty("DBPASS");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Connection myConnection;
        try {
            myConnection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return myConnection;
    }

    /**
     * ����� ��� �������� ���������� ������ ��� ������ � �������� Contacts � MySQL
     * @return ��������� ������ ContactMysqlImpl
     */
    @Override
    public ContactDao getContactDao() {
        return new ContactMysqlImpl();
    }
}

