package by.grechny.importCSV.core;

import by.grechny.importCSV.dao.ContactDao;
import by.grechny.importCSV.dao.DaoFactory;
import by.grechny.importCSV.dto.Contact;

import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для парсинга csv
 */
public class Import {

    /**
     * Данный метод парсит полученный csv построчно, складывает в массив
     * Чтобы избежать переполнения массива в случае, когда строк слишком много,
     * за раз вставляется в БД не более 100 записей
     * Если в строке нет последних полей, они заполняются пустыми строками
     * Метод синхронизирован, чтобы одновременно он мог быть вызван только одним пользователем
     *
     * @param filePart импортированный через форму файл
     * @throws IOException
     */
    public synchronized static void importODKU(Part filePart) throws IOException {

        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.WhichFactory.MYSQL);
        if (factory == null) return;
        ContactDao contactDao = factory.getContactDao();

        String line;
        BufferedReader br;
        List<Contact> list = new ArrayList<>();
        br = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

        /** Выполняем чтение построчно */
        while ((line = br.readLine()) != null) {
            Contact contact = new Contact();
            ArrayList<String> contactLine = new ArrayList<>(Arrays.asList(line.split(",")));

            /** Дополняем пустыми строками при необходимости */
            while (contactLine.size()<5) contactLine.add("");

            contact.setLogin(contactLine.get(0));
            contact.setName(contactLine.get(1));
            contact.setSurname(contactLine.get(2));
            contact.setEmail(contactLine.get(3));
            try {
                contact.setPhoneNumber(Long.parseLong(contactLine.get(4)));
            } catch (NumberFormatException nfe) {}

            /** Проверка на наличие "ключа" - поля login */
            if (!contact.getLogin().isEmpty()) list.add(contact);

            /** Если массив набрал 100 записей - вызываем метод всавки в БД */
            if (list.size() == 100){
                contactDao.insertContacts(list);
                list.clear();
            }
        }

        if (list.size() > 0){
            contactDao.insertContacts(list);
        }
    }
}
