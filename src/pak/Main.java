package pak;

public class Main
{

	public static void main(String[] args)
	{	
		Ship brod = ShipIndex.getInstance().getById("scout");
		Building zgrada = BuildingIndex.getInstance().getById("metalM");
		
		System.out.println("Zgrada ima " + zgrada.getHealth() + " helta");
		
		System.out.println("Fajter ima " + brod.getHealth() + " helta");

	}

}
