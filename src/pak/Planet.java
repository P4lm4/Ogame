package pak;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Planet
{
	
	public final int MAX_BUILDING_LVL = 19;
	private int id;
	private String name;
	private int positionX;
	private int positionY;
	private ResourceAmount resource;
	private Fleet ships;
	private int resourceTimer = 0;
	private Building conBuilding;
	private Ship conShip;
	private int conShipCount;
	private int conBuildingTimer;
	private int conShipTimer;
	private Player owner;
	private Universe universe;
	
	private HashMap<Building, Integer> planetBuildings = new HashMap<Building, Integer>();
	private ArrayList<Flight> incomingFlights = new ArrayList<Flight>();
	private ArrayList<Flight> outgoingFlights = new ArrayList<Flight>();

	public Planet(Universe universe, int id, String name, ResourceAmount resource, int positionX, int positionY)
	{
		this.universe = universe;
		this.id = id;
		this.name = name;
		this.resource = resource;
		this.positionX = positionX;
		this.positionY = positionY;
		this.ships = new Fleet();
	}
	
	public Planet(JSONObject json, Universe universe)
	{
		this.universe = universe;
		
		id = json.getInt("id");
		name = json.getString("name");
		positionX = json.getInt("positionX");
		positionY = json.getInt("positionY");
		resource = new ResourceAmount(json.getJSONObject("resource"));
		ships = new Fleet(json.getJSONObject("ships"), universe);
		resourceTimer = json.getInt("resourceTimer");
		if(json.has("conBuilding"))
		{
			conBuilding = BuildingIndex.getInstance().getById(json.getString("conBuilding"));
		}
		if(json.has("conShip"))
		{
			conShip = ShipIndex.getInstance().getById(json.getString("conShip"));
		}
		conShipCount = json.getInt("conShipCount");
		conBuildingTimer = json.getInt("conBuildingTimer");
		conShipTimer = json.getInt("conShipTimer");
		if(json.has("owner"))
		{
			owner = universe.getPlayer(json.getInt("owner"));
		}
		
		JSONObject buildings = json.getJSONObject("buildings");
		for(String buildingId : buildings.keySet())
		{
			Building b = BuildingIndex.getInstance().getById(buildingId);
			planetBuildings.put(b, buildings.getInt(buildingId));
		}
		
		JSONArray flights = json.getJSONArray("outgoingFlights");
		for(int i = 0; i < flights.length(); i++)
		{
			outgoingFlights.add(new Flight(flights.getJSONObject(i), universe));
		}
	}
	
	public void resolveFlights()
	{
		for(Flight f : outgoingFlights)
		{
			f.resolvePlanets();
			f.getEndPlanet().incomingFlights.add(f);
		}
	}
	
	public JSONObject serializeToJson()
	{
		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("name", name);
		json.put("positionX", positionX);
		json.put("positionY", positionY);
		json.put("resource", resource.serializeToJson());
		json.put("ships", ships.serializeToJson());
		json.put("resourceTimer", resourceTimer);
		if(conBuilding != null)
		{
			json.put("conBuilding", conBuilding.getId());
		}
		if(conShip != null)
		{
			json.put("conShip", conShip.getId());
		}
		json.put("conShipCount", conShipCount);
		json.put("conBuildingTimer", conBuildingTimer);
		json.put("conShipTimer", conShipTimer);
		if(owner != null)
		{
			json.put("owner", owner.getId());
		}
		
		JSONObject buildings = new JSONObject();
		
		for(Building b : planetBuildings.keySet())
		{
			buildings.put(b.getId(), planetBuildings.get(b));
		}
		json.put("buildings", buildings);
		
		JSONArray outgoingFlightsArray = new JSONArray();
		
		for(Flight f : outgoingFlights)
		{
			outgoingFlightsArray.put(f.serializeToJson());
		}
		
		json.put("outgoingFlights", outgoingFlightsArray);
		
		return json;
		
	}
	
	public Flight newFlight(Planet endPlanet, Fleet fleet, ResourceAmount flightResource)
	{
		if(endPlanet == this)
		{
			System.out.println("Ista planeta.");
			return null;
		}
		/*if(!owner.listOfKnownPlanets.contains(endPlanet))
		{
			System.out.println("Planeta ne postoji na spisku otrkivenih planeta. ");
			return null;
		}
		*/
		if(!fleet.isLessOrEqual(ships))
		{
			System.out.println("Da nema dovoljno flote");
			return null;
		}
		
		if(flightResource.getSumResource() > fleet.getMaxCapacity())
		{
			System.out.println("Nema dovoljno kapaciteta flota za resurse.");
			return null;
		}
		
		if(!resource.has(flightResource))
		{
			System.out.println("Nema dovoljno resursa na planeti.");
			return null;
		}
		
		//POLIJECEMO
		
		resource.remove(flightResource);
		ships.remove(fleet);
		fleet.setOwner(owner);
		
		Flight flight = new Flight(fleet,this,endPlanet,flightResource);
		
		this.outgoingFlights.add(flight);
		endPlanet.incomingFlights.add(flight);
		
		return flight;
		
		
	}
	
	public ConstructionResult constructBuilding(Building building)
	{
		int targetLvl = this.getBuildingLvl(building) + 1;
		
		if(targetLvl > MAX_BUILDING_LVL)
		{
			return ConstructionResult.MAX_LVL;
		}
		
		ResourceAmount price = building.getPriceForLvl(targetLvl);
		
		if(!this.resource.has(price))
		{
			return ConstructionResult.NO_RESOURCE;
		}
		if(conBuilding != null)
		{
			return ConstructionResult.ALREADY_CONSTRUCTING;
		}
		
		resource.remove(price);
		conBuilding = building;
		conBuildingTimer = building.getConstractionTimer();
		
		return ConstructionResult.OK;
		
		
	}
	
	public ConstructionResult constructShip(Ship ship, int count)
	{
		ResourceAmount price = ship.getPrice();
		
		if(!this.resource.has(price, count))
		{
			return ConstructionResult.NO_RESOURCE;
		}
		if(conShip != null)
		{
			return ConstructionResult.ALREADY_CONSTRUCTING;
		}
		
		resource.remove(price, count);
		conShip = ship;
		conShipCount = count;
		conShipTimer = ship.getConstructionTimer();
		
		return ConstructionResult.OK;
	}
	
	public double distanceTo(Planet planet)
	{
		
		double dx = positionX - planet.getPositionX();
		double dy = positionY - planet.getPositionY();
		
		double distance = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
		
		return distance;
		
	}
	
	public int getBuildingLvl(Building other)
	{
		if(planetBuildings.containsKey(other))
		{
			return planetBuildings.get(other);
		}
		else
		{
			return -1;
		}
	}
	
	public Planet addBuilding(Building building)
	{
		if(planetBuildings.containsKey(building))
		{
			int prevCount = planetBuildings.get(building);
			planetBuildings.replace(building, prevCount + 1);
		}
		else
		{
			planetBuildings.put(building, 0);
		}
		
		return this;
	}
	
	public Planet removeFleet(Fleet fleet)
	{
		ships.remove(fleet);
		
		return this;
	}
	
	public void tick()
	{
		/*U foreach petljama ne smije se lista mjenjati dok se prolazi kroz nju (dodavati ili oduzimati elemnte)
		 * Let u svojoj tick funkciji moze da sleti i onda ce da se brise iz incomingFlight liste.
		 * Zato nam treba for petlja unazad da nebi doslo do preskakanja elemenata pri brisanju iz liste.
		 */
		for(int i = incomingFlights.size()-1; i >= 0; i--)
		{
			incomingFlights.get(i).tick();
		}
		
		resourceTimer++;
		
		if(resourceTimer >= 10)
		{
			resourceTimer -= 10;
			
			if(getEnergyProduction() >= getEnergyConsumption())
			{
				updateResource();
			}		
			
		}
		
		if(conBuilding != null)
		{
			conBuildingTimer--;
			
			if(conBuildingTimer <= 0)
			{
				addBuilding(conBuilding);
				
				System.out.println("Na planeti " + name + " napravila se zgrada " + conBuilding.getName());
				
				conBuilding = null;
			}
		}
		
		if(conShip != null)
		{
			conShipTimer--;
			
			if(conShipTimer <= 0)
			{
				ships.addShip(conShip, 1);
				conShipCount--;
				
				if(conShipCount > 0)
				{
					conShipTimer = conShip.getConstructionTimer();
				}
				else
				{
					System.out.println("Na planeti " + name + " napravljen je " + conShip.getName());
					conShip = null;
				}
			}			
		}
		
		
	}
	
	public int getEnergyConsumption()
	{
		int energyConsumption = 0;
		
		for(Building building : planetBuildings.keySet())
		{
			
			int totalEnergy = building.getEnergyPetLvl(planetBuildings.get(building));
			
			if(totalEnergy < 0)
			{
				energyConsumption -= totalEnergy;
			}
		}
		
		return energyConsumption;
	}
	
	public int getEnergyProduction()
	{
		int energyProduction = 0;
		
		for(Building building : planetBuildings.keySet())
		{
			int totalEnergy = building.getEnergyPetLvl(planetBuildings.get(building));
			
			if(totalEnergy > 0)
			{
				energyProduction += totalEnergy;
			}
		}
		
		return energyProduction;
	}
	
	
	
	private void updateResource()
	{
		
		for(Building building : planetBuildings.keySet())
		{
					
			building.produce(this, planetBuildings.get(building));
		
		}
		
	}
	
	public String toString()
	{
		String result = " [ " + name + " " + "X: " + positionX + " Y: " + positionY + " ";
		
		result += resource + " [ENERGY: " + " -" + this.getEnergyConsumption() + " / " + "+" + this.getEnergyProduction();
		
		result += " ] ";
		
		return result;
	}
	
	public Universe getUniverse()
	{
		return universe;
	}
	
	public void setOwner(Player ownerPlanet)
	{
		this.owner = ownerPlanet;
	}
	
	public ResourceAmount getResource()
	{
		return resource;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPositionX()
	{
		return positionX;
	}
	
	public int getPositionY()
	{
		return positionY;
	}
	
	public Player getOwner()
	{
		return owner;
	}
	
	public Fleet getShips()
	{
		return ships;
	}
	
	public int getSumOfDefDamage()
	{
		return ships.getSumDamage();
	}
	
	public ArrayList<Flight> getOutgoingFlights()
	{
		return outgoingFlights;
	}
	
	public ArrayList<Flight> getIncomingFlight()
	{
		return incomingFlights;
	}
	
	public Building getBuildingInConstruction()
	{
		return conBuilding;
	}
	
	public int getBuildingConstructionTimer()
	{
		return conBuildingTimer;
	}
	
	public Ship getShipInConstruction()
	{
		return conShip;
	}
	
	public int getShipConstructionTimer()
	{
		return conShipTimer;
	}
	
	public int getConShipCount()
	{
		return conShipCount;
	}
	
	public int getfullConShipTimer()
	{		
		if(conShip != null)
		{
			return (conShipCount - 1) * conShip.getConstructionTimer() + conShipTimer;
		}
		else 
		{
			return 0;
		}
	}
	

}
