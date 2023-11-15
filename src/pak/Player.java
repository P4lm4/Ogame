package pak;

import java.util.ArrayList;

public class Player
{
	
	private int id;
	private String name;
	private String password;
	
	public Player(int id, String name, String password)
	{
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	ArrayList<Planet> listOfOwnedPlanets = new ArrayList<Planet>();
	ArrayList<Planet> listOfKnownPlanets = new ArrayList<Planet>();
	
	public ArrayList<Planet> scanFromPlanet(Planet planet)
	{
		int radius = 20;
		ArrayList<Planet> planets = planet.getUniverse().planetScan(planet, radius);
		ArrayList<Planet> newPlanets = new ArrayList<Planet>();
		
		for(Planet p : planets)
		{
			if(!listOfKnownPlanets.contains(p))
			{
				listOfKnownPlanets.add(p);
				newPlanets.add(p);
			}
		}
		
		return newPlanets;
		
	}


	public void takePlanet(Planet planet)
	{
		for(Planet p : listOfOwnedPlanets)
		{
			if(p == planet)
			{
				System.out.println("Igrac vec posjeduje " + planet + " planetu.");;
				return;
			}
		}
		
		if(planet.getOwner() != null)
		{
			
			planet.getOwner().listOfOwnedPlanets.remove(planet);
	
		}
		
		listOfOwnedPlanets.add(planet);
		planet.setOwner(this);
		
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String toString()
	{
		return name;
	}

}
