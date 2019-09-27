package edu.udel.dianali.gameframework;

import java.util.ArrayList;
import java.util.List;



/**
 * This class has some code to handle a basic listener
 * pattern. Game models update typically with two kinds of events:
 * actions that are performed on the game by a player, and
 * time updates. Both of these updates generate a call
 * to methods on each of the GameListener interfaces that are
 * listening to events on this game.
 *
 * @author jatlas
 */
// generics are nice but cause a lot of extra confusing code, so I
// have made the choice to use them sparingly, so I need to suppress
// the type warnings so you don't see a lot of yellow lines in Eclipse.
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class Game implements Model {
    // Lifecycle constants
    public static final int CREATED = 0;
    public static final int STARTED = 3;
    public static final int ACTIONED = 6;
    public static final int ENDED = 10;
    
    private List<GameListener> listeners;

    private long lastUpdateTime;

    // keeps track of the lifecycle of a game
    private int lifecycle;

    private long ticks;
    
    /**
     * Allows subclasses to not have to call a specific Game constructor.
     * Creates an empty List to store listeners in.
     */
    protected Game() {
        listeners = new ArrayList<GameListener>();
        lifecycle = CREATED;
    }
    
    /**
     * Adds the given listener to this game. Callbacks will be made
     * to the event methods on the listener from this game.
     * 
     * @param listener
     */
    public void addGameListener(GameListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given listener from this game.

     * @param listener
     * @return true if the given listener was actually listening
     *   to this game, false otherwise
     */
    public boolean removeGameListener(GameListener listener) {
        return listeners.remove(listener);
    }

    /**
     * It is expected that the Game return a status
     * or String representation of its current state
     */
    public abstract String toString();
    
    /**
     * Performs an action on the game.
     * 
     * @param action
     */
    public final void perform(Action action) {
        if (lifecycle == STARTED) {
            lifecycle = ACTIONED;
        }
        if (lifecycle == ACTIONED) {
            onPerformAction(action);
            
            for (GameListener listener : listeners) {
                listener.onPerformActionEvent(action, this);
            }

        }
    }

    /**
     * Returns 1 to limit the Game to calling onTick only once per call to update.
     * Return Integer.MAX_VALUE if you want a model to be ticked more than once per update if it
     * needs to. Generally returning Integer.MAX_VALUE makes for a more accurate model degredation
     * when tick length falls below the speed of rendering. Returning false makes for a less
     * accurate to time model degredation, but will allow listeners (and the view) to be
     * updated on each tick.
     * @return
     */
    protected int maxTicksPerUpdate() {
        return 1;
    }
    
    /**
     * Marked as final to prevent overriding. Subclasses must
     * put time-based logic in their onUpdate method. This ensures
     * that listeners are notified after every update.
     */
    public final void update(float time) {
        if (lifecycle >= STARTED && lifecycle <= ACTIONED) {
            long now = System.currentTimeMillis();

            // this produces not quite smooth ticks, but errs on the side of consistency
            // for example, a tick length of 10 and an actual update time of 12
            // will result in one tick every 12 milliseconds which is preferable
            // to 1 tick each of the first 4 x 12 millisecond periods and then 2
            // updates in the 5th period
            int tickThisUpdate = 0;
            while (getTickLength() > 0 && now >= lastUpdateTime + getTickLength()
                    && tickThisUpdate < maxTicksPerUpdate()) {
                onTick();
                for (GameListener listener : listeners) {
                    listener.onTickEvent(this);
                }
                ticks++;
                lastUpdateTime += getTickLength();
                tickThisUpdate++;
            }
            lastUpdateTime = now;

            // either a game ticks or it updates, not both.
            // if a game really wants to do both then it overrides this method, calls super.update
            // and then calls onUpdate itself
            if (tickThisUpdate == 0) {
                onUpdate(time);
                for (GameListener listener : listeners) {
                    listener.onUpdate(this, time);
                }
            }
        }
    }
    
    /**
     * Marked as final to prevent overriding. Subclasses must
     * put start logic in their onStart method. This ensures
     * that listeners are notified of the start.
     */
    public final void start() {
        if (lifecycle == CREATED) {
            lifecycle = STARTED;
            lastUpdateTime = System.currentTimeMillis();

            onStart();
            for (GameListener listener : listeners) {
                listener.onStartEvent(this);
            }
        }
    }

    /**
     * We can manually end games.
     */
    public final void end() {
        if (lifecycle != ENDED) {
            lifecycle = ENDED;

            onEnd();
            for (GameListener listener : listeners) {
                listener.onEndEvent(this);
            }
        }
    }
    
    /**
     * Returns the current Lifecycle stage of the game.
     * See the constants in this class.
     * 
     * @return
     */
    public int getLifecycle() {
        return lifecycle;
    }

    /**
     * This is the method to override if you need to do something
     * different than the default update for the Action.
     */
    protected void onPerformAction(Action action) {
        action.update(this);
    }
    
    /**
     * This is the method to override for game specific start
     * logic.
     */
    protected void onStart() {
    }
    
    /**
     * This is the method to override for game specific end
     * logic.
     */
    protected void onEnd() {
    }

    /**
     * This is the method to override for game specific time based logic.
     */
    protected void onUpdate(float time) {
    }

    /**
     * This is the method to override for game specific tick based logic.
     * NOTE: Your Game subclass must also override getTickLength() and return a value > 0 otherwise
     * this method will not be called.
     */
    protected void onTick() {
    }
    
    /**
     * Broadcasts a custom event String to listeners.
     * 
     * @param event
     */
    public void broadcastEvent(String event) {
        for (GameListener listener : listeners) {
            listener.onEvent(event, this);
        }
    }
    
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Allows for games that tick instead of continuously update. Override this method and return
     * a value > 0 and your onTick() method will automatically be called when the correct amount
     * of time passes.
     *
     * @return
     */
    public long getTickLength() {
        return 0;
    }

    /**
     * Returns number of ticks for games that use ticks
     * @return
     */
    public long getTicks() {
        return ticks;
    }


}
