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
import java.sql.*;

@WebServlet(urlPatterns = "/consulta4")
public class Consulta4 extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection(context.getInitParameter("dbUrl"),
                            context.getInitParameter("dbUser"),
                            context.getInitParameter("dbPassword"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT f.id, CAST(SUM(fd.cantidad * p.costo) AS DECIMAL(12, 2)) total, \n" +
                    "\t   CAST(SUM( (fd.cantidad * p.costo) * 0.1 ) AS DECIMAL(12, 2)) iva\n" +
                    "FROM factura f\n" +
                    "\tJOIN factura_detalle fd ON f.id = fd.factura_id\n" +
                    "\tJOIN producto p ON fd.producto_id = p.id\n" +
                    "GROUP BY f.id\n" +
                    "ORDER BY total DESC;");

            String texto = consultarClientes(rs);

            stmt.close();
            rs.close();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("mensajeError.html");
            rd.include(request, response);
        }
    }

    private String consultarClientes(ResultSet res) throws SQLException {
        String texto = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Clientes</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n" +
                "    <link rel=\"stylesheet\" href=\"estilos/style.css\">" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Mostrar el iva 10% de los montos totales de facturas</h1>\n" +
                "\n" +
                "    <table class=\"table\">\n" +
                "        <thead>\n" +
                "          <tr>\n" +
                "            <th scope=\"col\">#</th>\n" +
                "            <th scope=\"col\">Total</th>\n" +
                "            <th scope=\"col\">IVA</th>\n" +
                "          </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n";

        while(res.next()) {
            texto += "          <tr>\n" +
                    "            <th scope=\"row\">" + res.getInt("id") + "</th>\n" +
                    "            <td>" + res.getString("total") + "</td>\n" +
                    "            <td>" + res.getString("iva") + "</td>\n" +
                    "          </tr>\n";
        }
        texto += "        </tbody>\n" +
                "      </table>\n" +
                "</br>\n" +
                "<p>by: Alexis Ortiz</p>\n" +
                "</body>\n" +
                "</html>";

        //System.out.println(texto);

        return texto;
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
