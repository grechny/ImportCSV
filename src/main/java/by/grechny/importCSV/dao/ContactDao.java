package by.grechny.importCSV.dao;

import by.grechny.importCSV.dto.Contact;

import java.util.List;

/**
 * Интерфейс для взаимодействия с таблицей Contacts
 */
public interface ContactDao {

    /**
     * Выборка части записей из БД с учетом сортировки
     *
     * @param start начальное значение строки
     * @param count количество строк, которые необходимо выгрузить
     * @param orderBy колонка, по которой необходимо сортировать
     * @return List c полученными записями и null, если ничего не найдено
     */
    List<Contact> selectContacts(int start, int count, String orderBy);

    /**
     * Метод для получения общего количества строк в таблице
     *
     * @return Integer c количеством строк
     */
    Integer selectCount();

    /**
     * Метод для вставки записей в БД. Можно вставлять сразу несколько строк
     * В случае, если данный ключ уже существует, обновить записи
     *
     * @param list список строк для вставки
     * @return положительное число, если записи были вставлены
     */
    Integer insertContacts(List<Contact> list);

}
