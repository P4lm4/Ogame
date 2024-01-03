package pak;

import java.util.ArrayList;

import org.json.JSONArray;

public class ShipIndex
{
	private ArrayList<Ship> ships = new ArrayList<>();
	
	private static ShipIndex instance = null;
	
	private ShipIndex()
	{
		ships.add(new Ship(
				/* id */				"scout", 
				/* name */				"Scout", 
				/* health */			100,
				/* damage */			10, 
				/* capacity */			500, 
				/* speed */				1500, 
				/* price */				new ResourceAmount(ResourceType.CRYSTAL, 50).add(ResourceType.IRON, 10),
				/* construction time */	30));
		
		
		ships.add(new Ship(
				/* id */				"fighter", 
				/* name */				"Star Fighter", 
				/* health*/				500,
				/* damage*/				50, 
				/* capacity */			1000, 
				/* speed */				500, 
				/* price */				new ResourceAmount(ResourceType.CRYSTAL, 530).add(ResourceType.IRON, 100),
				/* construction time */ 40));
		
		ships.add(new Ship(
				/* id */				"bomber", 
				/* name */				"Bomber", 
				/* health*/				1000,
				/* damage*/				500, 
				/* capacity */			50, 
				/* speed */				100, 
				/* price */				new ResourceAmount(ResourceType.CRYSTAL, 530).add(ResourceType.IRON, 100),
				/* construction time */ 60));
	}
	
	public static ShipIndex getInstance()
	{
		if (instance == null)
		{
			instance = new ShipIndex();
		}
		
		return instance;
	}
	
	public ArrayList<Ship> getAllShips()
	{
		return ships;
	}
	
	public Ship getById(String id)
	{
		for (Ship ship : ships)
		{
			if (ship.getId().equals(id))
			{
				return ship;
			}
		}
		
		return null;
	}
	
	public JSONArray serializeToJson()
	{
		JSONArray list = new JSONArray();
		
		for(Ship s : ships)
		{
			list.put(s.serializeToJson());
		}
		
		return list;
	}
	
}
