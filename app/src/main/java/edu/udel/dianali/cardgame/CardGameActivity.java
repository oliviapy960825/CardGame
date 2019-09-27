package edu.udel.dianali.cardgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.udel.dianali.gameframework.Action;
import edu.udel.dianali.gameframework.Game;
import edu.udel.dianali.gameframework.GameListener;
import edu.udel.dianali.gameframework.ModelUpdateController;

public class CardGameActivity extends Activity implements GameListener<CardGame>
{
    private TextView status;
    private CardGameView gameView;
    private CardGame game;
    private ModelUpdateController modelUpdateController;

    // Magneto game instances are not created in a factory method, instead
    // they are parsed from a file. To save on effort, we parse the game
    // levels once and store a prototype game. When we want to start a new
    // game we call a deep copy method on the prototype.

    private CardGame prototype;

    private static final int GAMETYPE_HUMAN = 1;

    private int gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = new TextView(this);
        gameView = new CardGameView(this);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(status);
        ll.addView(gameView);

        startGame();

        setContentView(ll);
    }

    public CardGame getCurrentGame() {
        return game;
    }

    /**
     * Looks for levels in the assets folder under assets/levels
     */
    private void startGame() {

        game = CardGame.makeDefaultStartGame();
        game.addGameListener(this);
        game.start();
        modelUpdateController = new ModelUpdateController(game, gameView, 40, 1);

        CardGameUser human = new CardGameUser(this);
        gameView.setOnTouchListener(human);

        modelUpdateController.start();
    }

    public void restartGame() {
        if (modelUpdateController != null) {
            modelUpdateController.stop();
        }
        if (game != null && game.getLifecycle() != Game.ENDED) {
            game.end();
        }
        startGame();
    }

    @Override
    public void onPerformActionEvent(Action<CardGame> action, CardGame game) {
        updateViews();
    }

    @Override
    public void onTickEvent(CardGame game) {
        updateViews();
    }

    @Override
    public void onStartEvent(CardGame game) {
        updateViews();
    }

    @Override
    public void onEndEvent(CardGame game) {
        updateViews();
    }

    @Override
    public void onEvent(String event, CardGame game) {
        updateViews();
    }

    @Override
    public void setOnTouchListener(CardGameUser cardGameUser) {
        updateViews();
    }


    @Override
    public void onUpdate(CardGame game, float time) {
        updateViews();
    }

    private void updateViews() {
        gameView.invalidate();

        status.setText(game.getStatus());
    }


    public boolean onCreateOptionsMenu(Menu menu){
        //menu.add("Demo");
        menu.add("Play Game");
        menu.add("Restart");
        menu.add("Quit");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        /*if (title.equals("Demo")) {
            gameType = GAMETYPE_AI;
            restartGame();
        }
        */
        if (title.equals("Play Game")) {
            gameType = GAMETYPE_HUMAN;
            restartGame();
        }
        else if (title.equals("Restart")) {
            // start a new game with the same players as previous game
            restartGame();
        }
        else if (title.equals("Quit")) {
            finish();
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        modelUpdateController.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        modelUpdateController.start();
    }


    public CardGameView getGameView() {
        return gameView;
    }

}

