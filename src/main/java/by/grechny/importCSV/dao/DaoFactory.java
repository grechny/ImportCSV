package by.grechny.importCSV.dao;

import by.grechny.importCSV.dao.mysql.MysqlDaoFactory;

/**
 * Класс, реализующий фабрику
 */
public abstract class DaoFactory {

    /**
     * Список возможных реализаций
     */
    public enum WhichFactory {MYSQL}

    /**
     * Метод, возвращающий конкретную реализацию DAO
     * @param whichFactory необходимая реализация DAO
     * @return реализация необходимого класса DAO
     */
    public static DaoFactory getDaoFactory (WhichFactory whichFactory){

        switch (whichFactory){
            case MYSQL:
                return new MysqlDaoFactory();
        }
        return null;
    }

    /**
     * Абстрактный класс для получения реализации работы с таблицей Contacts
     * @return объект ContactDAO
     */
    public abstract ContactDao getContactDao ();

}
