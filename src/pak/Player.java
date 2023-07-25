package pak;

import java.util.ArrayList;

public class Player
{
	
	private String id;
	private String name;
	private String password;
	
	public Player(String id, String name, String password)
	{
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	ArrayList<Planet> listOfPlanets = new ArrayList<Planet>();
	
	public void takePlanet(Planet planet)
	{
		for(Planet p : listOfPlanets)
		{
			if(p == planet)
			{
				System.out.println("Igrac vec posjeduje " + planet + " planetu.");;
				break;
			}
		}
		
		listOfPlanets.add(planet);
		
	}
	
	public String getId()
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

}
