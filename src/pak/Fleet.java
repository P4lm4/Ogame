package pak;

import java.util.HashMap;

public class Fleet
{
	
	private Player owner;
		
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
	
	public Fleet remove(Fleet other)
	{
		
		for(Ship s : ships.keySet())
		{
			if(other.ships.containsKey(s))
			{
				int numFleetA = other.ships.get(s);
				int numFleetB = ships.get(s);
				
				if(numFleetA < numFleetB)
				{
					numFleetB -= numFleetA;
					ships.replace(s, numFleetB);

				}
				else
				{
					ships.remove(s);
				}
			}
		}
		
		return this;
	}
	
	public boolean isLessOrEqual(Fleet other)
	{
		//Prolazimo kroz sve brodove ove flote
		for(Ship s : ships.keySet())
		{
			//Ako taj brod postoji u drugoj floti
			if(other.ships.containsKey(s))
			{
				//Ako ih prva flota ima vise onda nije manje ili jednako i vraca false
				if(ships.get(s) > other.ships.get(s))
				{
					return false;
				}
			}
			//Ako ne postoji u drugoj floti ona nije manje ili jednako jer fali taj brod
			else
			{
				return false;
			}
		}
		//Ako smo stigli dovde onda je sve ok
		return true;
		
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
	
	public int getMaxCapacity()
	{
		int numberOfShips = 0;
		int sumCapacity = 0;
		
		for(Ship ship : ships.keySet())
		{
			numberOfShips = ships.get(ship);
			sumCapacity += ship.getCapacity() * numberOfShips;
		}
		
		return sumCapacity;
	}
	
	public int getMaxSpeed()
	{
		int speed = Integer.MAX_VALUE;
		
		for(Ship s : ships.keySet())
		{
			
			if(s.getSpeed() <= speed)
			{
				speed = s.getSpeed();
			}	
	
		}
		
		return speed;
	}
	
	public int getSumDamage()
	{
		int sumOfShips = 0;
		int sumDamage = 0;
		
		for(Ship s : ships.keySet())
		{
			sumOfShips = ships.get(s);
			sumDamage += s.getDamage() * sumOfShips;
		}
		
		return sumDamage;
	}
	
	public int getSumHealth()
	{
		int sumOfShips = 0;
		int sumHealth = 0;
		
		for(Ship s : ships.keySet())
		{
			sumOfShips = ships.get(s);
			sumHealth += s.getHealth() * sumOfShips;
		}
		
		return sumHealth;
	}
	
	public Player getOwner()
	{
		return owner;
	}	
	
	
	public boolean isAlive()
	{
		if(getSumHealth() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


}
