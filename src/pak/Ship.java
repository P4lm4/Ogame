package pak;

public class Ship 
{
	private String id;
	private String name;
	private int health;
	private int energy;
	private int damage;
	private int capacity;
	private int speed;
	private ResourceAmount price;
	
	
	public Ship(String id, String name, int health,int energy, int damage, int capacity, int speed, ResourceAmount price)
	{
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.energy = energy;
		this.damage = damage;
		this.capacity = capacity;
		this.speed = speed;
		this.price = price;
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
	
	public int getEnergy()
	{
		return energy;
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

	
	
}
