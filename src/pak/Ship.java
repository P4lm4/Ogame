package pak;

public class Ship 
{
	private String id;
	private String name;
	private int health;
	private int damage;
	private int capacity;
	private int speed;
	
	public Ship(String id, String name, int health, int damage, int capacity, int speed)
	{
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.capacity = capacity;
		this.speed = speed;
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
	
	
}
