package pak;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Universe
{
	private final long UPDATE_INTERVAL = 1000;
	private int numberOfPlanets;
	private int height;
	private int width;
	
	private long lastUpdate;
	
	ArrayList<Planet> planetsOfTheUniverse = new ArrayList<Planet>();
	ArrayList<Player> players = new ArrayList<Player>();
	

	
	public Universe(int heightCordinate, int weightCordinate, int nubmerOfPlantes)
	{
		this.height = heightCordinate;
		this.width = weightCordinate;
		this.numberOfPlanets = nubmerOfPlantes;
		
		generate();
		lastUpdate = System.currentTimeMillis();
	}
	
	public Universe(JSONObject json)
	{
		load(json);
	}
	
	private void load(JSONObject json)
	{
		numberOfPlanets = json.getInt("numberOfPlanets");
		height = json.getInt("height");
		width = json.getInt("width");
		lastUpdate = json.getLong("lastUpdate");
		
		JSONArray player = json.getJSONArray("players");
		for(int i = 0; i < player.length(); i++)
		{
			players.add(new Player(player.getJSONObject(i), this));
		}
		
		JSONArray planets = json.getJSONArray("planets");
		for(int i = 0; i < planets.length(); i++)
		{
			planetsOfTheUniverse.add(new Planet(planets.getJSONObject(i), this));
		}
		
		for(Player p : players)
		{
			p.resolvePlanets();
		}
		
		for(Planet p : planetsOfTheUniverse)
		{
			p.resolveFlights();
		}
	}
	
	public Universe(String fileName)
	{
		try
		{
			Scanner s = new Scanner(new File(fileName));
			
			StringBuilder sb = new StringBuilder();
			
			while(s.hasNext())
			{
				sb.append(s.nextLine());
			}
			
			s.close();
			
			JSONObject json = new JSONObject(sb.toString());
			
			load(json);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Neuspijesno ucitavanje univerzuma iza fajla " + fileName);
			e.printStackTrace();
		}
	}
	
	public JSONObject serializeToJson()
	{
		JSONObject json = new JSONObject();
		
		json.put("numberOfPlanets", numberOfPlanets);
		json.put("height", height);
		json.put("width", width);
		json.put("lastUpdate", lastUpdate);
		
		JSONArray planetsArray = new JSONArray();
		JSONArray playersArray = new JSONArray();
		
		for(Planet p : planetsOfTheUniverse)
		{
			planetsArray.put(p.serializeToJson());
		}
		json.put("planets", planetsArray);
		
		for(Player p : players)
		{
			playersArray.put(p.serializeToJson());
		}
		json.put("players", playersArray);
		
		return json;
	}
	
	public void saveToFile(String fileName)
	{
		try
		{
			PrintWriter pw = new PrintWriter(new File(fileName));
			
			pw.println(serializeToJson());
			
			pw.close();
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Greska u snimanju univerzuma u fajl " + fileName);
			e.printStackTrace();
		}
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
		
	
		
		for(int p = 0; p < numberOfPlanets; p++)
		{
			
			int positionX = randomNumber.nextInt(height);
			int positionY = randomNumber.nextInt(width);
			
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
	
	public Player getPlayer(int idPlayer)
	{
		for(Player p : players)
		{
			if(p.getId() == idPlayer)
			{
				return p;
			}
		}	
		return null;
	}

}
