package monopoly.model;

import java.util.List;

import monopoly.square.Square;
import monopoly.square.SquareProvider;

/**
 * A subclass of Player specifically for human players. When this player makes a
 * move, it waits for the human to use the user interface. When the user clicks
 * the "finished" button, finishMove causes it to stop waiting, allowing other
 * players to make their move.
 * 
 * @author Byron Weber Becker
 */
public class HumanPlayer extends Player {
	
	public HumanPlayer(int playerId, String name, boolean takingTurn, boolean isInJail, boolean bankrupt, int position, int money, List<Square> propertyList, Dice dice) {
		super(playerId, name, takingTurn, isInJail, bankrupt, position, money, propertyList, dice);
		Square square = SquareProvider.getInstance().getSquare(0);
		square.landOnSquare(this);
	}

	/**
	 * Make one move for this player by waiting for the human user to manipulate the
	 * user interface. Stop waiting when finishMove is called.
	 */
	protected synchronized void makeMove() {
		this.takingTurn = true;
		this.advanceToken();

		try {
			this.wait();
			// wait until finshMove is called; then resume execution here
		} catch (InterruptedException e) { // ignored
			e.printStackTrace();
			System.out.println("Interrupted Exception is occured...");
		}

		this.takingTurn = false;
		this.updateAllViews();
	}

	/** Finish waiting for the user to manipulate the user interface. */
	public synchronized void finishMove() {
		this.notify();
	}

	/**
	 * Confirm to the user interface that this player is taking a turn and controls
	 * in the UI should be enabled.
	 * 
	 * @return true if this user is taking its turn; false otherwise
	 */
	public boolean isTakingTurn() {
		return this.takingTurn;
	}
}
