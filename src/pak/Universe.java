package pak;

import java.util.ArrayList;
import java.util.Random;

public class Universe
{
	
	private int numbersOfPlanet;
	private int heightCordinate;
	private int weightCordinate;
	
	ArrayList<Planet> planetsOfTheUniverse = new ArrayList<Planet>();
	
	public Universe(int heightCordinate, int weightCordinate, int nubmerOfPlantes)
	{
		this.heightCordinate = heightCordinate;
		this.weightCordinate = weightCordinate;
		this.numbersOfPlanet = nubmerOfPlantes;
		
		generate();
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
