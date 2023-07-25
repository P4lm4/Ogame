package pak;

import java.util.ArrayList;

public class Main
{

	public static void main(String[] args)
	{	
		Ship brod = ShipIndex.getInstance().getById("scout");
		
		Building metalZgrada = BuildingIndex.getInstance().getById("mine_metal");
		Building kristalZgrada = BuildingIndex.getInstance().getById("mine_crystal");
		Building elektrana = BuildingIndex.getInstance().getById("powerplant_energy");
		
		ResourceAmount pareIgraca = new ResourceAmount(ResourceType.IRON, 1000).add(ResourceType.CRYSTAL, 1000);
		
		System.out.println("Igrace trenutno ima " + pareIgraca);
		
		if(pareIgraca.has(brod.getPrice(), 10))
		{
			
			System.out.println("Imate resursa za kupvinu.");
			pareIgraca.remove(brod.getPrice(), 10);
			System.out.println("Igracu je ostalo " + pareIgraca);
			
		}
		else
		{
			System.out.println("Nemate dovoljno resursa!");
		}
		
		Fleet flota1 = new Fleet();
		flota1.addShip(brod, 100);
		flota1.removeShip(brod, 50);
		flota1.removeShip(ShipIndex.getInstance().getById("fighter"), 50);
		
		System.out.println(flota1);
		
		Universe alpha = new Universe(100, 100, 100);
		
		System.out.println("Imamo " + alpha.getNumberOfPlanets() + " planeta");
		
		System.out.println("Razmak izmedju planeta " + alpha.getPlanet(10) + " i " + alpha.getPlanet(20) + " je " + alpha.getPlanet(10).distanceTo(alpha.getPlanet(20)));
		 
		Planet planet1 = alpha.getPlanet(10); //new Planet(1, "Zemlja", new ResourceAmount(ResourceType.IRON, 1000).add(ResourceType.CRYSTAL, 1000),25,50);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(elektrana));
		
		
		
		for(int i = 0; i < 20; i++)
		{
			planet1.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(metalZgrada));
		
		for(int i = 0; i < 20; i++)
		{
			planet1.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(elektrana));
		
		for(int i = 0; i < 20; i++)
		{
			planet1.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(metalZgrada));
		
		for(int i = 0; i < 20; i++)
		{
			planet1.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
	
		ArrayList<Planet> skeniranePlanete = alpha.planetScaned(planet1, 20);
		
		System.out.println(skeniranePlanete);
		

	}

}
