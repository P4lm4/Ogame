package pak;

import java.util.ArrayList;

public class PlayerIndex
{
	
	private int playerID = 0;
	
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
	
	public Player register(String name, String password)
	{
		for(Player p : playerList)
		{
			if(p.getName().equals(name))
			{
				return null;
			}
		}
		
		Player player = new Player(playerID, name, password);
		
		playerList.add(player);
		playerID++;
		
		return player;
		
	}
	
	public Player login(String name, String password)
	{
		for(Player p : playerList)
		{
			if(p.getName().equals(name) && p.getPassword().equals(password))
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
	
	public Player getById(int id)
	{
		for(Player p : playerList)
		{
			if(p.getId() == id)
			{
				return p;
			}
		}
		
		return null;
	}

}
