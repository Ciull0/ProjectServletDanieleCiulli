package it.itsvita.byte19.ufc9.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.itsvita.byte19.ufc9.database.DatabaseAccess;
import it.itsvita.byte19.ufc9.utils.Utils;

/**
 * Servlet implementation class LoginHttpServlet
 */
@WebServlet("/LoginHttpServlet")
public class LoginHttpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    //INIT
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter(Utils.PARAM_USERNAME);
		String password = request.getParameter(Utils.PARAM_PASSWORD);
		HttpSession session = request.getSession();
		String lastUserString = null;
		Cookie lastUserCookie = null;
		
		//Ottengo database
		DatabaseAccess database= Utils.loadDatabaseAccess(getServletContext());
		
		if( database.isNameDefined(name) ) {	//è registrato
			
			if( database.isPasswordVerified(name, password)) {	//Nome e Password Corrette
				
				session.setAttribute(Utils.PARAM_USERNAME, name);
				request.setAttribute(Utils.PARAM_PASSWORD, password);
				
			}else {	//Nome e Password Errati
				
				request.setAttribute("wrongPassword", "true");
				session.setAttribute(Utils.PARAM_INVALIDATE, "true");
				request.getRequestDispatcher("index.jsp").forward(request, response);	//Rimando a pagina login successo
				return;
				
			}
			
		}else {	//Utente Non Registrato
			
			if(name != null && password != null && !(name.equals("")) && !(password.equals("")) ) {
				
				session.setAttribute(Utils.PARAM_USERNAME, name);
				request.setAttribute(Utils.PARAM_PASSWORD, password);
				
			}else {
				
				if( database.addName(name, password) ) {
					
					session.setAttribute(Utils.PARAM_USERNAME, name);
					request.setAttribute(Utils.PARAM_PASSWORD, password);
					
				}else {
					
					request.setAttribute("wrongPassword", "true");
					session.setAttribute(Utils.PARAM_INVALIDATE, "true");
					request.getRequestDispatcher("index.jsp").forward(request, response);	//Rimando a pagina login successo
					return;
				}
			}
			
		}
        
       
        
        session.removeAttribute(Utils.PARAM_INVALIDATE);
        request.removeAttribute("wrongPassword");
        
        
     // Load the request cookies from the client browser
	    Cookie requestCookies[] = request.getCookies();
	 	
	 	// Check if cookie is in the request cookies
	 	if(requestCookies != null && requestCookies.length > 0)
	 	{
	 		for (int i = 0; i < requestCookies.length; i++)
	 		{
	 			if(requestCookies[i].getName().equals(Utils.PARAM_LAST_USER))
	 			{
	 				lastUserCookie = requestCookies[i];
	 				break;
	 			}
	 		}
	 	}
	 	
	 	// If the last login cookie is still undefined, create a new, otherwise update it and save its value for the view
	 	if (lastUserCookie == null)
	 	{
	 		lastUserCookie = new Cookie(Utils.PARAM_LAST_USER, name);	
	 	}
	    else
	    {
	        lastUserString = lastUserCookie.getValue();	//salva vecchio utente in stringa
	        lastUserCookie.setValue(name);	//mette utente attuale
	    }
	 	
	 	// Send the cookie to the client browser (expires in two hours)!
	 	lastUserCookie.setMaxAge(60 * 60 * 24 * 2);
	    response.addCookie(lastUserCookie);
	    
	    // Add last login date as attribute to the session
	    session.setAttribute(Utils.PARAM_LAST_USER, lastUserString);	//vecchio utente come attributo di sessione
        
        
        //reindirizzamento verso login.jsp
        
        request.getRequestDispatcher("login.jsp").forward(request, response);	//Rimando a pagina login successo

	}

}
