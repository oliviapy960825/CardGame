package edu.udel.dianali.cardgame;

import junit.framework.TestCase;

/**
 * Created by Diana Li on 4/28/2017.
 */

public class CardGameTest extends TestCase
{
    public static CardGame createsGame()
    {
        Card[][] board =
                {       {new Card(1,10), new Card(2,10), new Card(2,8), new Card(2,7), new Card(4,1), new Card(4,10)},
                        {new Card(4,4), new Card(4,2), new Card(3,7), new Card(1,7), new Card(1,6), new Card(1,2)},
                        {new Card(2,6), new Card(2,6), new Card(2,4), new Card(1,4), new Card(3,4), new Card(4,6)},
                        {new Card(1,6), new Card(2,8), new Card(2,10), new Card(1,9), new Card(4,6), new Card(4,2)},
                        {new Card(3,6), new Card(2,4), new Card(4,8), new Card(2,3), new Card(4,5),new Card(3,6)},
                        {new Card(4,6), new Card(1,5), new Card(3,5), new Card (3,10), new Card (1,9), new Card(1,5)}};
        return new CardGame(board, 0, 60);
    }

    public void test_colColor()
    {
        CardGame testCardGame = createsGame();
        assertEquals(2, testCardGame.colColor(0));
        assertEquals(3, testCardGame.colColor(1));
        assertEquals(4, testCardGame.colColor(2));
        assertEquals(3, testCardGame.colColor(3));
        assertEquals(3, testCardGame.colColor(4));
        assertEquals(4, testCardGame.colColor(5));
    }

    public void test_rowColor()
    {
        CardGame testCardGame = createsGame();
        assertEquals(3, testCardGame.rowColor(0));
        assertEquals(3, testCardGame.rowColor(1));
        assertEquals(3, testCardGame.rowColor(2));
        assertEquals(3, testCardGame.rowColor(3));
        assertEquals(2, testCardGame.rowColor(4));
        assertEquals(2, testCardGame.rowColor(5));
    }

    public void test_colNumber()
    {
        CardGame testCardGame = createsGame();
        assertEquals(4, testCardGame.colNumber(0));
        assertEquals(1, testCardGame.colNumber(1));
        assertEquals(1, testCardGame.colNumber(2));
        assertEquals(2, testCardGame.colNumber(3));
        assertEquals(1,testCardGame.colNumber(4));
        assertEquals(1,testCardGame.colNumber(5));
    }

    public void test_rowNumber()
    {
        CardGame testCardGame = createsGame();
        assertEquals(2, testCardGame.rowNumber(0));
        assertEquals(2, testCardGame.rowNumber(1));
        assertEquals(3, testCardGame.rowNumber(2));
        assertEquals(1, testCardGame.rowNumber(3));
        assertEquals(1, testCardGame.rowNumber(4));
        assertEquals(2, testCardGame.rowNumber(5));
    }

    public void test_getSuit()
    {
        Card test = new Card(2,8);
        assertEquals(2, test.getSuit());
    }

    public void test_getNumber()
    {
        Card test = new Card(4,6);
        assertEquals(6, test.getNumber());
    }

    public void test_sameColor()
    {
        Card c12 = new Card(1,2);
        Card c43 = new Card(4,3);
        Card c36 = new Card (3,6);
        assertFalse(c12.sameColor(c36));
        assertTrue(c12.sameColor(c43));
    }

    public void test_sameNumber()
    {
        Card c16 = new Card(1,6);
        Card c43 = new Card(4,3);
        Card c36 = new Card (3,6);
        assertTrue(c16.sameNumber(c36));
        assertFalse(c16.sameNumber(c43));
    }

    public void test_toString()
    {
        Card c11 = new Card(1,1);
        Card c13 = new Card(1,3);
        Card c21 = new Card(2,1);
        assertEquals("(S1)", c11.toString());
        assertEquals("(S3)", c13.toString());
        assertEquals("(H1)", c21.toString());
    }
}
