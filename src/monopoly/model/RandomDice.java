package monopoly.model;

import java.util.Random;

/** Roll a pair of random dice.
@author Byron Weber Becker */
public class RandomDice extends Dice
{
	private Random random;
	
   /** Return a roll of the pair of dice.
   @return a number between 2 and 2*numSides */
	@Override
	public int getRoll() {
		random = new Random();
		diceOne = 1 + random.nextInt(6);
		diceTwo = 1 + random.nextInt(6);
		return diceOne + diceTwo;
	}
   
}
