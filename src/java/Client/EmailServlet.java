/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entities.Mail;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "EmailServlet", urlPatterns = {"/EmailServlet"})
public class EmailServlet extends HttpServlet {

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        URL client;
        String email = request.getParameter("email");
        client = new URL("http://apilayer.net/api/check?access_key=8dec209336333136683a1b1525ebbfd6&email=" + email);
        URLConnection tc = client.openConnection(); //Conexion cliente 
        BufferedReader rd; //buffer para leer la respuesta del servidor
        rd = new BufferedReader(new InputStreamReader(tc.getInputStream()));
        String line; //variable para leer del buffer de la conexión
        String info = new String(); //respuesta del servicio web
        while ((line = rd.readLine()) != null) {
            info += line;
        }
        rd.close();
        Gson gson = new Gson();
        Mail mail = gson.fromJson(info, Mail.class);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmailServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1>Response: </h1>");
            out.println("<h1>User: " +mail.getUser() + "</h1>");
            out.println("<h1>Domain: " +mail.getDomain()+ "</h1>");
            out.println("<h1>Format Valid: " +mail.isFormat_valid()+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
