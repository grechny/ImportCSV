package by.grechny.importCSV.dao;

import by.grechny.importCSV.dao.mysql.MysqlDaoFactory;

/**
 * �����, ����������� �������
 */
public abstract class DaoFactory {

    /**
     * ������ ��������� ����������
     */
    public enum WhichFactory {MYSQL}

    /**
     * �����, ������������ ���������� ���������� DAO
     * @param whichFactory ����������� ���������� DAO
     * @return ���������� ������������ ������ DAO
     */
    public static DaoFactory getDaoFactory (WhichFactory whichFactory){

        switch (whichFactory){
            case MYSQL:
                return new MysqlDaoFactory();
        }
        return null;
    }

    /**
     * ����������� ����� ��� ��������� ���������� ������ � �������� Contacts
     * @return ������ ContactDAO
     */
    public abstract ContactDao getContactDao ();

}
