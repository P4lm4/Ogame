package pak;

import java.util.ArrayList;

import org.json.JSONArray;

public class BuildingIndex
{
	
	private ArrayList<Building> buildings = new ArrayList<>();
	
	private static BuildingIndex instance = null;
	
	private BuildingIndex()
	{
		buildings.add(new Building(
				/* id */				"mine_iron", 
				/* name */				"Metal Mine", 
				/* health */			1000,
				/* energy */			-100,
				/* energyPerLvl */		-50,
				/* production */		new ResourceAmount(ResourceType.IRON, 100).add(ResourceType.CRYSTAL, 0), 
				/* productionPerLvl */	new ResourceAmount(ResourceType.IRON, 200).add(ResourceType.CRYSTAL, 0),
				/* price */				new ResourceAmount(ResourceType.IRON, 100).add(ResourceType.CRYSTAL, 50),
				/* pricePerLvl */		new ResourceAmount(ResourceType.IRON, 200).add(ResourceType.CRYSTAL, 100),
				/* constractionTimer */	60));
		
		buildings.add(new Building(
				/* id */				"mine_crystal", 
				/* name */				"Crystal Mine", 
				/* health */			1000,
				/* energy */			-100,
				/* energyPerLvl */		-50,
				/* production*/			new ResourceAmount(ResourceType.IRON, 0).add(ResourceType.CRYSTAL, 100),
				/* productionPerLvl */	new ResourceAmount(ResourceType.IRON, 0).add(ResourceType.CRYSTAL, 250),
				/* price */				new ResourceAmount(ResourceType.IRON, 100).add(ResourceType.CRYSTAL, 50),
				/* pricePerLvl */		new ResourceAmount(ResourceType.IRON, 200).add(ResourceType.CRYSTAL, 100),
				/* constractionTimer */	60));
		
		buildings.add(new Building(
				/* id */				"powerplant_energy", 
				/* name */				"Solar Power Plant", 
				/* health */			1000,
				/* energy */			1000,
				/* energyPerLvl */		200,
				/* production*/			new ResourceAmount(ResourceType.IRON, 0).add(ResourceType.CRYSTAL, 0),
				/* productionPerLvl */	new ResourceAmount(ResourceType.IRON, 0).add(ResourceType.CRYSTAL, 0),
				/* price */				new ResourceAmount(ResourceType.IRON, 100).add(ResourceType.CRYSTAL, 50),
				/* pricePerLvl */		new ResourceAmount(ResourceType.IRON, 200).add(ResourceType.CRYSTAL, 100),
				/* constractionTimer */	60));
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
	
	public JSONArray serializeToJson()
	{
		JSONArray list = new JSONArray();
		
		for(Building b : buildings)
		{
			list.put(b.serializeToJson());
		}
		
		return list;
	}

}
