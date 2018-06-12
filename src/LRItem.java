import java.util.ArrayList;

public class LRItem {
	
	private Symbol from;
	private ArrayList<Symbol> to;
	
	private int markIndex;
	
	/* Constructor */
	public LRItem() {
	}
	
	public LRItem(ProductionRule pr, int index) {
		this.from = pr.getFrom();
		this.to = pr.getTo();
		this.markIndex = index;
	}
	
	public LRItem(Symbol from, ArrayList<Symbol> to, int id, int index) {
		this.from = from;
		this.to = to;
		this.markIndex = index;
	}
	
	
	/* Public Functions */
	/**
	 * Get Mark Symbol
	 * @return Symbol, 
	 * 			but if markSymbol is at the end, return Symbol that consist of '.'
	 */
	public Symbol getMarkSymbol() {
		if (markIndex >= to.size())
			return new Symbol('.');
		else
			return to.get(markIndex);
	}
	
	/**
	 * Get LRItem in String
	 * @return String
	 */
	public String getStringItem() {
		String s = new String();
		
		s += from.getSymbol();
		s += "->";
		for (int i = 0; i < to.size(); i++) {
			if (i == markIndex)
				s += ".";
			s += to.get(i).getSymbol();
		}
		
		if (markIndex >= to.size())
			s += ".";
		
		return s;
	}
	
	public void addMarkIndex() {
		this.markIndex++;
	}
	
	/* Getter */
	public Symbol getFrom() {
		return this.from;
	}
	
	public char getFromSymbol() {
		return this.getFromSymbol();
	}
	
	public ArrayList<Symbol> getTo() {
		return this.to;
	}
	
	public char getToSymbolofIdx(int i) {
		return this.to.get(i).getSymbol();
	}
	
	
}
