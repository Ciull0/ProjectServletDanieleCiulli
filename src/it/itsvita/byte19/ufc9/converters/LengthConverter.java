package it.itsvita.byte19.ufc9.converters;

public class LengthConverter {
	
	private float metersPerMile;
	private final float METERS_PER_MILE = 1609.34f;
	
	public LengthConverter()
	{
		this(0f);
	}
	
	public LengthConverter(float mpm)
	{
		
		if(mpm <= 0f)
			mpm = METERS_PER_MILE;
		
		metersPerMile = mpm;
	}
	
	
	public float convertMetersToMiles(float meters)
	{
		return meters / metersPerMile;
	}
	
	
	public float convertMilesToMeters(float miles)
	{
		return metersPerMile * miles;
	}

}
