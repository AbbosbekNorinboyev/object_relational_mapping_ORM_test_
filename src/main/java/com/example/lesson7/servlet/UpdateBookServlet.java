package com.example.lesson7.servlet;

import com.example.lesson7.entity.Books;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson7.servlet.UpdateBookServlet",
        value = "/update/book"
)
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String page = req.getParameter("page");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Books books = entityManager.find(Books.class, id);
        if (Objects.isNull(books)) {
            resp.sendError(404, "Book with id '%s' not found".formatted(id));
        } else {
            books.setTitle(Objects.requireNonNullElse(title, books.getTitle()));
            books.setPage(Integer.parseInt(Objects.requireNonNullElse(page, "" + books.getPage())));
            entityManager.merge(books);
        }
        entityManager.getTransaction().commit();
    }
}
