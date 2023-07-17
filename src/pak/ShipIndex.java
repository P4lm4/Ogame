package pak;

import java.util.ArrayList;

public class ShipIndex
{
	private ArrayList<Ship> ships = new ArrayList<>();
	
	private static ShipIndex instance = null;
	
	private ShipIndex()
	{
		ships.add(new Ship("scout", "Scout", 100, 10, 10, 500, new ResourceAmount(ResourceType.CRYSTAL, 50).add(ResourceType.IRON, 10)));
		ships.add(new Ship("fighter", "Star Fighter", 500, 50, 100, 5000, new ResourceAmount(ResourceType.CRYSTAL, 530).add(ResourceType.IRON, 100)));
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
