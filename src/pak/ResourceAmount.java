package pak;

public class ResourceAmount
{
	
	private int[] amount;
	
	public ResourceAmount()
	{
		
		amount = new int[ResourceType.values().length];
		
	}
	
	public ResourceAmount(ResourceType resource, int count)
	{
		this(); //poziva drugi konstruktor(iznad konstruktor)
		add(resource, count);
	}
	
	public ResourceAmount add(ResourceAmount other, int multiplier)
	{
		for(int i = 0; i < amount.length; i++)
		{
			this.amount[i] += other.amount[i] * multiplier;
		}
		
		return this;
	}
	
	public ResourceAmount remove(ResourceAmount other, int multiplier)
	{
		for(int i = 0; i < amount.length; i++)
		{
			this.amount[i] -= other.amount[i] * multiplier;
		}
		
		return this;
	}
	
	public boolean has(ResourceAmount other)
	{
		
		for(int i = 0; i < amount.length; i++)
		{
			if(this.amount[i] < other.amount[i])
			{
				return false;
			}
		}
		
		return true;
			
	}
	
	public boolean has(ResourceAmount other, int multiplier)
	{
		
		for(int i = 0; i < amount.length; i++)
		{
			if(this.amount[i] < other.amount[i] * multiplier)
			{
				return false;
			}
		}
		
		return true;
			
	}
	
	public ResourceAmount remove(ResourceType resource, int count)
	{
		amount[resource.ordinal()] -= count;
		return this;
	}
	
	public ResourceAmount remove(ResourceAmount other)
	{
		for(int i = 0; i < amount.length; i++)
		{
			this.amount[i] -= other.amount[i];
		}
		
		return this;
	}
	
	/*
	public ResourceAmount checkPay(ResourceAmount other)
	{
		for(int i = 0; i < amount.length; i++)
		{
			if(this.amount[i] >= other.amount[i])
			{
				this.amount[i] -= other.amount[i];
			}
			else
			{	
			break;
			}
			
		}
		
		return this;
	}
	*/
	
	public ResourceAmount add(ResourceType resource, int count)
	{
		amount[resource.ordinal()] += count;
		return this; // omogucava da se nanize vise add poziva u jednoj liniji
	}	
	
	public ResourceAmount add(ResourceAmount other)
	{
		for(int i = 0; i < amount.length; i++)
		{
			this.amount[i] += other.amount[i];
		}
		
		return this;
	}
	
	public String toString()
	{
		String result = "[";
		
		for(int i = 0; i < amount.length; i++)
		{
			if(amount[i] != 0)
			{
				result += " " + ResourceType.values()[i] + ":" + amount[i]; 
			}
		}
		
		result += " ]";
		
		return result;
	}

}
