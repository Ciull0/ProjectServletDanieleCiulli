package it.itsvita.byte19.ufc9.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.itsvita.byte19.ufc9.utils.Utils;

/**
 * Servlet Filter implementation class NotLoggedFilter
 */
@WebFilter("/NotLoggedFilter")
public class NotLoggedFilter implements Filter {
	
	ServletContext context;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
		context = fConfig.getServletContext();
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        String uri = httpRequest.getRequestURI();
        
        // If we are not logged in (checking the session or its specific attribute)), change the response by redirecting to index.jsp
        if( (session == null || session.getAttribute(Utils.PARAM_INVALIDATE) != null) && !uri.endsWith("index.jsp"))
        {
            context.log("ACCESSO NON AUTORIZZATO A MATERIALE RISERVATO BECCATO SIGNOR HACKER");
            httpResponse.sendRedirect("./index.jsp");
            return;
        }
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
