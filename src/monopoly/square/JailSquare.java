package monopoly.square;

import monopoly.model.Monopoly;
import monopoly.model.Player;

public class JailSquare extends Square {

	public JailSquare() {
		super("JAIL SQUARE", "JUST \nVISITING");
	}

	@Override
	public void doAction(Player player, Monopoly model) {
		player.setInJail(true);
	}
	
	

}
