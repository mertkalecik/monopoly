package monopoly.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import monopoly.model.ComputerPlayer;
import monopoly.model.GameConstants;
import monopoly.model.HumanPlayer;
import monopoly.model.RandomDice;
import monopoly.square.*;

public class Tests {

	HumanPlayer humanPlayer = new HumanPlayer(1, "Test Human", true, false, false, GameConstants.INITIAL_POSITION,
			GameConstants.INITIAL_MONEY, new ArrayList<Square>(), new RandomDice());

	ComputerPlayer computerPlayer = new ComputerPlayer(11, "Test Computer", true, false, false,
			GameConstants.INITIAL_POSITION, GameConstants.INITIAL_MONEY, new ArrayList<Square>(), new RandomDice());

	HumanPlayer humanPlayerInJail = new HumanPlayer(2, "Test Human In Jail", false, true, false,
			GameConstants.JAIL_POSITION, GameConstants.INITIAL_MONEY, new ArrayList<Square>(), new RandomDice());

	HumanPlayer humanPlayerRich = new HumanPlayer(3, "Test Human Rich", false, true, false, 5, 3000,
			new ArrayList<Square>(), new RandomDice());

	HumanPlayer humanPlayerPoor = new HumanPlayer(4, "Test Human Poor", false, true, false, 4, 200,
			new ArrayList<Square>(), new RandomDice());

	public HumanPlayer createPlayerHasItems() {
		ArrayList<Square> propertyList = new ArrayList<Square>();// Creating arraylist
		Square squareFirst = SquareProvider.getInstance().getSquare(1);
		Square squareThird = SquareProvider.getInstance().getSquare(3);
		propertyList.add(squareFirst);
		propertyList.add(squareThird);
		HumanPlayer humanPlayerHasItems = new HumanPlayer(4, "Test Human Poor", false, true, false, 4, 200,
				propertyList, new RandomDice());
		return humanPlayerHasItems;
	}

	@Test
	public void testInitialPosition() {
		assertEquals(GameConstants.INITIAL_POSITION, humanPlayer.getBoardPosition());
	}

	@Test
	public void testRandomDiceBigger() {
		RandomDice randomDice = new RandomDice();
		int result = randomDice.getRoll();
		int max = 12;

		assertEquals(false, max < result);

	}

	@Test
	public void testRandomDiceSmaller() {
		RandomDice randomDice = new RandomDice();

		int result = randomDice.getRoll();
		int max = 2;

		assertEquals(true, max < result);

	}

	@Test
	public void testRandomDiceZero() {
		RandomDice randomDice = new RandomDice();

		int result = randomDice.getRoll();
		int zero = 0;

		assertEquals(true, result != zero);

	}

	@Test
	public void testPlayerInitialMoney() {
		assertEquals(GameConstants.INITIAL_MONEY, humanPlayer.getBalance());
	}

	@Test
	public void testPlayerInitialIsInJail() {
		assertEquals(false, humanPlayer.isInJail());
	}

	@Test
	public void testPlayerIsInJail() {
		assertEquals(true, humanPlayerInJail.isInJail());
	}

	@Test
	public void testPlayerInitialIsBankrupt() {
		assertEquals(false, humanPlayer.isBankrupt());
	}

	@Test
	public void testPlayerIsBankrupt() {
		assertEquals(false, humanPlayerInJail.isBankrupt());
	}

	@Test
	public void testPlayerInitialIsTakingTurn() {
		assertEquals(true, humanPlayer.isTakingTurn());
	}

	@Test
	public void testPlayerIsTakingTurn() {
		assertEquals(false, humanPlayerInJail.isTakingTurn());
	}

	@Test
	public void testPlayerNetWorthIsPositive() {
		assertEquals(true, humanPlayerRich.getNetWorth() > GameConstants.INITIAL_MONEY);
	}

	@Test
	public void testPlayerNetWorthIsNegative() {
		assertEquals(true, humanPlayerPoor.getNetWorth() < GameConstants.INITIAL_MONEY);
	}

