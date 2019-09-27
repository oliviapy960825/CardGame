package edu.udel.dianali.cardgame;

import edu.udel.dianali.gameframework.Action;
import edu.udel.dianali.gameframework.Position;

public class SelectCardAction implements Action<CardGame> {
    public Position selectedPosition;
    public SelectCardAction(Position selectedPosition){
        this.selectedPosition=selectedPosition;
    }
    public Position getSelectedPosition(){return selectedPosition;}
    public void setPosition(Position nextPosition){selectedPosition=nextPosition;}
    @Override
    public void update(CardGame game) {
        game.setInitPosition(selectedPosition);

    }
    /* generate new card in the place where there's space?
    public void update(CardGame game,)
     */
}