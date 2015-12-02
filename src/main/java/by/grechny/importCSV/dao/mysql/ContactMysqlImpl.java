package by.grechny.importCSV.dao.mysql;

import by.grechny.importCSV.dao.ContactDao;
import by.grechny.importCSV.dto.Contact;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ���������� ������ � �������� Contacts
 */
public class ContactMysqlImpl implements ContactDao{

    /**
     * ������� ����� ������� �� �� � ������ ����������
     *
     * @param start ��������� �������� ������
     * @param count ���������� �����, ������� ���������� ���������
     * @param orderBy �������, �� ������� ���������� �����������
     * @return List c ����������� �������� � null, ���� ������ �� �������
     */
    public List<Contact> selectContacts (int start, int count, String orderBy){

        Connection connection = null;
        Statement statement = null;

        String sqlSelectAll = "SELECT * FROM `contacts` WHERE 1 ORDER BY "
                + orderBy + " ASC LIMIT " + start + ", " + count + ";";

        try {
            connection = MysqlDaoFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sqlSelectAll);
            List<Contact> contactList = new ArrayList<>();

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setLogin(rs.getString("login"));
                contact.setName(rs.getString("name"));
                contact.setSurname(rs.getString("surname"));
                contact.setEmail(rs.getString("email"));
                contact.setPhoneNumber(rs.getLong("phone_number"));

                contactList.add(contact);
            }
            return contactList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * ����� ��� ��������� ������ ���������� ����� � �������
     *
     * @return Integer c ����������� �����
     */
    public Integer selectCount (){

        Connection connection = null;
        Statement statement = null;

        String sqlSelectCount = "SELECT COUNT(*) as count FROM `contacts` WHERE 1;";

        try {
            connection = MysqlDaoFactory.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlSelectCount);
            if (rs.next()) return Integer.parseInt(rs.getString("count"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * ����� ��� ������� ������� � ��. ����� ��������� ����� ��������� �����
     * � ������, ���� ������ ���� ��� ����������, �������� ������
     *
     * @param contactList ������ ����� ��� �������
     * @return ������������� �����, ���� ������ ���� ���������
     */
    public Integer insertContacts(List<Contact> contactList) {
        Connection connection = null;
        Statement statement = null;

        ArrayList<String> values = new ArrayList<>();

        for (Contact contact : contactList){
            String contactString = "(\"" + contact.getLogin() + "\", \""
                    + contact.getName() + "\", \""
                    + contact.getSurname() + "\", \""
                    + contact.getEmail() + "\", \""
                    + contact.getPhoneNumber() + "\")";
            values.add(contactString);
        }

        String sqlInsertODKU = "INSERT IGNORE INTO contacts (login, name, surname, email, phone_number)" +
                "  VALUES " + String.join(", ", values) +
                " ON DUPLICATE KEY UPDATE name=VALUES(name),surname=VALUES(surname)," +
                "email=VALUES(email),phone_number=VALUES(phone_number);";

        try {
            connection = MysqlDaoFactory.getConnection();
            statement = connection.createStatement();
            return statement.executeUpdate(sqlInsertODKU);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
