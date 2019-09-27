package edu.udel.dianali.cardgame;

import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import edu.udel.dianali.gameframework.Position;

/**
 * Created by wangpeiyu on 4/25/17.
 */
public class Card
{
    private int suit;
    //1-spade 2-heart 3-diamond 4-club
    private int number;
    //A-1, J-11, Q-12, K-13

    public Card()
    {
        Random generator = new Random();
        suit = generator.nextInt(4)+1;
        number = generator.nextInt(13)+1;
    }

    public Card(int s, int n)
    {
        suit = s;
        number = n;
    }

    public boolean sameColor (Card c)
    {
        if(suit == c.suit)
        {return true;}
        else if((suit == 1&&c.suit == 4) || (suit == 4&& c.suit == 1))
        {return true;}
        else if((suit == 2&&c.suit == 3) || (suit == 3&& c.suit ==2))
        {return true;}
        else
        {return false;}
    }
    public boolean sameNumber (Card c)
    {
        return this.number == c.number;
    }

    public int getSuit() {return suit;}
    public int getNumber() {return number;}
    public void setSuit(int s) {suit = s;}
    public void setNumber(int n) {number = n;}

    public void newRandomCard()
    {
        int newSuit, newNumber;
        Random generator = new Random();
        newSuit = generator.nextInt(4)+1;
        newNumber = generator.nextInt(13)+1;

        this.suit = newSuit;
        this.number = newNumber;
    }

    public String toString()
    {
        if(suit == 1)
        {return "(S" + number + ")";}
        if (suit == 2)
        {return "(H" + number + ")";}
        if (suit == 3)
        {return "(D" + number + ")";}
        if (suit == 4)
        {return "(C" + number + ")";}
        else
        {return "Error";}
    }
}