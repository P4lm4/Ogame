package pak;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Player
{
	
	private int id;
	private String name;
	private String password;
	private String token;
	private Universe universe;
	
	JSONArray ownedArray;
	JSONArray knownArray;
	
	public Player(int id, String name, String password)
	{
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Player(JSONObject json, Universe universe)
	{
		this.universe = universe;
		id = json.getInt("id");
		name = json.getString("name");
		password = json.getString("password");
		if(json.has("token"))
		{
			token = json.getString("token");
		}
		else
		{
			token = null;
		}
		ownedArray = json.getJSONArray("ownedArray");
		knownArray = json.getJSONArray("knownArray");
	
	}
	
	public void resolvePlanets()
	{
		for(int i = 0; i < ownedArray.length(); i++)
		{
			listOfOwnedPlanets.add(universe.getPlanet(ownedArray.getInt(i)));
		}
		for(int i = 0; i < knownArray.length(); i++)
		{
			listOfKnownPlanets.add(universe.getPlanet(knownArray.getInt(i)));
		}
		
		ownedArray = null;
		knownArray = null;
	}
	
	public JSONObject serializeToJson()
	{
		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("name", name);
		json.put("password", password);
		json.put("token", token);
		
		JSONArray ownedArray = new JSONArray();
		JSONArray knownArray = new JSONArray();
		
		for(Planet p : listOfOwnedPlanets)
		{
			ownedArray.put(p.getId());
		}
		for(Planet p : listOfKnownPlanets)
		{
			knownArray.put(p.getId());
		}
		
		json.put("ownedArray", ownedArray);
		json.put("knownArray", knownArray);
		
		return json;
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
	
	public String generateToken()
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int lenght = 16;
		
		token = "";
		
		for(int i = 0; i < lenght; i++)
		{
			token += alphabet.charAt((int)(Math.random()*alphabet.length()));
		}
		
		return token;
	}
	
	public void clearToken()
	{
		token = null;
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
	
	public String getToken()
	{
		return token;
	}
	
	public ArrayList<Planet> getOwnedPlanets()
	{
		return listOfOwnedPlanets;
	}

}
