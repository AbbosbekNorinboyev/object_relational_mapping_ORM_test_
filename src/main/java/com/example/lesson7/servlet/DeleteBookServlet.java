package com.example.lesson7.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "com.example.lesson7.servlet.DeleteBookServlet",
        value = "/delete/book"
)
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        Books books = entityManager.find(Books.class, id);
//        entityManager.remove(books);

        Query query = entityManager.createQuery("delete Books b where b.id = :id").setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
