
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author miguel
 */
public class Mecanismo {
    
    private String Nombre;
        private HttpServletResponse Usuario;
        
        Mecanismo(String n,HttpServletResponse u){
            this.Nombre=n;
            this.Usuario=u;
        }
        
        public String getNombre(){
         return this.Nombre;
        }
        
        public HttpServletResponse  getUsuario(){
         return this.Usuario;
        }
    
}
