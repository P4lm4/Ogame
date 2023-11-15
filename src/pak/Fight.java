package pak;

public class Fight
{
	
	public static boolean attackPlanet(Fleet attFleet, Planet defPlanet) 
	{
		
		if(attFleet.getSumDamage() > defPlanet.getSumOfDefDamage())
		{
			/*Napadac je pobjedio*/
			int result = attFleet.getSumDamage() - defPlanet.getSumOfDefDamage();
			defPlanet.getShips().clear();
			attFleet.takeDamage(result);
			return true;
		}
		else
		{
			/*Planeta je pobjedio*/
			int result = defPlanet.getSumOfDefDamage() - attFleet.getSumDamage();
			attFleet.clear();
			defPlanet.getShips().takeDamage(result);
			return false;
		}

	}

}
