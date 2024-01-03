package pak;

import org.json.JSONObject;

public class Ship 
{
	private String id;
	private String name;
	private int health;
	private int damage;
	private int capacity;
	private int speed;
	private ResourceAmount price;
	private int constructionTimer;
	
	
	public Ship(String id, String name, int health, int damage, int capacity, int speed, ResourceAmount price, int constructionTimer)
	{
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.capacity = capacity;
		this.speed = speed;
		this.price = price;
		this.constructionTimer = constructionTimer;
	}
	
	public JSONObject serializeToJson()
	{
		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("name", name);
		json.put("health", health);
		json.put("damage", damage);
		json.put("capacity", capacity);
		json.put("speed", speed);
		json.put("price", price.serializeToJson());
		json.put("constructionTimer", constructionTimer);
		
		return json;
		
	}
	
	public String getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public int getHealth() 
	{
		return health;
	}

	public int getDamage() 
	{
		return damage;
	}

	public int getCapacity() 
	{
		return capacity;
	}

	public int getSpeed() 
	{
		return speed;
	}
	
	public ResourceAmount getPrice()
	{
		return price;
	}
	
	public int getConstructionTimer()
	{
		return constructionTimer;
	}

	
	
}
