package solitaire;

import java.util.ArrayList;
import java.util.Scanner;

public class Cui
{
	private Tray tray;
	private Scanner sc = new Scanner(System.in);
	
	public Cui()
	{
		this.tray = new Tray();
		
		while (!this.tray.checkWin())
		{
			System.out.print(this);
			while(!moveCui())
				System.out.println("Error");
		}
		System.out.print("Félicitations : vous avez gagnez en " + this.tray.getNbTurns() + " coups!");
	}
	
	public String getStringValue(Card card)
	{
		if (card.getValue() == 1)
			return "A";
		else if (card.getValue() == 11)
			return "V";
		else if (card.getValue() == 12)
			return "D";
		else if (card.getValue() == 13)
			return "R";
		else
			return String.valueOf(card.getValue());
	}
	
	public String getCuiSigne(Card card)
	{
		if (card.getSign().equals("heart"))
			return "\u2661";
		else if (card.getSign().equals("diamond"))
			return "\u2662";
		else if (card.getSign().equals("spade"))
			return "\u2663";
		else if (card.getSign().equals("club"))
			return "\u2660";
		else
			return "";
	}
	
	public String getLittleCard(Card card)
	{
		String s = "-----\n";
		if (card.getVisible())
			s += "|" + String.format("%-2s", getStringValue(card)) + getCuiSigne(card) + "|\n";
		else
			s += "|   |\n";
		
		return s;
	}
	
	public String getCard(Card card)
	{
		String s = "-----\n";
		if (card.getVisible())
			s += "|" + String.format("%-2s", getStringValue(card)) + getCuiSigne(card) + "|\n";
		else
			s += "|   |\n";
		s += "|   |\n";
		s += "-----\n";
		
		return s;
	}
	
	public ArrayList<String> getCuiColumn(ArrayList<Card> column)
	{
		ArrayList<String> s;
		
		s = new ArrayList<String>();
		
		
		for (int i = 0; i < column.size(); i++)
		{
			String[] data;
			if (i == (column.size() - 1))
				data = getCard(column.get(i)).split("\n");
			else
				data = getLittleCard(column.get(i)).split("\n");
			for (int j = 0; j < data.length; j++)
				s.add(data[j]);
		}
		
		return s;
	}
	
	public ArrayList<String> getCuiSign(ArrayList<Card> sign)
	{
		ArrayList<String> s = new ArrayList<String>();
		String[] data;
		
		if (sign.size() > 0)
		{
			data = getCard(sign.get(sign.size() - 1)).split("\n");
			for (int j = 0; j < data.length; j++)
				s.add(data[j]);
		}
		else
		{
			s.add("-----");
			s.add("|   |");
			s.add("|   |");
			s.add("-----");
		}
		
		return s;
	}
	
	public ArrayList<String> getCuiDeck(ArrayList<Card> deck, int deck_position)
	{
		ArrayList<String> s = new ArrayList<String>();
		String[] data;
		
		if (deck.size() > 0)
		{
			data = getCard(deck.get(deck_position)).split("\n");
			for (int j = 0; j < data.length; j++)
				s.add(data[j]);
		}
		else
		{
			s.add("-----");
			s.add("|   |");
			s.add("|   |");
			s.add("-----");
		}
		
		return s;
	}
	
	public String getCuiTray()
	{
		String s = "";
		
		ArrayList<Card> deck = this.tray.getDeck();
		int deck_position = this.tray.getDeckPosition();
		ArrayList<ArrayList<Card>> signs = this.tray.getAllSigns();
		ArrayList<ArrayList<Card>> columns = this.tray.getAllColumns();
		
		ArrayList<String> deckString = getCuiDeck(deck, deck_position);
		
		ArrayList<ArrayList<String>> signsString = new ArrayList<ArrayList<String>>();
		
		for (int i = 0; i < signs.size(); i++)
			signsString.add(getCuiSign(signs.get(i)));
		
		for (int i = 0; i < 4; i++)
		{
			s += deckString.get(i) + "             ";
			for (int j = 0; j < 4; j++)
			{
				s += signsString.get(j).get(i) + " ";
			}
			s += "\n";
		}
		
		ArrayList<ArrayList<String>> columnsString = new ArrayList<ArrayList<String>>();
		s+= "\n";
		
		for (int i = 0; i < columns.size(); i++)
			columnsString.add(getCuiColumn(columns.get(i)));
		
		int maxSize = 0;
		
		for (int i = 0; i < columnsString.size(); i++)
			if (columnsString.get(i).size() > maxSize)
				maxSize = columnsString.get(i).size();
		
		for (int i = 0; i < maxSize; i++)
		{
			for (int j = 0; j < columnsString.size(); j++)
			{
				if (i < columnsString.get(j).size())
					s += columnsString.get(j).get(i) + " ";
				else
					s += "      ";
			}
			s += "\n";
		}
		
		return s;
	}
	
