package pak;

public class Main
{

	public static void main(String[] args)
	{	
		Ship brod = ShipIndex.getInstance().getById("scout");
		
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

	}

}
