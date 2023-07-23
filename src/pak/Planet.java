package pak;

import java.util.HashMap;

public class Planet
{
	
	private String name;
	private ResourceAmount resource;
	Fleet ships;
	int resourceTimer = 0;
	private Building conBuilding;
	private int constructionTimer;
	
	public Planet(String name, ResourceAmount resource)
	{
		this.name = name;
		this.resource = resource;
	}
	
	HashMap<Building, Integer> planetBuildings = new HashMap<Building, Integer>();
	
	public ConstructionResult constructBuilding(Building building)
	{
		if(!this.resource.has(building.getPrice()))
		{
			return ConstructionResult.NO_RESOURCE;
		}
		if(conBuilding != null)
		{
			return ConstructionResult.ALREADY_CONSTRUCTING;
		}
		
		building.payPrice(this, 0);
		conBuilding = building;
		constructionTimer = building.getConstractionTimer();
		
		return ConstructionResult.OK;
		
		
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
			if(building.getEnergy() < 0)
			{
				energyConsumption -= building.getEnergy();
			}
		}
		
		return energyConsumption;
	}
	
	public int getEnergyProduction()
	{
		int energyProduction = 0;
		
		for(Building building : planetBuildings.keySet())
		{
			if(building.getEnergy() > 0)
			{
				energyProduction += building.getEnergy();
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
		String result = " [ " + name + " ";
		
		result += resource + " [ENERGY: " + " -" + this.getEnergyConsumption() + " / " + "+" + this.getEnergyProduction();
		
		result += " ] ";
		
		return result;
	}
	
	public ResourceAmount getResource()
	{
		return resource;
	}
	

}
