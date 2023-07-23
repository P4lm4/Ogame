package pak;

import java.util.ArrayList;

public class ShipIndex
{
	private ArrayList<Ship> ships = new ArrayList<>();
	
	private static ShipIndex instance = null;
	
	private ShipIndex()
	{
		ships.add(new Ship(
				/* id */		"scout", 
				/* name */		"Scout", 
				/* health */	100,
				/* damage */	10, 
				/* capacity */	10, 
				/* speed */		500, 
				/* price */		new ResourceAmount(ResourceType.CRYSTAL, 50).add(ResourceType.IRON, 10)));
		
		
		ships.add(new Ship(
				/* id */		"fighter", 
				/* name */		"Star Fighter", 
				/* health*/		500,
				/* damage*/		50, 
				/* capacity */	100, 
				/* speed */		5000, 
				/* price */		new ResourceAmount(ResourceType.CRYSTAL, 530).add(ResourceType.IRON, 100)));
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
}
