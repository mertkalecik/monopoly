package monopoly.square;

import java.util.ArrayList;
import java.util.List;

public class SquareProvider {
	
	private static SquareProvider instance;
	private List<Square> propertyList;
	
	private SquareProvider() {
		propertyList = new ArrayList<>();
		init();
	}
	
	public static SquareProvider getInstance() {
		if(instance == null)
			instance = new SquareProvider();
		
		return instance;
	}
	
	public Square getSquare(int position) {
		int pos = position % propertyList.size();
		return propertyList.get(pos);
	}
	
	public Square getSquareByName(String name) {
		for(Square s: propertyList) {
			if(s.getName() == name)
				return s;
		}
		return null;
	}
	
	public void setPropList(List<Square> list) {
		this.propertyList = list;
	}
	
	public int getPosition(Square s) {
		for(int i = 0; i < propertyList.size(); i++) {
			if(propertyList.get(i) == s)
				return i;
		}
		return 0;
	}
	
	private void init() {
		propertyList.clear();
		propertyList.add(new GoSquare());
		propertyList.add(new BuyableSquare("MEDITERRANEAN AVENUE", "MEDITERRANEAN\n AVENUE", 60));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("BALTIC AVENUE", "BALTIC \nAVENUE", 60));
		propertyList.add(new TaxSquare());
		propertyList.add(new BuyableSquare("HAYDAR PASHA RAILROAD", "HAYDAR \nPASHA \nRAILRIAD", 200));
		propertyList.add(new BuyableSquare("ORIENTAL AVENUE", "ORIENTAL \nAVENUE", 100));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("VERMONT AVENUE", "VERMONT \nAVENUE", 100));
		propertyList.add(new BuyableSquare("CONNECTICUT AVENUE", "CONNECTICUT \nAVENUE", 120));
		propertyList.add(new JailSquare());
		propertyList.add(new BuyableSquare("ST. CHARLES PLACE", "ST. CHARLES \nPLACE", 140));
		propertyList.add(new BuyableSquare("ELECTRIC COMPANY", "ELECTRIC\n COMPANY", 150));
		propertyList.add(new BuyableSquare("STATES AVENUE", "STATES \nAVENUE", 140));
		propertyList.add(new BuyableSquare("VIRGINIA AVENUE", "VIRGINIA \nAVENUE", 160));
		propertyList.add(new BuyableSquare("ALSANCAK RAILROAD", "ALSANCAK\n RAILROAD", 200));
		propertyList.add(new BuyableSquare("ST. JAMES PLACE", "ST. JAMES \nPLACE", 180));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("TENESSEE AVENUE", "TENESSEE \nAVENUE", 180));
		propertyList.add(new BuyableSquare("NEWYORK AVENUE", "NEWYORK \nAVENUE", 200));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("KENTUCKY AVENUE", "KENTUCKY \nAVENUE", 220));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("INDIANA AVENUE", "INDIANA \nAVENUE", 220));
		propertyList.add(new BuyableSquare("ILLINOIS AVENUE", "ILLINOIS \nAVENUE", 240));
		propertyList.add(new BuyableSquare("ANKARA RAILROAD", "ANKARA \nRAILROAD", 200));
		propertyList.add(new BuyableSquare("ATLANTIC AVENUE", "ATLANTIC \nAVENUE", 260));
		propertyList.add(new BuyableSquare("VETINOR AVENUE", "VETINOR \nAVENUE", 260));
		propertyList.add(new BuyableSquare("WATER WORKS", "WATER \nWORKS", 150));
		propertyList.add(new BuyableSquare("MARVIN GARDENS", "MARVIN \nGARDENS", 280));
		propertyList.add(new JailSquare());
		propertyList.add(new BuyableSquare("PACIFIC AVENUE", "PACIFIC \nAVENUE", 300));
		propertyList.add(new BuyableSquare("NORTH CALIFIRNIA AVENUE", "NORTH CALIFORNIA\n AVENUE", 300));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("PENSILVANIA AVENUE", "PENSILVANIA \nAVENUE", 320));
		propertyList.add(new BuyableSquare("PARIS RAILROAD", "PARIS \nRAILROAD", 200));
		propertyList.add(new ChestSquare());
		propertyList.add(new BuyableSquare("PARK PLACE", "PARK \nPLACE", 350));
		propertyList.add(new TaxSquare());
		propertyList.add(new BuyableSquare("BOARDEAUX", "BOARDEAUX", 400));
	}

	public List<Square> getAllSquares() {
		return propertyList;
	}
	

}