	public ArrayList<Card> getCuiColumn(int nb)
	{
		if (nb > 0 && nb < 8)
			return this.tray.getColumn(nb - 1);
		else if (nb >= 8)
			return this.tray.getSign(nb - 8);
		else if (nb == 0)
			return this.tray.getDeck();
		else
			return null;
	}
	
	public int[] getCuiOptions()
	{
		System.out.println("Tour " + this.tray.getNbTurns() + ":");
		String input = sc.nextLine();
		if (input.equals("P") || input.equals("p"))
			return new int[] { 0, 0 };
		String tmp[] = input.split("/");
		if (tmp.length > 3 || tmp.length < 2)
		{
			System.out.println("Error");
			return getCuiOptions();
		}
		int options[] = new int[tmp.length];
		
		
		if (tmp[0].equals("P") || tmp[0].equals("p"))
			options[0] = 0;
		else if (tmp[0].equals("A") || tmp[0].equals("a"))
			options[0] = 8;
		else if (tmp[0].equals("B") || tmp[0].equals("b"))
			options[0] = 9;
		else if (tmp[0].equals("C") || tmp[0].equals("c"))
			options[0] = 10;
		else if (tmp[0].equals("D") || tmp[0].equals("d"))
			options[0] = 11;
		else if (tmp[0].equals("1") || tmp[0].equals("2") || tmp[0].equals("3") || tmp[0].equals("4") || tmp[0].equals("5") || tmp[0].equals("6") || tmp[0].equals("7"))
			options[0] = Integer.parseInt(tmp[0]);
		else
		{
			System.out.println("Error");
			return getCuiOptions();
		}
		
		if (tmp[1].equals("A") || tmp[1].equals("a"))
			options[1] = 8;
		else if (tmp[1].equals("B") || tmp[1].equals("b"))
			options[1] = 9;
		else if (tmp[1].equals("C") || tmp[1].equals("c"))
			options[1] = 10;
		else if (tmp[1].equals("D") || tmp[1].equals("d"))
			options[1] = 11;
		else if (tmp[1].equals("1") || tmp[1].equals("2") || tmp[1].equals("3") || tmp[1].equals("4") || tmp[1].equals("5") || tmp[1].equals("6") || tmp[1].equals("7") 
			  || tmp[1].equals("8") || tmp[1].equals("9") || tmp[1].equals("10") || tmp[1].equals("11") || tmp[1].equals("12") || tmp[1].equals("13"))
			options[1] = Integer.parseInt(tmp[1]);
		else
		{
			System.out.println("Error");
			return getCuiOptions();
		}
		
		if (tmp.length == 3)
			if (tmp[2].equals("1") || tmp[2].equals("2") || tmp[2].equals("3") || tmp[2].equals("4") || tmp[2].equals("5") || tmp[2].equals("6") || tmp[2].equals("7") 
				  || tmp[2].equals("8") || tmp[2].equals("9") || tmp[2].equals("10") || tmp[2].equals("11") || tmp[2].equals("12") || tmp[2].equals("13"))
				options[2] = Integer.parseInt(tmp[2]);
			else
			{
				System.out.println("Error");
				return getCuiOptions();
			}
		
		return options;
	}
	
	public boolean moveCui()
	{
		int options[] = this.getCuiOptions();
		
		int nb_cards = 1;
		if (options.length == 3)
			nb_cards = options[2];
		
		boolean fromDeck = false;
		if (options[0] == 0)
		{
			fromDeck = true;
		}
		
		if (options[0] == 0 && options[1] == 0)
		{
			this.tray.upDeck();
			this.tray.upNbTurns();
			return true;
			
		}
		if (options[1] == 8 || options[1] == 9 || options[1] == 10 || options[1] == 11)
		{
			return this.tray.moveToSign(this.getCuiColumn(options[0]), this.getCuiColumn(options[1]), fromDeck);
		}
		else if (options[1] > 0 && options[1] < 8)
			return (this.tray.moveToColumn(nb_cards, this.getCuiColumn(options[0]), this.getCuiColumn(options[1]), fromDeck));
		else
			return false;
	}
	
	public String toString()
	{
		String s = "";
		
		s += this.getCuiTray();
		
		return s;
	}
}