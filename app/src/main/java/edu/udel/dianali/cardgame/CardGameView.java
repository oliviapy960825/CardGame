package edu.udel.dianali.cardgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.View;

public class CardGameView extends View {

    protected CardGameActivity cardGameActivity;

    private Bitmap c11,c12,c13,c14,c15,c16,c17,c18,c19,c110;
    private Bitmap c21,c22,c23,c24,c25,c26,c27,c28,c29,c210;
    private Bitmap c31,c32,c33,c34,c35,c36,c37,c38,c39,c310;
    private Bitmap c41,c42,c43,c44,c45,c46,c47,c48,c49,c410;

    public CardGameView(CardGameActivity context) {
        super(context);
        cardGameActivity = context;
        setBackgroundColor(Color.GRAY);
        setFocusable(true);
        setFocusableInTouchMode(true);

        c11 = loadImage("c11"); c12 = loadImage("c12"); c13 = loadImage("c13"); c14 = loadImage("c14");
        c15 = loadImage("c15"); c16 = loadImage("c16"); c17 = loadImage("c17"); c18 = loadImage("c18");
        c19 = loadImage("c19"); c110 = loadImage("c110");
        c21 = loadImage("c21"); c22 = loadImage("c22"); c23 = loadImage("c23"); c24 = loadImage("c24");
        c25 = loadImage("c25"); c26 = loadImage("c26"); c27 = loadImage("c27"); c28 = loadImage("c28");
        c29 = loadImage("c29"); c210 = loadImage("c210");
        c31 = loadImage("c31"); c32 = loadImage("c32"); c33 = loadImage("c33"); c34 = loadImage("c34");
        c35 = loadImage("c35"); c36 = loadImage("c36"); c37 = loadImage("c37"); c38 = loadImage("c38");
        c39 = loadImage("c39"); c310 = loadImage("c310");
        c41 = loadImage("c41"); c42 = loadImage("c42"); c43 = loadImage("c43"); c44 = loadImage("c44");
        c45 = loadImage("c45"); c46 = loadImage("c46"); c47 = loadImage("c47"); c48 = loadImage("c48");
        c49 = loadImage("c49"); c410 = loadImage("c410");
    }

    private Bitmap loadImage(String name)
    {
        return BitmapFactory.decodeResource(cardGameActivity.getResources(),
                cardGameActivity.getResources().getIdentifier(name, "drawable",
                        getClass().getPackage().getName()));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(
                getWidth() / 6,
                getHeight() / 6);
        drawCards(canvas);
        canvas.restore();
    }

    /**
     * Return the correct card image based on the card's suit and number
     * This function will be called in drawCards
     * @param card
     * @return
     */
    public Bitmap cardImage (Card card)
    {
        if(card.getSuit() == 1)
        {
            if(card.getNumber() == 1) {return c11;}
            else if(card.getNumber() == 2) {return c12;}
            else if(card.getNumber() == 3) {return c13;}
            else if(card.getNumber() == 4) {return c14;}
            else if(card.getNumber() == 5) {return c15;}
            else if(card.getNumber() == 6) {return c16;}
            else if(card.getNumber() == 7) {return c17;}
            else if(card.getNumber() == 8) {return c18;}
            else if(card.getNumber() == 9) {return c19;}
            else {return c110;}
        }
        else if(card.getSuit() == 2)
        {
            if(card.getNumber() == 1) {return c21;}
            else if(card.getNumber() == 2) {return c22;}
            else if(card.getNumber() == 3) {return c23;}
            else if(card.getNumber() == 4) {return c24;}
            else if(card.getNumber() == 5) {return c25;}
            else if(card.getNumber() == 6) {return c26;}
            else if(card.getNumber() == 7) {return c27;}
            else if(card.getNumber() == 8) {return c28;}
            else if(card.getNumber() == 9) {return c29;}
            else {return c210;}
        }
        else if(card.getSuit() == 3)
        {
            if(card.getNumber() == 1) {return c31;}
            else if(card.getNumber() == 2) {return c32;}
            else if(card.getNumber() == 3) {return c33;}
            else if(card.getNumber() == 4) {return c34;}
            else if(card.getNumber() == 5) {return c35;}
            else if(card.getNumber() == 6) {return c36;}
            else if(card.getNumber() == 7) {return c37;}
            else if(card.getNumber() == 8) {return c38;}
            else if(card.getNumber() == 9) {return c39;}
            else {return c310;}
        }
        else
        {
            if(card.getNumber() == 1) {return c41;}
            else if(card.getNumber() == 2) {return c42;}
            else if(card.getNumber() == 3) {return c43;}
            else if(card.getNumber() == 4) {return c44;}
            else if(card.getNumber() == 5) {return c45;}
            else if(card.getNumber() == 6) {return c46;}
            else if(card.getNumber() == 7) {return c47;}
            else if(card.getNumber() == 8) {return c48;}
            else if(card.getNumber() == 9) {return c49;}
            else {return c410;}
        }
    }

    RectF rectF = new RectF();
    private void setRectFromPosition(int row, int column) {
        rectF.set(column, row, column+1f, row+1f);
    }

    public void drawCards(Canvas canvas)
    {
        Card[][] board = cardGameActivity.getCurrentGame().getCurrentBoard();

        for(int i=0;i<6;i++)
        {
            for(int j=0; j<6;j++)
            {
                setRectFromPosition(i,j);
                canvas.drawBitmap(cardImage(board[i][j]), null, rectF, null);
            }
        }
    }
}