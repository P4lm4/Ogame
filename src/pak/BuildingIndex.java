package pak;

import java.util.ArrayList;

public class BuildingIndex
{
	
	private ArrayList<Building> buildings = new ArrayList<>();
	
	private static BuildingIndex instance = null;
	
	private BuildingIndex()
	{
		buildings.add(new Building("metalM", "Metal Mine", 1000));
		buildings.add(new Building("crystalM", "Crystal Mine", 1000));
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
