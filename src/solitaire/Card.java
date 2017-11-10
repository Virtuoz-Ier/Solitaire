package solitaire;

public class Card
{
	private int value;
	private String signe;
	private boolean visible = false;

	public Card(int value, String signe)
	{
		this.value = value;
		this.signe = signe;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public String getSigne()
	{
		return this.signe;
	}
	
	public String getStringValue()
	{
		if (this.value == 1)
			return "A";
		else if (this.value == 11)
			return "V";
		else if (this.value == 12)
			return "D";
		else if (this.value == 13)
			return "R";
		else
			return String.valueOf(this.value);
	}

	public void setVisible()
	{
		this.visible = true;
	}
	
	public String littleCard()
	{
		String s = "-----\n";
		if (this.visible)
			s += "|" + String.format("%-2s", this.getStringValue()) + this.getSigne() + "|\n";
		else
			s += "|   |\n";
		
		return s;
	}
	
	public String toString()
	{
		String s = "-----\n";
		if (this.visible)
			s += "|" + String.format("%-2s", this.getStringValue()) + this.getSigne() + "|\n";
		else
			s += "|   |\n";
		s += "|   |\n";
		s += "-----\n";
		
		return s;
	}
}