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
			
			System.out.println("Imate resursa ya kupvinu.");
			pareIgraca.remove(brod.getPrice(), 10);
			System.out.println("Igracu je ostalo " + pareIgraca);
			
		}
		else
		{
			System.out.println("Nemate dovoljno resursa!");
		}

	}

}
