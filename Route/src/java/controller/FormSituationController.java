package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Situation;

public class FormSituationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] idpkString = request.getParameterValues("idpk");
        String[] id_degatString = request.getParameterValues("id_degat");
        String argent = request.getParameter("argent");
        Timestamp date_now = Timestamp.valueOf(LocalDateTime.now());
        Situation situation = new Situation();
        try {
            for (int i = 0; i < idpkString.length; i++) {
                String id_degat = id_degatString[i]; 
                String idpk = idpkString[i];
                situation.setDate_situation(date_now);
                situation.setId_degat(Integer.parseInt(id_degat));
                situation.setIdpk(Integer.parseInt(idpk));
                situation.update();
            }
        }catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
        request.setAttribute("argent", Double.parseDouble(argent));
        RequestDispatcher dispatcher = request.getRequestDispatcher("type.jsp");
        dispatcher.forward(request, response);
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
