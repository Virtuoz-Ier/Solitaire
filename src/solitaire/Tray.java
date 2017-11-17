package solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class Tray
{
	private ArrayList<ArrayList<Card>> columns;
	private ArrayList<Card> deck;
	private ArrayList<ArrayList<Card>> signs;
	private int deck_position;
	private int nbTurns;
	
	public Tray()
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		String signe = "";
		
		for (int i = 1; i <= 4; i++)
		{
			if (i == 1)
				signe = "heart";
			else if (i == 2)
				signe = "diamond";
			else if (i == 3)
				signe = "spade";
			else if (i == 4)
				signe = "club";
			
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
		this.nbTurns = 0;
		
		this.signs = new ArrayList<ArrayList<Card>>();
		for (int i = 0; i < 4; i++)
			this.signs.add(new ArrayList<Card>());
		
		this.columns = new ArrayList<ArrayList<Card>>();
		for (int i = 0; i < 7; i++)
			this.columns.add(new ArrayList<Card>());
		
		int start = 24;
		
		for (int j = 0; j < 7; j++)
		{
			for (int i = start; i < start + j + 1; i++)
			{
				this.columns.get(j).add(cards.get(i));
			}
			start = start + j + 1;
		}
	
		this.setVisible();
	}
	
	public void setVisible()
	{
		for (int i = 0; i < columns.size(); i++)
		{
			if (this.columns.get(i).size() != 0)
				this.columns.get(i).get(this.columns.get(i).size() - 1).setVisible();
		}
			
	}
	
	public ArrayList<Card> getColumn(int nbColumn)
	{
		return this.columns.get(nbColumn);
	}
	
	public ArrayList<ArrayList<Card>> getAllColumns()
	{
		return this.columns;
	}
	
	public ArrayList<Card> getDeck()
	{
		return this.deck;
	}
	
	public int getDeckPosition()
	{
		return this.deck_position;
	}
	
	public ArrayList<Card> getSign(int nbSign)
	{
		return this.signs.get(nbSign);
	}
	
	public ArrayList<ArrayList<Card>> getAllSigns()
	{
		return this.signs;
	}
	
	public int getNbTurns()
	{
		return this.nbTurns;
	}
	
	public void upDeck()
	{
		if (this.deck_position == this.deck.size() - 1)
			this.deck_position = 0;
		else
			this.deck_position++;
	}
	
	public void upNbTurns()
	{
		this.nbTurns++;
	}
	
	public boolean moveToColumn(int nbCards, ArrayList<Card> column, ArrayList<Card> columnToMove, boolean fromDeck)
	{
		if (column.size() == 0)
			return false;
		
		int index;
		if (!fromDeck)
			index = column.size() - nbCards;
		else
			index = this.deck_position;
		
		Card firstCardToMove =  column.get(index);
		
		if (columnToMove.size() == 0)
		{
			if (firstCardToMove.getValue() == 13)
			{
				for (int i = index; i < index + nbCards; i++)
				{
					columnToMove.add(column.get(i));
				}
				for (int i = index; i < index + nbCards; i++)
				{
					column.remove(i);
				}
				this.setVisible();
				this.nbTurns++;
				return true;
			}
			else
				return false;
		}
		else
		{
			Card lastCardColumnToMove =  columnToMove.get(columnToMove.size() - 1);
			
			if (checkMoveToColumn(firstCardToMove, lastCardColumnToMove))
			{
				for (int i = index; i < index + nbCards; i++)
				{
					columnToMove.add(column.get(i));
				}
				for (int i = index; i < index + nbCards; i++)
				{
					column.remove(i);
				}
				this.setVisible();
				this.nbTurns++;
				return true;
			}
				
			return false;
		}
	}
	
	public boolean moveToSign(ArrayList<Card> column, ArrayList<Card> signToMove, boolean fromDeck)
	{
		if (column.size() == 0)
			return false;

		int index;
		if (!fromDeck)
			index = column.size() - 1;
		else
			index = this.deck_position;
		
		Card cardToMove =  column.get(index);
		
		if (signToMove.size() == 0)
		{
			if (cardToMove.getValue() == 1)
			{
				signToMove.add(cardToMove);
				this.upDeck();
				column.remove(index);
				this.setVisible();
				this.nbTurns++;
				return true;
			}
			else
				return false;
		}
		else
		{
			Card lastCardSignToMove =  signToMove.get(signToMove.size() - 1);
			
			if (checkMoveToSign(cardToMove, lastCardSignToMove))
			{
				signToMove.add(cardToMove);
				this.upDeck();
				column.remove(index);
				this.setVisible();
				this.nbTurns++;
				return true;
			}
				
			return false;
		}
	}
	
	public String[] getOppositeSigns(Card card)
	{
		if (card.getSign().equals("heart") ||  card.getSign().equals("diamond"))
			return new String[] {"spade", "club"};
		else
			return new String[] {"heart", "diamond"};
	}
	
	public boolean checkMoveToColumn(Card cardToMove, Card lastCardColumn)
	{
		if (cardToMove.getSign().equals(getOppositeSigns(lastCardColumn)[0]) || cardToMove.getSign().equals(getOppositeSigns(lastCardColumn)[1]))
			if (cardToMove.getValue() == (lastCardColumn.getValue() - 1))
				return true;

		return false;
	}
	
	public boolean checkMoveToSign(Card cardToMove, Card lastCardSign)
	{
		if (cardToMove.getSign().equals(lastCardSign.getSign()))
			if (cardToMove.getValue() == (lastCardSign.getValue() + 1))
				return true;

		return false;
	}
	
	public boolean checkWin()
	{
		for (int i = 0; i < 4; i++)
			if (signs.get(i).size() != 13)
				return false;
		
		return true;
	}
}
