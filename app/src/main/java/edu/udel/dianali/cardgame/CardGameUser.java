package edu.udel.dianali.cardgame;

import android.view.MotionEvent;
import android.view.View;
import edu.udel.dianali.gameframework.Game;
import edu.udel.dianali.gameframework.Position;

public class CardGameUser implements View.OnTouchListener {
    public CardGameActivity cardGameActivity;
    public CardGameView cardGameView;

    public CardGameUser(CardGameActivity cardGameActivity) {
        this.cardGameActivity = cardGameActivity;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        CardGame game = cardGameActivity.getCurrentGame();
        if (game != null) {
            // is the game ended? if so restart it!
            if (game.getLifecycle() == Game.ENDED) {
                //cardGameActivity.restartGame();
            }
            /* rewrite else if condition
            else if (action == MotionEvent.ACTION_DOWN) {
                game.perform(new SwitchPolarityAction());
            }
            */
            else if (action == MotionEvent.ACTION_DOWN) {
                Position p=new Position((event.getX()/v.getWidth())*CardGame.WORLD_WIDTH,(event.getY()/v.getHeight())*CardGame.WORLD_HEIGHT);
                if(game.getInitPosition()==null){
                    game.perform(new SelectCardAction(p));
                }
                else{
                    game.perform(new SwapCardAction(p));
                }
            }
        }
        // we don't need any more events in this sequence
        return false;
    }
}

