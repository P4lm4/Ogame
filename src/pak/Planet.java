package pak;

import java.util.HashMap;

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
	private int constructionTimer;
	private Player owner;
	


	public Planet(int id, String name, ResourceAmount resource, int positionX, int positionY)
	{
		this.id = id;
		this.name = name;
		this.resource = resource;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	HashMap<Building, Integer> planetBuildings = new HashMap<Building, Integer>();
	
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
		constructionTimer = building.getConstractionTimer();
		
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
	
	public void tick()
	{
		
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
			constructionTimer--;
			
			if(constructionTimer <= 0)
			{
				addBuilding(conBuilding);
				
				System.out.println("Na planeti " + name + " napravila se zgrada " + conBuilding.getName());
				
				conBuilding = null;
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
	

}
