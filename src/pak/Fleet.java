package pak;

import java.util.HashMap;

public class Fleet
{
	
		
	HashMap<Ship, Integer> ships = new HashMap<Ship, Integer>();
	
	public Fleet addShip(Ship ship, int count)
	{
		
		if(ships.containsKey(ship))
		{
			
			int prevCount = ships.get(ship);
			ships.replace(ship, prevCount + count);
			
		}
		else
		{
			ships.put(ship, count);
		}
		
		return this;

		
	}
	
	public Fleet removeShip(Ship ship, int count)
	{
		if(ships.containsKey(ship))
		{
			int prevCount = ships.get(ship);
			
			if(prevCount - count <= 0)
			{
				ships.remove(ship);
				
				if(prevCount - count < 0)
				{
					System.out.println("Brisanje flote ide u negativno stanje! ");
				}
			}
			else
			{
				ships.replace(ship, prevCount - count);
			}	
		
		}
		else
		{
			System.out.println("Ne postoji brod u floti.");
		}
		
		return this;
	}
	
	public int getCount(Ship ship)
	{
		if(ships.containsKey(ship))
		{
			return ships.get(ship);
		}
		else
		{
			return 0;
		}
	}
	
	public String toString()
	{
		
		String result = " [";
		
		for(Ship i : ships.keySet())
		{
			result += " " + i.getName() + ":" + ships.get(i);
		}
		
		result += " ] ";
		
		return result;
		
	}

}
