package pak;

import java.util.ArrayList;

public class Main
{

	public static void main(String[] args)
	{	
		Ship brod = ShipIndex.getInstance().getById("scout");
		Ship brod1 = ShipIndex.getInstance().getById("fighter");
		Ship brod2 = ShipIndex.getInstance().getById("bomber");
		
		Building metalZgrada = BuildingIndex.getInstance().getById("mine_metal");
		Building kristalZgrada = BuildingIndex.getInstance().getById("mine_crystal");
		Building elektrana = BuildingIndex.getInstance().getById("powerplant_energy");
		
		ResourceAmount pareIgraca = new ResourceAmount(ResourceType.IRON, 1000).add(ResourceType.CRYSTAL, 1000);
		
		System.out.println("Igrace trenutno ima " + pareIgraca);
		
		if(pareIgraca.has(brod.getPrice(), 10))
		{
			
			System.out.println("Imate resursa za kupovinu.");
			pareIgraca.remove(brod.getPrice(), 10);
			System.out.println("Igracu je ostalo " + pareIgraca);
			
		}
		else
		{
			System.out.println("Nemate dovoljno resursa!");
		}
		
		Fleet flota1 = new Fleet();
		flota1.addShip(brod, 500);
		flota1.addShip(brod1, 500);
		flota1.addShip(brod2, 50);
		
		Fleet flota2 = new Fleet();
		flota2.addShip(brod, 200);
		flota2.addShip(brod1, 300);
		flota2.addShip(brod2, 30);
		
		System.out.println("Kapacitet flote je: " + flota1.getMaxCapacity());
		
		System.out.println("Brzina flote je: " + flota1.getMaxSpeed());
		
		System.out.println(flota1);
		
		Universe alpha = new Universe(100, 100, 100);
		
		System.out.println("Imamo " + alpha.getNumberOfPlanets() + " planeta");
		
		System.out.println("Razmak izmedju planeta " + alpha.getPlanet(10) + " i " + alpha.getPlanet(20) + " je " + alpha.getPlanet(10).distanceTo(alpha.getPlanet(20)));
		 
		Planet planet1 = alpha.getPlanet(10); //new Planet(1, "Zemlja", new ResourceAmount(ResourceType.IRON, 1000).add(ResourceType.CRYSTAL, 1000),25,50);
		
		Planet planet2 = alpha.getPlanet(20);
		
		ResourceAmount resursiFlote = new ResourceAmount(ResourceType.IRON, 200).add(ResourceType.CRYSTAL, 300);
		
		ArrayList<Planet> pronadjenePlanete = alpha.planetScan(planet1, 10);
		
		System.out.println("Skenirane planete su: " + pronadjenePlanete);
		
		planet1.getShips().addShip(brod, 600).addShip(brod1, 800).addShip(brod2, 250);
		
		planet2.getShips().addShip(brod, 400).addShip(brod1, 400);
		
		Flight letFlote = planet1.newFlight(planet2, flota1, resursiFlote);
		
		System.out.println("Vrijeme leta flote je: " + letFlote.getTimeOfFlight());
		
		System.out.println("Na planeti je ostalo brodova: " + planet1.getShips());
		
		System.out.println("Na planeti je ostalo resursa: " + planet1.getResource());
		

		
		System.out.println("Na planeti " + planet2.getName() + " sada ima " + planet2.getShips() + " brodova.");
		
		
		
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(elektrana));
		
		for(int i = 0; i < 20; i++)
		{
			alpha.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(metalZgrada));
		
		for(int i = 0; i < 20; i++)
		{
			alpha.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(elektrana));
		
		for(int i = 0; i < 20; i++)
		{
			alpha.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
		
		System.out.println("Pokusavamo praviti zgradu: " + planet1.constructBuilding(metalZgrada));
		
		for(int i = 0; i < 200; i++)
		{
			alpha.tick();
		}
		
		System.out.println("Planeta ima: " + planet1);
	
		ArrayList<Planet> skeniranePlanete = alpha.planetScan(planet1, 20);
		
		System.out.println(skeniranePlanete);
		
		System.out.println("Na " + planet2.getName() + " ima brodova " + planet2.getShips());
		

	}

}
