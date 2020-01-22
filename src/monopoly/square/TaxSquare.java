package monopoly.square;

import monopoly.model.Monopoly;
import monopoly.model.Player;

public class TaxSquare extends Square {

	public TaxSquare() {
		super("INCOME TAX", "INCOME \nTAX");
	}

	@Override
	public void doAction(Player player, Monopoly model) {
			player.substractMoney(200);	
	}
	

}
