package solitaire;

public class Card
{
	private int value;
	private String sign;
	private boolean visible = false;

	public Card(int value, String sign)
	{
		this.value = value;
		this.sign = sign;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public String getSign()
	{
		return this.sign;
	}
	
	public boolean getVisible()
	{
		return this.visible;
	}

	public void setVisible()
	{
		this.visible = true;
	}
}