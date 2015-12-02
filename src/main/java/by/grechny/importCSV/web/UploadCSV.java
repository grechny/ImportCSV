package by.grechny.importCSV.web;

import by.grechny.importCSV.core.Import;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Сервлет для загрузки файла
 */
@MultipartConfig
@WebServlet(name = "UploadCSV", urlPatterns={"/upload"})
public class UploadCSV extends HttpServlet {

    /**
     * Получение файла из формы и передача его в метод для импорта в БД
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        Import.importODKU(filePart);
        getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
    }
}
