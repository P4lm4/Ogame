package pak;

public class Building
{
	
	private String id;
	private String name;
	private int health;
	private int energy;
	private	ResourceAmount production;
	private ResourceAmount productionPerLvl;
	private ResourceAmount price;
	private ResourceAmount pricePerLvl;
	private int constractionTimer;
	
	public Building(String id, String name, int health, int energy, ResourceAmount production, ResourceAmount productionPerLvl, ResourceAmount price, ResourceAmount pricePerLvl, int constractionTimer)
	{
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.energy = energy;
		this.production = production;
		this.productionPerLvl = productionPerLvl;
		this.price = price;
		this.pricePerLvl = pricePerLvl;
		this.constractionTimer = constractionTimer;
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
	
	public ResourceAmount getProduction()
	{
		return production;
	}
	
	public ResourceAmount getProductionPerLvl()
	{
		return productionPerLvl;
	}	
	
	public ResourceAmount getPrice()
	{
		return price;
	}
	
	public ResourceAmount getPricePerLvl()
	{
		return pricePerLvl;
	}
	
	public int getConstractionTimer()
	{
		return constractionTimer;
	}
	
	public void produce(Planet planet, int lvl)
	{
		planet.getResource().add(production);
		planet.getResource().add(productionPerLvl, lvl);
		
	}
	
	public void payPrice(Planet planet, int lvl)
	{
		planet.getResource().remove(price);
		planet.getResource().remove(pricePerLvl);
	}
	

}
