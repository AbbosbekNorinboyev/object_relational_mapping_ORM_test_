package com.example.lesson7.servlet;

import com.example.lesson7.entity.Books;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(
        name = "com.example.lesson7.servlet.GetId",
        value = "/get/id"
)
public class GetId extends HttpServlet {
    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Books> query = entityManager.createQuery("select b from Books b where b.id = :id", Books.class)
                .setParameter("id", id);
        Books singleResult = query.getSingleResult();
//        Books books = entityManager.find(Books.class, id);
        entityManager.getTransaction().commit();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .setPrettyPrinting()
                .create();
        System.out.println("singleResult: " + singleResult);
        resp.getWriter().println(gson.toJson(singleResult));
    }
}
