package monopoly.square;

import java.util.*;
import becker.util.IView;
import monopoly.model.Monopoly;
import monopoly.model.Player;

/**
 * A class representing one square on a monopoly board. A Square can be a
 * building lot such as "Vermont Ave" or "Park Place". It can also be a
 * railroad, "Go", Chance, the jail, and so on.
 * 
 * @author Byron Weber Becker
 */
public abstract class Square extends Object {
	private IView[] views = new IView[1];
	private int numViews = 0;
	private String name;
	private String description;
	private List<Player> players;

	public Square(String name, String description) {
		this.name = name;
		this.description = description;
		players = new ArrayList<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void landOnSquare(Player p) {
		if (!players.contains(p))
			players.add(p);
		
		if(this instanceof BuyableSquare)
			p.checkPlayerIsBunkrupted(((BuyableSquare)this).getCost());
		
		if(this instanceof JailSquare || this instanceof TaxSquare || this instanceof ChestSquare)
			this.doAction(p, null);
		
		updateAllViews();
	}

	public void leftSquare(Player p) {
		if (players.contains(p))
			players.remove(p);
		
		updateAllViews();
	}
	
	/**
	 * Get a description of this square.
	 * 
	 * @return a String description of this square
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get a list of the players currently occupying this property.
	 * 
	 * @return a full array with the 0 or more players who have most recently landed
	 *         on this property
	 */
	public List<Player> getPlayersList() {
		return players;
	}
	
	public Player[] getPlayers() {
		Player[] plyrs = new Player[players.size()];
		for(int i = 0; i< players.size(); i++) {
			plyrs[i] = players.get(i);
		}
		return plyrs;
	}
	/**
	 * Add another view to be notified whenever this part of the model changes.
	 * 
	 * @param view the view to add
	 */
	public void addView(IView aView) {
		views[0] = aView;
	}

	/** Update all the views registered with this part of the model. */
	protected synchronized void updateAllViews() {
		for(int i = 0; i< views.length; i++) {
			if(views[i] != null)
				views[i].updateView();
		}
	}
	
	public abstract void doAction(Player player, Monopoly model);

}