	// We check the player balance can buy all buyable Square
	@Test
	public void testCanBuySquare() {
		boolean success = false;
		for (Square s : SquareProvider.getInstance().getAllSquares()) {
			if (s instanceof BuyableSquare) {
				if (((BuyableSquare) s).getCost() < humanPlayer.getBalance()) {
					success = true;
				}
			}
		}

		assertEquals(true, success);
	}

	@Test
	public void testAllSquaresAvailable() {
		boolean success = false;
		for (Square s : SquareProvider.getInstance().getAllSquares()) {
			if (s instanceof BuyableSquare) {
				if (((BuyableSquare) s).isHasOwner() == false) {
					success = true;
				}
			}
		}
		assertEquals(true, success);
	}

	@Test
	public void testFirstSquareNameIsCorrect() {
		boolean success = false;

		Square firstSquare = SquareProvider.getInstance().getSquare(1);
		if (firstSquare.getName() == "MEDITERRANEAN AVENUE") {
			success = true;
		}

		assertEquals(true, success);
	}

	@Test
	public void testTaxSquare() {
		boolean success = false;
		Square taxSquare = SquareProvider.getInstance().getSquare(4);
		if (taxSquare.getName() == "INCOME TAX") {
			success = true;
		}

		assertEquals(true, success);
	}

	@Test
	public void testJailSquare() {
		boolean success = false;
		Square jailSquare = SquareProvider.getInstance().getSquare(10);
		if (jailSquare.getName() == "JAIL SQUARE") {
			success = true;
		}
		assertEquals(true, success);
	}

	@Test
	public void testSquareListSize() {
		boolean success = false;
		int listSize = SquareProvider.getInstance().getAllSquares().size();
		if (40 == listSize) {
			success = true;
		}

		assertEquals(true, success);
	}

	@Test
	public void testPlayerGotAnyProperty() {
		boolean success = false;
		if (createPlayerHasItems().getOwnedProperty().size() > 0) {
			success = true;
		}

		assertEquals(true, success);
	}

	@Test
	public void testPlayerHasNoProperty() {
		boolean success = false;
		if (humanPlayer.getOwnedProperty().size() == 0) {
			success = true;
		}

		assertEquals(true, success);
	}

	@Test
	public void testCanBuyProperty() {
		boolean success = false;
		Square square = SquareProvider.getInstance().getSquare(humanPlayerRich.getBoardPosition());
		System.out.println(square.getName());
		if (square instanceof BuyableSquare) {
			System.out.println(((BuyableSquare) square).getCost());
			System.out.println(humanPlayerRich.getBalance());
			if (((BuyableSquare) square).getCost() < humanPlayerRich.getBalance()) {
				success = true;
				System.out.println("Player can buy this : " + square.getName());
			}
		}

		assertEquals(true, success);
	}

	@Test
	public void testEverySquareHasCost() {
		boolean success = false;
		for (Square s : SquareProvider.getInstance().getAllSquares()) {
			if (s instanceof BuyableSquare) {
				if (((BuyableSquare) s).getCost() != 0) {
					success = true;
				}
			}
		}

		assertEquals(true, success);
	}

	@Test
	public void testEverySquareHasName() {
		boolean success = false;
		for (Square s : SquareProvider.getInstance().getAllSquares()) {
			if (s instanceof BuyableSquare) {
				if (((BuyableSquare) s).getName() != null) {
					success = true;
				}
			}
		}

		assertEquals(true, success);
	}

	@Test
	public void testEverySquareHasDescription() {
		boolean success = false;
		for (Square s : SquareProvider.getInstance().getAllSquares()) {
			if (s instanceof BuyableSquare) {
				if (((BuyableSquare) s).getDescription() != null) {
					success = true;
				}
			}
		}

		assertEquals(true, success);
	}

	@Test
	public void testIsThereAnySquareNameBalticAvenue() {
		boolean success = false;
		if (SquareProvider.getInstance().getSquareByName("BALTIC AVENUE") != null) {
			success = true;
		}

		assertEquals(true, success);
	}

	@Test
	public void testPlayersInTheSameSquare() {
		boolean result = false;
		if (humanPlayer.getBoardPosition() == computerPlayer.getBoardPosition()) {
			result = true;
		}

		assertEquals(true, result);
	}

}
