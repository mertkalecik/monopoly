package monopoly.model;

import java.util.List;

import monopoly.square.Square;
import monopoly.square.SquareProvider;

/** A subclass of Player for a computer player */
public class ComputerPlayer extends Player {

	public ComputerPlayer(int playerId, String name, boolean takingTurn, boolean isInJail, boolean bankrupt, int position, int money, List<Square> propertyList, Dice dice) {
		super(playerId, name, takingTurn, isInJail, bankrupt, position, money, propertyList, dice);
		Square square = SquareProvider.getInstance().getSquare(0);
		square.landOnSquare(this);
	}

	public void makeMove() {
		this.takingTurn = true;
		this.advanceToken();
		try {
			Thread.sleep(1000);
			if(canBuyOccupiedSquare())
				buyOccupiedSquare();
			this.updateAllViews();
			this.takingTurn = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
