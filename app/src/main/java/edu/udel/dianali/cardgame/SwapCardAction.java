package edu.udel.dianali.cardgame;

import android.view.MotionEvent;
import android.view.View;
import edu.udel.dianali.gameframework.Action;
import edu.udel.dianali.gameframework.Position;

public class SwapCardAction implements Action<CardGame> {
    public Position swapPosition;
    public SwapCardAction(Position swapPosition){
        this.swapPosition=swapPosition;
    }
    public Position getSwapPosition(){
        return swapPosition;
    }
    public void setSwapPosition(Position nextPosition) {
        swapPosition = nextPosition;
    }
    @Override
    public void update(CardGame game) {
        // Should we use exchangeCard method here?
        Position initPosition=game.getInitPosition();//at this point we have two Positions--initPosition and swapPosition
        game.checkExchangeCard(initPosition,swapPosition);
        //now after the exchange we need to reset the initPosition on the game
        game.setInitPosition(null);
    }
}
