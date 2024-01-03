package pak;

import org.json.JSONObject;

public class Building
{
	
	private String id;
	private String name;
	private int health;
	private int energy;
	private int energyPerLvl;
	private	ResourceAmount production;
	private ResourceAmount productionPerLvl;
	private ResourceAmount price;
	private ResourceAmount pricePerLvl;
	private int constructionTimer;
	
	public Building(String id, String name, int health, int energy, int energyPerLvl, ResourceAmount production, ResourceAmount productionPerLvl, ResourceAmount price, ResourceAmount pricePerLvl, int constractionTimer)
	{
		super();
		this.id = id;
		this.name = name;
		this.health = health;
		this.energy = energy;
		this.energyPerLvl = energyPerLvl;
		this.production = production;
		this.productionPerLvl = productionPerLvl;
		this.price = price;
		this.pricePerLvl = pricePerLvl;
		this.constructionTimer = constractionTimer;
	}
	
	public JSONObject serializeToJson()
	{
		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("name", name);
		json.put("health", health);
		json.put("energy", energy);
		json.put("energyPerLvl", energyPerLvl);
		json.put("production", production.serializeToJson());
		json.put("productionPerLvl", productionPerLvl.serializeToJson());
		json.put("price", price.serializeToJson());
		json.put("pricePerLvl", pricePerLvl.serializeToJson());
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
		return constructionTimer;
	}
	
	public void produce(Planet planet, int lvl)
	{
		planet.getResource().add(production);
		planet.getResource().add(productionPerLvl, lvl);
		
	}
	
	public ResourceAmount getPriceForLvl(int lvl)
	{
		ResourceAmount targetPrice = new ResourceAmount();
		
		targetPrice.add(price);
		targetPrice.add(pricePerLvl, lvl);
		
		return targetPrice;
	}
	
	public int getEnergyPetLvl(int lvl)
	{
		return energy + energyPerLvl * lvl;
	}

}
