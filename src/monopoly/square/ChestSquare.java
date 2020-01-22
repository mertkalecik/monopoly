package monopoly.square;

import java.util.Random;

import monopoly.model.Monopoly;
import monopoly.model.Player;

public class ChestSquare extends Square {

	public ChestSquare() {
		super("COMMUNITY CHEST", "COMMUNITY \nCHEST");
	}

	@Override
	public void doAction(Player player, Monopoly model) {
		Random random = new Random();
		player.addMoney(50 + random.nextInt(150));
	}

}
