package solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class Tray
{
	private ArrayList<Card> cards;
	private ArrayList<Card> deck;
	private ArrayList<Card> column1;
	private ArrayList<Card> column2;
	private ArrayList<Card> column3;
	private ArrayList<Card> column4;
	private ArrayList<Card> column5;
	private ArrayList<Card> column6;
	private ArrayList<Card> column7;
	private ArrayList<Card> hearts;
	private ArrayList<Card> diamonds;
	private ArrayList<Card> spades;
	private ArrayList<Card> clubs;
	private int deck_position;
	
	public Tray()
	{
		cards = new ArrayList<Card>();
		String signe = "";
		
		for (int i = 1; i <= 4; i++)
		{
			if (i == 1)
				signe = "\u2661";
			else if (i == 2)
				signe = "\u2662";
			else if (i == 3)
				signe = "\u2663";
			else if (i == 4)
				signe = "\u2660";
			
			for (int j = 1; j <= 13; j++)
			{
				cards.add(new Card(j, signe));
			}
		}
		
		Collections.shuffle(cards);
		this.deck = new ArrayList<Card>();
		
		for (int i = 1; i <= 24; i++)
		{
			cards.get(i - 1).setVisible();
			this.deck.add(cards.get(i - 1));
		}
		
		this.deck_position = 0;
		
		this.hearts = new ArrayList<Card>();
		this.diamonds = new ArrayList<Card>();
		this.spades = new ArrayList<Card>();
		this.clubs = new ArrayList<Card>();
		
		column1 = new ArrayList<Card>();
		column2 = new ArrayList<Card>();
		column3 = new ArrayList<Card>();
		column4 = new ArrayList<Card>();
		column5 = new ArrayList<Card>();
		column6 = new ArrayList<Card>();
		column7 = new ArrayList<Card>();
		
		for (int i = 25; i <= 52; i++)
		{
			if (i <= 25)
				column1.add(cards.get(i - 1));
			else if (i <= 27)
				column2.add(cards.get(i - 1));
			else if (i <= 30)
				column3.add(cards.get(i - 1));
			else if (i <= 34)
				column4.add(cards.get(i - 1));
			else if (i <= 39)
				column5.add(cards.get(i - 1));
			else if (i <= 45)
				column6.add(cards.get(i - 1));
			else if (i <= 52)
				column7.add(cards.get(i - 1));
		}

		column1.get(column1.size() - 1).setVisible();
		column2.get(column2.size() - 1).setVisible();
		column3.get(column3.size() - 1).setVisible();
		column4.get(column4.size() - 1).setVisible();
		column5.get(column5.size() - 1).setVisible();
		column6.get(column6.size() - 1).setVisible();
		column7.get(column7.size() - 1).setVisible();
	}

	public ArrayList<String> getColumn(ArrayList<Card> column)
	{
		ArrayList<String> s;
		
		s = new ArrayList<String>();
		
		
		for (int i = 0; i < column.size(); i++)
		{
			String[] data;
			if (i == (column.size() - 1))
				data = column.get(i).toString().split("\n");
			else
				data = column.get(i).littleCard().split("\n");
			for (int j = 0; j < data.length; j++)
				s.add(data[j]);
		}
		
		return s;
	}
	
	public ArrayList<String> getSign(ArrayList<Card> sign)
	{
		ArrayList<String> s = new ArrayList<String>();
		String[] data;
		
		if (sign.size() > 0)
		{
			data = sign.get(sign.size() - 1).toString().split("\n");
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
	
	public ArrayList<String> getDeck()
	{
		ArrayList<String> s = new ArrayList<String>();
		String[] data;
		
		if (this.deck.size() > 0)
		{
			data = this.deck.get(this.deck_position).toString().split("\n");
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
	
	public String printLine()
	{
		String s = "";
		ArrayList<String> heart = getSign(this.hearts);
		ArrayList<String> spade = getSign(this.spades);
		ArrayList<String> diamond = getSign(this.diamonds);
		ArrayList<String> club = getSign(this.clubs);
		ArrayList<String> deck = getDeck();
		
		for (int i = 0; i < 4; i++)
		{
			s += deck.get(i) + "             ";
			s += heart.get(i) + " ";
			s += spade.get(i) + " ";
			s += diamond.get(i) + " ";
			s += club.get(i) + " ";
			s += "\n";
		}
		
		s+= "\n";
		
		ArrayList<String> column_1 = getColumn(this.column1);
		ArrayList<String> column_2 = getColumn(this.column2);
		ArrayList<String> column_3 = getColumn(this.column3);
		ArrayList<String> column_4 = getColumn(this.column4);
		ArrayList<String> column_5 = getColumn(this.column5);
		ArrayList<String> column_6 = getColumn(this.column6);
		ArrayList<String> column_7 = getColumn(this.column7);
		
		for (int i = 0; i < column_7.size(); i++)
		{
			if (i < column_1.size())
				s += column_1.get(i) + " ";
			else
				s += "      ";
			if (i < column_2.size())
				s += column_2.get(i) + " ";
			else
				s += "      ";
			if (i < column_3.size())
				s += column_3.get(i) + " ";
			else
				s += "      ";
			if (i < column_4.size())
				s += column_4.get(i) + " ";
			else
				s += "      ";
			if (i < column_5.size())
				s += column_5.get(i) + " ";
			else
				s += "      ";
			if (i < column_6.size())
				s += column_6.get(i) + " ";
			else
				s += "      ";
			if (i < column_7.size())
				s += column_7.get(i) + " ";
			else
				s += "      ";
			
			s += "\n";
		}
		
		return s;
	}

	public String toString()
	{
		String s = "";
		
		s += printLine();
		
		return s;
	}
}
