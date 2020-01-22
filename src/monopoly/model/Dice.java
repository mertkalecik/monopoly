package monopoly.model;


/** An class to be inherited from to create various kinds of dice.
@author Byron Weber Becker */
public abstract class Dice
{
	protected int diceOne;
	protected int diceTwo;	
	
   public Dice()
   {
	   this.diceOne = 0;
	   this.diceTwo = 0;
   }

   /** Get a roll of the "dice"
   @return a random number */
   abstract int getRoll();
}
