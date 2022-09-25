package com.servlet.example;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/encrypt");
        request.setAttribute("lname", request.getParameter("lname"));
        request.setAttribute("fname", request.getParameter("fname"));
        ServletContext applicationScope = request.getServletContext();
        applicationScope.setAttribute("dbConnection", "jdbc:derby:student_db");
        dispatcher.forward(request, response);
    }

}
