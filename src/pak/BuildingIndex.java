package pak;

import java.util.ArrayList;

public class BuildingIndex
{
	
	private ArrayList<Building> buildings = new ArrayList<>();
	
	private static BuildingIndex instance = null;
	
	private BuildingIndex()
	{
		buildings.add(new Building(
				/* id */				"mine_metal", 
				/* name */				"Metal Mine", 
				/* health */			1000,
				/* energy */			-100,
				/* production */		new ResourceAmount(ResourceType.IRON, 100).add(ResourceType.CRYSTAL, 0), 
				/* productionPerLvl */	new ResourceAmount(ResourceType.IRON, 200).add(ResourceType.CRYSTAL, 0)));
		
		buildings.add(new Building(
				/* id */				"mine_crystal", 
				/* name */				"Crystal Mine", 
				/* health */			1000,
				/* energy */			-100,
				/* production*/			new ResourceAmount(ResourceType.IRON, 0).add(ResourceType.CRYSTAL, 100),
				/* productionPerLvl */	new ResourceAmount(ResourceType.IRON, 0).add(ResourceType.CRYSTAL, 250)));
	}
	
	public static BuildingIndex getInstance()
	{
		if(instance == null)
		{
			instance = new BuildingIndex();
		}
		
		return instance;
	}
	
	public ArrayList<Building> getAllBuildings()
	{
		return buildings;
	}
	
	public Building getById(String id)
	{
		for(Building building : buildings)
		{
			if(building.getId().equals(id))
			{
				return building;
			}
		}
		
		return null;
	}

}
