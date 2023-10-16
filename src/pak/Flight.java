package pak;

public class Flight
{
	private final int SPEED_CONSTANT = 300;
	private Fleet fleet;
	private Planet startPlanet;
	private Planet endPlanet;
	private ResourceAmount resource;
	private int timeOfFlight;

	
	public Flight(Fleet fleet, Planet startPlanet, Planet endPlanet, ResourceAmount resource)
	{
		
		this.fleet = fleet;
		this.startPlanet = startPlanet;
		this.endPlanet = endPlanet;
		this.resource = resource;
		
		int fleetSpeed = fleet.getMaxSpeed();
		double distance = startPlanet.distanceTo(endPlanet);
		timeOfFlight = (int)((distance * SPEED_CONSTANT)/fleetSpeed);
		
		if(timeOfFlight == 0)
		{
			timeOfFlight = 1;
		}
		
	}
	
	public void tick()
	{
		timeOfFlight--;
		if(timeOfFlight == 0)
		{
			System.out.println("Flota je stigla na planetu " + endPlanet);
			startPlanet.getOutgoingFlights().remove(this);
			endPlanet.getIncomingFlight().remove(this);
			
			/*U ovome dijelu ce biti fight i ostale stvari pri sletanju flote na endPlanet*/
			
			
			
		}
	}
	
	
	public Fleet getFleet()
	{
		return fleet;
	}
	
	public Planet getStartOfPlanet()
	{
		return startPlanet;
	}
	
	public Planet getEndPlanet()
	{
		return endPlanet;
	}
	
	public ResourceAmount getResource()
	{
		return resource;
	}
	
	public int getTimeOfFlight()
	{
		return timeOfFlight;
	}

}
