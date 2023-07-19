package pak;

import java.util.HashMap;

public class Planet
{
	
	String name;
	ResourceAmount resource;
	Fleet ships;
	int resourceTimer = 0;
	
	public Planet(String name, ResourceAmount resource)
	{
		this.name = name;
		this.resource = resource;
	}
	
	HashMap<Building, Integer> planetBuildings = new HashMap<Building, Integer>();
	
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
			
			updateResource();
			
		}
		
		
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
		
		result += resource;
		
		result += " ] ";
		
		return result;
	}
	
	public ResourceAmount getResource()
	{
		return resource;
	}
	

}
