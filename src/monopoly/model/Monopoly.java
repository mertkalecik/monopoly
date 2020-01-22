package monopoly.model;
import java.util.*;
import becker.io.*;
import becker.util.Test;
import monopoly.square.Square;
import monopoly.square.SquareProvider;
import monopoly.util.GameReader;


/** Monopoly is the model's central class.  It provides access to both
properties and players.  It also alternates moves between all the players.
@author Byron Weber Becker */
public class Monopoly extends Object
{
	private GameReader reader;
	private List<Square> propertyList;
	private List<Player> playerList;
	private Dice dice;
	private boolean isEnd;
   

   /** Construct a new instance of Monopoly. */
   public Monopoly()
   {   
	   super();
	   //propertyList = SquareProvider.getInstance().getAllSquares();
	   playerList = new ArrayList<>();
	   isEnd = false;
	   
	   init();
   }

   /** Load a game from the named file.
   @param fileName the name of the file containing the saved game */
   public void loadGame(String fileName)
   {  
	   reader = new GameReader(fileName);
	   playerList = reader.getPlayerList();
	   propertyList = SquareProvider.getInstance().getAllSquares();
	   System.out.println("reading from file");
   }
   
   public void init() {
	   dice = new RandomDice();
	   
	   propertyList = SquareProvider.getInstance().getAllSquares();

	   playerList.add(new HumanPlayer(1, "Mert", true, false, false, 0, GameConstants.INITIAL_MONEY, new ArrayList<>(), dice));
	   playerList.add(new HumanPlayer(2, "Kývanç", true, false, false, 0,  GameConstants.INITIAL_MONEY, new ArrayList<>(), dice));
	   playerList.add(new ComputerPlayer(3, "Ogulcan", true, false, false, 0,  GameConstants.INITIAL_MONEY, new ArrayList<>(), dice));
   }

   /** Play the game by giving each player a turn, in sequence, until
   all but one are bankrupt. */
	public void playGame()
   {  
		while(!isEnd) {
			if(playerList.size() >1) {
				for(int i = 0; i < playerList.size(); i++) {
					Player p = playerList.get(i);
					if(!p.isBankrupt())
						if(p.isInJail()) {
							p.setInJail(false);
							continue;
						}
						else
							p.makeMove();
					else if(playerList.size() > 2)
							playerList.remove(p);
						else {
							System.out.println("Oyun bitti... Kazanan:" + p.getName());
							playerList.remove(p);
							isEnd = true;
						}	
				}
			}
		}
   }

   /** Make available the number of squares in the game.
   @return the number of squares in the game. */
   public int getNumSquares() {  
	   return propertyList.size();
   }

   /** Make available the number of players in the game.
   @return the number of players in the game. */
   public int getNumPlayers()
   {  
    return playerList.size();
   }

   /** Get a Square.
   @param boardPosition the position of the Square to get
      ("Go" = position 0, "Mediterranean Ave" = position 1, etc.)
   @return the Square at the given position */
   public Square getSquare(int boardPosition)
   {  
	   return propertyList.get(boardPosition);
   }

   /** Get a Square.
   @param name the name of the Square to get
      ("Go", "Mediterranean Ave", etc.)
   @return the Square with the given name */
   public Square getSquare(String name)
   {
	   Square square = null;
	   
	   for(Square s: propertyList) {
		   if(s.getName() == name) {
			   square = s;
		   }
	   }
	   
    return square;
   }

   /** Make available a particular player.
   @param id the id number of the player to get
   @return the player with the given id */
   public Player getPlayer(int id)
   {  
    return playerList.get(id);
   }

   

   /** Represent this object as a String. Useful for debugging. */
   public String toString()
   {  
	   return null;
   }

   /** A main method to test Monopoly, Player and Square classes.
   Errors print to the console. A correct run will only print "Done testing." */
   /*public static void main(String[] args)
   {
      Monopoly m = new Monopoly();
      Square sq = m.getSquare(0);
      Test.ckEquals("Name of Go", "Go", sq.getName());

      Player pl = m.getPlayer(0);
      Test.ckEquals("1st player id", 0, pl.getID());;

      sq = m.getSquare(0);
      Test.ckEquals("Num players on square", 4, sq.getPlayers().length);

      sq = m.getSquare(1);
      Test.ckEquals("First prop name", "Mediterranean Ave", sq.getName());
      Test.ckEquals("Num players on first prop", 0, sq.getPlayers().length);

      sq = m.getSquare(m.getNumSquares()-1);
      Test.ckEquals("Last prop name", "Boardwalk", sq.getName());
      Test.ckEquals("Num players on last prop", 0, sq.getPlayers().length);

      pl = m.getPlayer(0);
      Test.ckEquals("initial net worth", 1500, pl.getNetWorth());
      Test.ckEquals("initial balance", 1500, pl.getBalance());
      Test.ckEquals("initial num owned prop", 0, pl.getOwnedPropertyNames().length);

      pl.advanceToken(1);
      Square op = m.getSquare(pl.getBoardPosition());
      
      Test.ckEquals("occupied square name", "Mediterranean Ave", op.getName());
      Test.ckEquals("can buy occuppied square", true, pl.canBuyOccupiedSquare());
      pl.buyOccupiedSquare();
      Test.ckEquals("can buy purchased square (f)", false, pl.canBuyOccupiedSquare());
      Test.ckEquals("balance after purchase", 1440, pl.getBalance());
      Test.ckEquals("net worth after purchase", 1500, pl.getNetWorth());

      m = new Monopoly();
      m.loadGame("../testGame1.txt");
      sq = m.getSquare("Baltic Ave");
      Test.ckEquals("prop name read from file", "Baltic Ave", sq.getName());
      pl = m.getPlayer(0);
      Test.ckEquals("player name read from file", "Fred", pl.getName());
      Test.ckEquals("owned prop 1", "Reading RR", pl.getOwnedPropertyNames()[0]);
      Test.ckEquals("owned", "Baltic Ave", pl.getOwnedPropertyNames()[1]);

      System.out.println("Done testing.");
   }*/
   
}



