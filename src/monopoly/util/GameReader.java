package monopoly.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import monopoly.model.ComputerPlayer;
import monopoly.model.GameConstants;
import monopoly.model.HumanPlayer;
import monopoly.model.Player;
import monopoly.model.RandomDice;
import monopoly.square.BuyableSquare;
import monopoly.square.GoSquare;
import monopoly.square.Square;
import monopoly.square.SquareProvider;

public class GameReader {
	
	private List<Square> propertyList;
	private List<Player> playerList;
	private Scanner sc;
	
	public GameReader(String path) {
		propertyList = new ArrayList<>();
		playerList = new ArrayList<>();
		read(path);
	}
	
	private void read(String path) {
		File file = new File(path); 
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Dosya okuma baþarýsýz...");
			e.printStackTrace();
		} 
			  
		while (sc.hasNextLine()) {
			addObject(sc.nextLine());
		} 
	}
	
	private void addObject(String object) {
		switch(object.trim()) {
		case "BuildingLot":
			addBuildingLot();
			break;
		case "Go":
			addGo();
			break;
		case "RailRoad":
			addRailRoad();
			break;
		case "HumanPlayer":
			addPlayer(object.trim());
			break;
		case "ComputerPlayer":
			addPlayer(object.trim());
			break;
			
			default:
				break;
		}
	}
	
	private void addBuildingLot() {
		String name = getValue(sc.nextLine());
		String price = getValue(sc.nextLine());
		sc.nextLine();
		//String rent =  getValue(sc.nextLine());
		
		propertyList.add(new BuyableSquare(name, name, Integer.valueOf(price)));
	}
	
	private void addGo() {
		propertyList.add(new GoSquare());
		System.out.println("Go is added...");
	}
	
	private void addRailRoad() {
		String name = getValue(sc.nextLine());
		String price = getValue(sc.nextLine());
		
		propertyList.add(new BuyableSquare(name, name, Integer.valueOf(price)));
	}
	
	private void addPlayer(String type) {
		SquareProvider provider = SquareProvider.getInstance();
		provider.setPropList(propertyList);
		System.out.println(type);
		  
		  String name = getValue(sc.nextLine());
		  String balance = getValue(sc.nextLine());
		  Boolean bankrupt = Boolean.valueOf(getValue(sc.nextLine()));
		  String squareName = getValue(sc.nextLine());
		  int position = provider.getPosition(provider.getSquareByName(squareName));
		  
		  if(type.equals("HumanPlayer"))
			  playerList.add(new HumanPlayer(playerList.size()+1,
					  name,
					  false,
					  false,
					  bankrupt,
					  position,
					  Integer.valueOf(balance),
					  new ArrayList<Square>(),
					  new RandomDice()
					  ));
		  else 
			  playerList.add(new ComputerPlayer(playerList.size()+1,
					  name,
					  false,
					  false,
					  bankrupt,
					  position,
					  Integer.valueOf(balance),
					  new ArrayList<Square>(),
					  new RandomDice()
					  ));
	}
	
	private String getValue(String s) {
		String[] arr = s.split(":");
		return arr[1].trim();
	}
	
	public List<Square> getPropertyList() {
		return propertyList;
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}
}
