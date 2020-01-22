package monopoly.square;

import monopoly.model.Monopoly;
import monopoly.model.Player;

public class BuyableSquare extends Square implements Buyable {
	private Player owner;
	private int price;
	private int rent;
	private boolean hasOwner;
	
	
	public BuyableSquare(String name, String description, int price) {
		super(name, description);
		this.price = price;
		this.rent = price/6;
		hasOwner = false;
	}
	
	
	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getCost() {
		return price;
	}
	
	@Override
	public int getRent() {
		return rent;
	}

	@Override
	public void setOwner(Player owner) {
		this.owner = owner;
		setHasOwner(true);
	}

	@Override
	public boolean isHasOwner() {
		return hasOwner;
	}

	@Override
	public void setHasOwner(boolean hasOwner) {
		this.hasOwner = hasOwner;
	}

	@Override
	public void doAction(Player player, Monopoly model) {
		player.checkPlayerIsBunkrupted(getCost());
		if(!isHasOwner()) {
			if(player.canBuyOccupiedSquare()) {
				setOwner(player);
				player.substractMoney(getCost());
			}	
		} else {
			if(getOwner() != player)
				player.substractMoney(getRent());
		}
	}

}
