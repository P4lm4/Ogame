package pak;

public class Building
{
	
	private String id;
	private String name;
	private int health;
	private int energy;
	private	ResourceAmount production;
	private ResourceAmount productionPerLvl;
	
	public Building(String id, String name, int health, int energy, ResourceAmount production, ResourceAmount productionPerLvl)
	{
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.energy = energy;
		this.production = production;
		this.productionPerLvl = productionPerLvl;
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
	
	public void produce(Planet planet, int lvl)
	{
		planet.getResource().add(production);
		planet.getResource().add(productionPerLvl, lvl);
	}
	

}
