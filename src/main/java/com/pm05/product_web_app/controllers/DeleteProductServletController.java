package com.pm05.product_web_app.controllers;

import java.io.IOException;
import java.sql.Connection;

import com.pm05.product_web_app.models.Product;
import com.pm05.product_web_app.models.db.DBCrud;
import com.pm05.product_web_app.models.db.MySQLConnector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteProduct")
public class DeleteProductServletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get code from url query string
        String code = req.getParameter("code");

        // connect to DB

        Connection conn = MySQLConnector.getMySQLConnection();

        // remove Product by code
        Product product = DBCrud.removeProductByCode(conn, code);
        // close
        MySQLConnector.closeConnection(conn);

        // place product into request object
        req.setAttribute("product", product);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/DeleteProductView.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
