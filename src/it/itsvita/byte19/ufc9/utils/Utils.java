package it.itsvita.byte19.ufc9.utils;

import javax.servlet.ServletContext;

import it.itsvita.byte19.ufc9.database.DatabaseAccess;

public class Utils {
	
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_DATE = "currentDate";
    public static final String PARAM_DATABASE_ACCESS = "databaseAccess";
    public static final String PARAM_WRONG_PASSWORD = "wrongPassword";
    public static final String PARAM_LAST_USER = "lastUser";
    public static final String PARAM_INVALIDATE = "invalidate";
    public static final String CONV_METERS_TO_MILES = "metes_to_miles";
    public static final String CONV_MILES_TO_METERS = "miles_to_meters";
    public static final String CONV_KMH_TO_MS = "kmh_to_ms";
    public static final String CONV_MS_TO_KMH = "ms_to_kmh";

    //Singleton con Contesto
    public static DatabaseAccess loadDatabaseAccess(ServletContext servletContext)
    {
        DatabaseAccess databaseAccess = (DatabaseAccess)servletContext.getAttribute(PARAM_DATABASE_ACCESS);
        
        if(databaseAccess == null)
        {
            databaseAccess = new DatabaseAccess(); 
            servletContext.setAttribute(PARAM_DATABASE_ACCESS, databaseAccess);
        }
        
        return databaseAccess;
    }

	
}
