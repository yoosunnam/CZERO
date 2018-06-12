import java.util.ArrayList;

public class LR0Item {
	
	private Symbol from;
	private ArrayList<Symbol> to;
	
	private int markIndex;
	
	/* Constructor */
	public LR0Item() {
	}
	
	public LR0Item(ProductionRule pr, int index) {
		this.from = pr.getFrom();
		this.to = pr.getTo();
		this.markIndex = index;
	}
	
	public LR0Item(Symbol from, ArrayList<Symbol> to, int id, int index) {
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
	 * Get LR0Item in String
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
		
		return s;
	}
	
	/* Getter */
	public Symbol getFrom() {
		return this.from;
	}
	
	public ArrayList<Symbol> getTo() {
		return this.to;
	}
	
	
}
