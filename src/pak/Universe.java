package pak;

import java.util.ArrayList;
import java.util.Random;

public class Universe
{
	private final long UPDATE_INTERVAL = 1000;
	private int numbersOfPlanet;
	private int heightCordinate;
	private int weightCordinate;
	
	private long lastUpdate;
	
	ArrayList<Planet> planetsOfTheUniverse = new ArrayList<Planet>();
	ArrayList<Player> players = new ArrayList<Player>();
	

	
	public Universe(int heightCordinate, int weightCordinate, int nubmerOfPlantes)
	{
		this.heightCordinate = heightCordinate;
		this.weightCordinate = weightCordinate;
		this.numbersOfPlanet = nubmerOfPlantes;
		
		generate();
		lastUpdate = System.currentTimeMillis();
	}
	
	public void update()
	{
		while(lastUpdate + UPDATE_INTERVAL <= System.currentTimeMillis())
		{
			tick();
			lastUpdate += UPDATE_INTERVAL;
		}
	}
	
	private void generate()
	{
		Random randomNumber = new Random();
		
	
		
		for(int p = 0; p < numbersOfPlanet; p++)
		{
			
			int positionX = randomNumber.nextInt(heightCordinate);
			int positionY = randomNumber.nextInt(weightCordinate);
			
			boolean ok = true;
			
			for(Planet planets : planetsOfTheUniverse)
			{
			
				if(planets.getPositionX() == positionX && planets.getPositionY() == positionY)
				{
					ok = false;
					break;	
				}
				
			}
			
			if(ok)
			{
				planetsOfTheUniverse.add(new Planet(this, p, "Planet" + p, new ResourceAmount(ResourceType.IRON, 500).add(ResourceType.CRYSTAL, 500),positionX, positionY));
			}
		}
		
	}
	
	public void tick()
	{
		for(Planet p : planetsOfTheUniverse)
		{
			p.tick();
		}
	}
	
	public Player register(String username, String password)
	{
		if(username.length() < 3 || username.length() > 20)
		{
			return null;
		}
		if(password.length() < 6 || password.length() > 150)
		{
			return null;
		}
		
		Planet startPlanet = null;
		
		for(Planet p : planetsOfTheUniverse)
		{
			if(p.getOwner() == null)
			{
				startPlanet = p;
				break;
			}
		}
		
		if(startPlanet == null)
		{
			return null;
		}
		for(Player p : players)
		{
			if(p.getName().equalsIgnoreCase(username))
			{
				return null;
			}
		}
		
		Player player = new Player(players.size(), username, password);
		player.takePlanet(startPlanet);
		players.add(player);
		
		return player;
	}
	
	public Player login(String username, String password)
	{
		for(Player p : players)
		{
			if(p.getName().equalsIgnoreCase(username) && p.getPassword().equals(password))
			{
				p.generateToken();
				return p;
			}			
		}
		
		return null;
	}
	
	public Player authenticate(String token)
	{
		if(token == null || token.length() == 0)
		{
			return null;
		}
		
		for(Player p : players)
		{
			if(p.getToken() != null && p.getToken().equals(token))
			{
				return p;
			}
		}
		
		return null;
	}
	
	public ArrayList<Planet> planetScan(Planet planet, int radius)
	{
		
		ArrayList<Planet> planets = new ArrayList<Planet>();
		
		for(Planet p : planetsOfTheUniverse)
		{
			if(p.distanceTo(planet) <= radius && p != planet)
			{
				planets.add(p);				
			}
		}
		
		return planets;
	}
	
	public boolean checkPlanet(Planet p)
	{
		for(Planet planet : planetsOfTheUniverse)
		{
			if(p.getId() == planet.getId())
			{
				System.out.println("Planeta se nalazi u univerzumu. ");
				return true;
			}
		}
		
		return false;
	}
	
	public Planet getPlanet(int idPlanet)
	{
		return planetsOfTheUniverse.get(idPlanet);
	}
	
	public int getNumberOfPlanets()
	{
		return planetsOfTheUniverse.size();
	}

}
