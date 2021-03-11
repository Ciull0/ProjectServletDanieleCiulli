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
 * Servlet Filter implementation class LoggedFilter
 */
@WebFilter("/LoggedFilter")
public class LoggedFilter implements Filter {
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        // If we are logged in (checking the session), change the response by redirecting to login.jsp
        if(session != null && session.getAttribute(Utils.PARAM_INVALIDATE) == null)
        {
            context.log("Signore, lei è già Loggato, la accompagno alla sua Pagina");
            httpResponse.sendRedirect("./login.jsp");
        }
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
