package pak;

import org.json.JSONObject;

public class Flight
{
	private final int SPEED_CONSTANT = 300;
	private Fleet fleet;
	private Planet startPlanet;
	private Planet endPlanet;
	private ResourceAmount resource;
	private int timeOfFlight;
	
	private int startPlanetId;
	private int endPlanetId;
	private Universe universe;

	
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
	
	public Flight(JSONObject json, Universe universe)
	{
		this.universe = universe;
		fleet = new Fleet(json.getJSONObject("fleet"), universe);
		startPlanetId = json.getInt("startPlanet");
		endPlanetId = json.getInt("endPlanet");
		resource = new ResourceAmount(json.getJSONObject("resource"));
		timeOfFlight = json.getInt("timeOfFlight");
	}
	
	public void resolvePlanets()
	{
		startPlanet = universe.getPlanet(startPlanetId);
		endPlanet = universe.getPlanet(endPlanetId);
	}
	
	public JSONObject serializeToJson()
	{
		JSONObject json = new JSONObject();
		
		json.put("fleet", fleet.serializeToJson());
		json.put("startPlanet", startPlanet.getId());
		json.put("endPlanet", endPlanet.getId());
		json.put("resource", resource.serializeToJson());
		json.put("timeOfFlight", timeOfFlight);
		
		return json;
		
	}
	
	public void tick()
	{
		timeOfFlight--;
		if(timeOfFlight == 0)
		{
			System.out.println("Flota igraca " + fleet.getOwner() +  " (" + fleet + ") je stigla na planetu " + endPlanet + " vlasnika " + endPlanet.getOwner() + " sa flotom " + endPlanet.getShips());
			startPlanet.getOutgoingFlights().remove(this);
			endPlanet.getIncomingFlight().remove(this);
			
			/*U ovome dijelu ce biti fight i ostale stvari pri sletanju flote na endPlanet*/
			
			if(fleet.getOwner() != endPlanet.getOwner())
			{
				if(Fight.attackPlanet(fleet, endPlanet))
				{
					fleet.getOwner().takePlanet(endPlanet);
					System.out.println("Igrac " + fleet.getOwner() + " je pobjedio sa preostalom flotom " + fleet + " i osvaja planetu " + endPlanet.getName());
				}
				else
				{
					System.out.println("Igrac " + fleet.getOwner() + " je izgubio flotu u neuspjesnom napadu na planetu " + endPlanet.getName());
					return;
				}
			}
			
			endPlanet.getShips().add(fleet);
			endPlanet.getResource().add(resource);
			System.out.println("Igraj " + fleet.getOwner() + " je istovario " + resource + " na planetu " + endPlanet.getName());
			
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
