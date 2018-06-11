import java.util.ArrayList;

public class LR0Item {
	
	private Symbol from;
	private ArrayList<Symbol> to;
	private int id;
	
	private int markIndex;
	private boolean end;
	
	/* Constructor */
	public LR0Item() {
	}
	
	public LR0Item(ProductionRule pr, int index) {
		this.from = pr.getFrom();
		this.to = pr.getTo();
		this.id = pr.getID();
		this.markIndex = index;
		this.end = false;
	}
	
	public LR0Item(Symbol from, ArrayList<Symbol> to, int id, int index) {
		this.from = from;
		this.to = to;
		this.id = id;
		this.markIndex = index;
		this.end = false;
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
	
	/* Setter */
	public void setEnd() {
		this.end = true;
	}
	
	/* Getter */
	public Symbol getFrom() {
		return this.from;
	}
	
	public ArrayList<Symbol> getTo() {
		return this.to;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isEnd() {
		return end;
	}
	
	
}
