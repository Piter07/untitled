package com.roshka.bootcamp;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

@WebServlet("/formPro")
public class formPro extends HttpServlet {
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
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("pasa por aca uwuwuwu");
        try {
            Statement stmt = connection.createStatement();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            ResultSet rs = stmt
                    .executeQuery("SELECT id , nombre FROM proveedor ORDER BY id ASC ;");
            out.println("<!doctype html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <title>Bootstrap demo</title>\n" +
                    "\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n" +
                    "</head>\n" +
                    "<body class=\"p-5 mb-5 bg-info text-dark\" >\n" +
                    "<h1 class=\"h1\">PRODUCTO</h1>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2\" crossorigin=\"anonymous\"></script>\n" +
                    "<form method=\"post\" action=\"producto\">\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col\">\n" +
                    "            <label for=\"exampleInputEmail1\">Id</label>\n" +
                    "            <input name= \"id\" type=\"text\" class=\"form-control\">\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col\">\n" +
                    "            <label for=\"exampleInputEmail1\">Nombre</label>\n" +
                    "            <input name= \"nombre\" type=\"text\" class=\"form-control\">\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col\">\n" +
                    "            <label for=\"exampleInputEmail1\">Precio</label>\n" +
                    "            <input name= \"precio\" type=\"text\" class=\"form-control\">\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col\">\n" +
                    "\n" +
                    "\n" +
                    "            <label for=\"exampleInputEmail1\">Proveedor id</label>\n" +
                    "            <select  name=\"proveedor_id\" class=\"form-select\" aria-label=\"Default select example\">\n" +
                    "                <option selected> </option>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                out.println(" <option value= '" + id + " '>" + id +" - " + nombre + "</option>");

            }
            out.println(" </select>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col\">\n" +
                    "            <label for=\"exampleInputEmail1\">Costo</label>\n" +
                    "            <input name= \"costo\" type=\"text\" class=\"form-control\">\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "\n" +
                    "    <br>\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col\">\n" +
                    "            <div class=\"d-grid gap-2\">\n" +
                    "                <button class=\"btn btn-primary\" type=\"submit\" >Enviar</button>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "<script type=\"text/javascript\" src=\"form.js\" async></script>\n" +
                    "</html>");
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
