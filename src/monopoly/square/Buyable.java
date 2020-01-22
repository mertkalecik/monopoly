package monopoly.square;

import monopoly.model.Player;

public interface Buyable {
	public Player getOwner();
	public int getCost();
	public int getRent();
	public void setOwner(Player owner);
	public boolean isHasOwner();
	public void setHasOwner(boolean hasOwner);
}
