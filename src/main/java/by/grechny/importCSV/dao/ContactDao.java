package by.grechny.importCSV.dao;

import by.grechny.importCSV.dto.Contact;

import java.util.List;

/**
 * ��������� ��� �������������� � �������� Contacts
 */
public interface ContactDao {

    /**
     * ������� ����� ������� �� �� � ������ ����������
     *
     * @param start ��������� �������� ������
     * @param count ���������� �����, ������� ���������� ���������
     * @param orderBy �������, �� ������� ���������� �����������
     * @return List c ����������� �������� � null, ���� ������ �� �������
     */
    List<Contact> selectContacts(int start, int count, String orderBy);

    /**
     * ����� ��� ��������� ������ ���������� ����� � �������
     *
     * @return Integer c ����������� �����
     */
    Integer selectCount();

    /**
     * ����� ��� ������� ������� � ��. ����� ��������� ����� ��������� �����
     * � ������, ���� ������ ���� ��� ����������, �������� ������
     *
     * @param list ������ ����� ��� �������
     * @return ������������� �����, ���� ������ ���� ���������
     */
    Integer insertContacts(List<Contact> list);

}
