package monopoly.model;

import becker.util.IView;
import monopoly.square.BuyableSquare;
import monopoly.square.Square;
import monopoly.square.SquareProvider;

import java.util.List;

import becker.io.TextInput;

/**
 * A class representing one monopoly player.
 * 
 * @author Byron Weber Becker
 */
public abstract class Player extends Object {
	private IView[] views = new IView[1];
	private int numViews = 0;
	private static Dice dice;
	protected int playerId;
	protected String name;
	protected boolean takingTurn = false;
	protected boolean isInJail;
	protected boolean bankrupt;
	protected int position;
	protected int money;
	protected List<Square> propertyList;

	public Player(int playerId, String name, boolean takingTurn, boolean isInJail, boolean bankrupt, int position,
			int money, List<Square> propertyList, Dice dice) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.takingTurn = takingTurn;
		this.isInJail = isInJail;
		this.bankrupt = bankrupt;
		this.position = position;
		this.money = money;
		this.propertyList = propertyList;
		Player.dice = dice;
	}

	/*
	 * Used to determine if we are debugging or not. Once this is determined, set
	 * the appropriate type of dice.
	 */
	private void determineDiceType() {
		/* Only needs to be called if we have not initialized dice. */
		if (Player.dice == null) {
			TextInput in = new TextInput();

			System.out.print("Do you want to debug? ");
			/* Get input from the user to determine whether debugging or not. */
			if (in.readLine().trim().equalsIgnoreCase("y")) {
			} else {
			}
			in.close();
		}
	}

	/**
	 * Get a list naming the properties this player has bought.
	 * 
	 * @return a full array giving the names of the properties owned by this player.
	 */
	public List<Square> getOwnedProperty() {
		return propertyList;
	}
	
	public String[] getOwnedPropertyNames() {
		String[] arr = new String[propertyList.size()];
		for(int i = 0; i < propertyList.size(); i++) {
			arr[i] = propertyList.get(i).getName();
		}
		
		return arr;
	}

	/**
	 * Get the name of this player.
	 * 
	 * @return the name of this player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the id of this player.
	 * 
	 * @return the id of this player
	 */
	public int getID() {
		return playerId;
	}

	/**
	 * Get the bank balance of this player.
	 * 
	 * @return this player's bank balance
	 */
	public int getBalance() {
		return money;
	}

	/**
	 * Get the net worth of this player.
	 * 
	 * @return this player's net worth
	 */
	public int getNetWorth() {
		int netWorth = this.money;
		for(Square s: propertyList) {
			netWorth += ((BuyableSquare)s).getCost();
		}
		return netWorth;
	}

	/**
	 * Indicate whether or not this player can buy the Square it most recently
	 * landed on.
	 * 
	 * @return true if the player can buy the Square; false otherwise
	 */
	public boolean canBuyOccupiedSquare() {
		SquareProvider provider = SquareProvider.getInstance();
		Square s = provider.getSquare(position);
		if(s instanceof BuyableSquare)
			if(((BuyableSquare) s).getCost() < money && ((BuyableSquare)s).getOwner() == null)
				return true;
		
		return false;
	}

	/**
	 * Instruct this player to buy a house.
	 * 
	 * @param propertyName the name of the square for which the player wants to
	 *                     purchase a house
	 */
	public void buyHouse(String propName) {
		Square s = SquareProvider.getInstance().getSquareByName(propName);
		propertyList.add(s);
	}
	
	public void buyHouse(Square prop) {
		propertyList.add(prop);
	}

	/** Buy the Square most recently landed on by this player. */
	public void buyOccupiedSquare() {
		Square s = SquareProvider.getInstance().getSquare(position);
		propertyList.add(s);
		s.doAction(this, null);
		updateAllViews();
	}
	
	public void checkPlayerIsBunkrupted(int price) {
		if(price > this.money)
			setBankrupt(true);
	}

	/** Roll the dice and advance the token. */
	protected void advanceToken() {
		advanceToken(dice.getRoll());
	}

	/**
	 * Advance the token the given number of properties.
	 * 
	 * @param howFar how many properties to advance
	 */
	protected void advanceToken(int howFar) {
		SquareProvider.getInstance().getSquare(position).leftSquare(this);
		this.position += howFar;
		Square newSquare = SquareProvider.getInstance().getSquare(position);
		newSquare.landOnSquare(this);
		updateAllViews();
	}

	/**
	 * Make a move for this player -- find out how far to advance the token, advance
	 * it, complete any resulting business such as paying rent or buying
	 * properties/houses. Must be overridden in subclasses.
	 */
	protected abstract void makeMove();

	/**
	 * Get the player's current position on the board.
	 * 
	 * @return 0 for "Go, 1 for "Mediterrainean Ave", etc.
	 */
	public int getBoardPosition() {
		return position;
	}

	public void substractMoney(int cost) {
		this.money -= cost;
		updateAllViews();
	}
	
	public void addMoney(int mny) {
		this.money += mny;
		updateAllViews();
	}
	
	public boolean isTakingTurn() {
		return takingTurn;
	}

	public void setTakingTurn(boolean takingTurn) {
		this.takingTurn = takingTurn;
	}

	public boolean isInJail() {
		return isInJail;
	}

	public void setInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}

	public boolean isBankrupt() {
		return bankrupt;
	}

	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
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
	protected void updateAllViews() {
		for(int i = 0; i < views.length; i++) {
			if(views[i] != null)
				views[i].updateView();
		}
	}

	/** Represent this object as a String. Useful for debugging. */
	public String toString() {
		return null;
	}

}
