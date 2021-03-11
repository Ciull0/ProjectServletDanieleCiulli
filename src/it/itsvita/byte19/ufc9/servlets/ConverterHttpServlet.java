package it.itsvita.byte19.ufc9.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.itsvita.byte19.ufc9.converters.LengthConverter;
import it.itsvita.byte19.ufc9.converters.SpeedConverter;
import it.itsvita.byte19.ufc9.utils.Utils;

/**
 * Servlet implementation class ConverterHttpServlet
 */
@WebServlet("/ConverterHttpServlet")
public class ConverterHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LengthConverter lenConverter;
	SpeedConverter speedConverter;
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		lenConverter = new LengthConverter();
		speedConverter = new SpeedConverter();
		
		
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
		
		try {
			
			String conversionType = request.getParameter("conversion");
			Float toConvert = Float.parseFloat( request.getParameter("value") );
			
			if( conversionType.equals( Utils.CONV_METERS_TO_MILES) ) {
				
				String result = String.valueOf( lenConverter.convertMetersToMiles( toConvert ) );
				
				request.setAttribute("conversionResult", result);
				
			}else if( conversionType.equals( Utils.CONV_MILES_TO_METERS) ) {
				
				String result = String.valueOf( lenConverter.convertMilesToMeters( toConvert ) );
				
				request.setAttribute("conversionResult", result);
				
			}else if( conversionType.equals( Utils.CONV_KMH_TO_MS) ) {
				
				String result = String.valueOf( speedConverter.convertKmHToMeterS( toConvert ) );
				
				request.setAttribute("conversionResult", result);
				
			}else if( conversionType.equals( Utils.CONV_MS_TO_KMH) ) {
				
				String result = String.valueOf( speedConverter.convertMeterSToKmS( toConvert ) );
				
				request.setAttribute("conversionResult", result);
				
			}else {
				
				request.setAttribute("conversionResult", "INPUT NON VALIDO SELEZIONA CONVERSIONE");
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			request.setAttribute("conversionResult", "NON DOVRESTI ESSERE QUI");
			
		} finally {
			
			request.getRequestDispatcher("converter.jsp").forward(request, response);
			
		}

	}

}
