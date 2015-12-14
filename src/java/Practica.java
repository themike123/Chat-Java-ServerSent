/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/chat"})
public class Practica extends HttpServlet {
        
        //Aqui es donde se guardan los usuario en general
        private HashMap<String, HttpServletResponse> clients = new HashMap<>();
        
        //Aqui se guardan solo los nombres de los Canales
        public List list = new ArrayList();
        public List ayuda = new ArrayList();
        
        
        String cadena;
        
        //Aqui es donde se guardan los canales
        private HashMap<String, HttpServletResponse> canales = new HashMap<>();
        
        private HashMap<String, HttpServletResponse> gruposCanales = new HashMap<>();
        
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Practica</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Practica at " + request.getContextPath() + "</h1>");
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
       // processRequest(request, response);
                response.setContentType("text/event-stream; charset=utf-8");
        

        clients.put(UUID.randomUUID().toString(),response);
        
        while(true){
            try{
            Thread.sleep(5000);
            }catch(Exception e){}
        }
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
        //processRequest(request, response);
        
        String user=request.getParameter("user");
        String text=request.getParameter("text");
        String crearcanal=request.getParameter("crear"); 
        String grupo=request.getParameter("grupo");
        String Interr=request.getParameter("Inte");
        
        int varxx = 0;
        
        if(!Interr.equals("No")){
            //String nombre=request.getParameter("nombre");
            ayuda.add(grupo);
            if(ayuda.size()==0){
                gruposCanales.put( Integer.toString(0), response);    
            }else{
                gruposCanales.put( Integer.toString(ayuda.size() -1 ), response);    
            }
            
        }

        for(HttpServletResponse c: clients.values()){
            
            if(crearcanal.length()>0){
                if(varxx==0){
                list.add(crearcanal);
                c.getWriter().write("event: ping\n");
                c.getWriter().write("data: {\"type\":\"message\" ,\"canal\":\" "+list+"  \" }  \n\n");            
                c.getWriter().flush();
                }
            
            }else{
                
               if(varxx==0){
                    if(grupo.equals("No")){
                    String message1 = "data: {\"type\":\"message\", \"content\":\""+text+"\", \"user\":\""+user+"\"  }\n\n";
                    c.getWriter().write(message1);
                    c.getWriter().flush();  
               }else{
                   for(int i=0 ; i< ayuda.size(); i++){
                       if(grupo.equals(ayuda.get(i).toString())){
                           canales.put(UUID.randomUUID().toString(), gruposCanales.get(Integer.toString(i)));
                       }
                   }
                   
                   for(HttpServletResponse d: canales.values()){
                     String message2 = "data: {\"type\":\"message\", \"content\":\""+text+"\", \"user\":\""+user+"\"  }\n\n";
                     d.getWriter().write(message2);
                     d.getWriter().flush();  
                   }
                   varxx=1;
                   
               }
               }
                
            }
        
        }

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
