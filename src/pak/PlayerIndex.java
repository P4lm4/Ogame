package pak;

import java.util.ArrayList;

public class PlayerIndex
{
	
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	private static PlayerIndex instance = null;
	
	private PlayerIndex()
	{
		
	}
	
	public static PlayerIndex getInstance()
	{
		if(instance == null)
		{
			instance = new PlayerIndex();
		}
		
		return instance;
	}
	
	public Player login(String name, String password)
	{
		for(Player p : playerList)
		{
			if(p.getName().contains(name) && p.getPassword().contains(password))
			{
				return p;
			}
		}
		
		return null;
	}
	
	public ArrayList<Player> getAllPlayers()
	{
		return playerList;
	}
	
	public Player getById(String id)
	{
		for(Player p : playerList)
		{
			if(p.getId().equals(id))
			{
				return p;
			}
		}
		
		return null;
	}

}
