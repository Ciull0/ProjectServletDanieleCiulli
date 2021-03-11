package it.itsvita.byte19.ufc9.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.itsvita.byte19.ufc9.utils.Utils;

/**
 * Servlet implementation class LogoutHttpFilter
 */
@WebServlet("/LogoutHttpFilter")
public class LogoutHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
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
		
		try
        {
            // Invalidate the currently existing session by setting a specific attribute
      // Note: JSP is creating new sessions for us, so we cannot just check for null in filters
            HttpSession session = request.getSession(false);	//prende sessione senza crearne una nuova
            
            if (session != null) {
             session.setAttribute(Utils.PARAM_INVALIDATE, "true");
            }
            
            // Redirect to login page
	      request.getRequestDispatcher("index.jsp").forward(request, response);	//rimanda al login
	      return;
                         
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }

		
	}

}
