package it.itsvita.byte19.ufc9.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class ParamLoggerFilter
 */
@WebFilter("/ParamLoggerFilter")
public class ParamLoggerFilter implements Filter {

	private ServletContext context;
    
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;    
        Enumeration<String> params = httpRequest.getParameterNames();   
        
        while(params.hasMoreElements())
        {
            String name = params.nextElement();
            String value = request.getParameter(name);
            context.log("From --> " + httpRequest.getRemoteAddr() + ", Parameter --> "+name+" = "+value);
        }
		
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
