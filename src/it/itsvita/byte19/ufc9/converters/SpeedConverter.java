package it.itsvita.byte19.ufc9.converters;

public class SpeedConverter {
	
	private float kmh_per_meters;
	private final float KMH_PER_METERS = 3.6f;
	
	public SpeedConverter()
	{
		this(0f);
	}
	
	public SpeedConverter(float kmh_per_meters)
	{
		
		if(kmh_per_meters <= 0f)
			kmh_per_meters = KMH_PER_METERS;
		
		this.kmh_per_meters = kmh_per_meters;
	}
	
	
	public float convertMeterSToKmS(float meters)
	{
		return meters * kmh_per_meters;
	}
	
	
	public float convertKmHToMeterS(float kmh)
	{
		return kmh / kmh_per_meters;
	}

	
}
