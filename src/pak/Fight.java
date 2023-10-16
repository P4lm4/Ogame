package pak;

public class Fight
{
	
	public static boolean attackPlanet(Fleet attFleet, Planet defPlanet) 
	{
		
		int resultOfFight = defPlanet.getShips().getSumHealth() -  attFleet.getSumDamage();
		
		if(resultOfFight > 0)
		{
			return false;
		}
		
		return true;
		

	}

}
