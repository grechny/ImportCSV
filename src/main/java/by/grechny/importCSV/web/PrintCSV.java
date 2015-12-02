package by.grechny.importCSV.web;

import by.grechny.importCSV.dao.ContactDao;
import by.grechny.importCSV.dao.DaoFactory;
import by.grechny.importCSV.dto.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * —ервлет дл€ работы со страницей просмотра записей из Ѕƒ
 */
@WebServlet(name = "PrintCSV", urlPatterns={"/print"})
public class PrintCSV extends HttpServlet {

    /**
     * ¬ данном методе реализовываетс€ получение необходимых строк дл€ отображени€,
     * а так же рассчитываютс€ параметры начальной строки, количества отображаемых строк
     * и необходима€ сортировка. ¬се данные передаютс€ дл€ вывода в JSP страницу
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.WhichFactory.MYSQL);
        List<Contact> contactList = new ArrayList<>();

        String startParam = request.getParameter("start");
        String countParam = request.getParameter("count");
        String orderByParam = request.getParameter("orderBy");

        int start = 0, count = 10, countAll = 0;
        String orderBy = "login";

        if (startParam != null) start = Integer.parseInt(startParam);
        if (countParam != null && Integer.parseInt(countParam) > 0
                && Integer.parseInt(countParam) <= 100) count = Integer.parseInt(countParam);
        if (orderByParam != null && (orderByParam.equals("name")
                || orderByParam.equals("surname")
                || orderByParam.equals("email")
                || orderByParam.equals("phone_number"))) orderBy = orderByParam;

        ContactDao contactDao = null;
        if (daoFactory != null) contactDao = daoFactory.getContactDao();

        if (contactDao != null) {
            contactList = contactDao.selectContacts(start, count, orderBy);
            countAll = contactDao.selectCount();
        }

        request.setAttribute("contactList", contactList);
        request.setAttribute("start", start);
        request.setAttribute("count", count);
        request.setAttribute("countAll", countAll);
        request.setAttribute("orderBy", orderBy);

        request.getRequestDispatcher("/print.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
