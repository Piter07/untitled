package com.roshka.bootcamp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

@WebServlet("/cliente")
public class AgregarCliente extends HttpServlet {

    Connection connection;

    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();

        //mostramos los parametros del contexto
        Enumeration<String> attributeNames = context.getInitParameterNames();

        while(attributeNames.hasMoreElements()) {
            String eachName = attributeNames.nextElement();
            System.out.println("Context Param name: " + eachName);
        }

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(context.getInitParameter("dbUrl"), context.getInitParameter("dbUser"), context.getInitParameter("dbPassword"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String ci = request.getParameter("ci");
        String tel = request.getParameter("tel");

        System.out.println(id + " " + nombre + " " + apellido + " " + ci + " " + tel);
        RequestDispatcher view = request.getRequestDispatcher("/consulta");
        view.forward(request, response);
       // response.sendRedirect("/ConsultaBD/consulta");

        if (this.connection == null) return;
        String linea = "insert into cliente values ('" + id + "','" + nombre + "','" + apellido + "','" + ci + "','" + tel + "');";
        try {
            Statement tmt = connection.createStatement();
            ResultSet rs = tmt.executeQuery(linea);
            tmt.close();
            rs.close();

        } catch (Exception e) {

            return;

        }



    }
}
