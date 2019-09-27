package edu.udel.dianali.gameframework;

/**
 * An Action is a command pattern that wraps a mutating method call.
 * Instead of directly calling a mutator such as setPiece(int row, int column, char piece),
 * a player can represent the call in a SetPieceAction where the update(G) method calls
 * the setPiece(int row, int column, char piece) method on the game model G.
 * This allows us to reason about the action and to let GameListeneres know when an
 * action has been performed on the game.
 * 
 * @author jatlas
 * 
 * @param <G>
 */
public interface Action<G extends Game> {
    /**
     * Mutates state of the game according to the properties
     * of this Action
     * 
     * @param game
     */
    public void update(G game);
}
