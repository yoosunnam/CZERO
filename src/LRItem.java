import java.util.ArrayList;

public class LRItem {
	
	private Symbol from;			// Symbol before traInsformation
	private ArrayList<Symbol> to;	// Set of Symbol after transformation
	
	private int markIndex;			// Index of "." (Index of Mark Symbol)
	private boolean end;			// If "." is at the end 
	
	/* Constructor */
	public LRItem() {
	}
	
	public LRItem(ProductionRule pr) {
		this.from = pr.getFrom();
		this.to = pr.getTo();
		this.markIndex = 0;
		this.end = false;
	}
	
	
	/* Public Functions */
	/**
	 * Get Mark Symbol
	 * @return Symbol, 
	 * 			but if markSymbol is at the end, return Symbol that consist of '.'
	 */
	public Symbol getMarkSymbol() {
		if (end)
			return new Symbol('.');
		else
			return this.to.get(markIndex);
	}
	
	/**
	 * Get LRItem in String (form to write in file)
	 * @return String
	 */
	public String getItemInString() {
		String s = new String();
		
		s += "[";
		s += from.getSymbol();
		s += "->";
		for (int i = 0; i < to.size(); i++) {
			if (i == markIndex)
				s += ".";
			s += to.get(i).getSymbol();
		}
		
		if (markIndex >= to.size())
			s += ".";
		s += "]";
		
		return s;
	}
	
	/**
	 * Add MarkIndex 1 (Move "." to Next Symbol)
	 */
	public void addMarkIndex() {
		if (!end) 
			this.markIndex++;
		
		if (markIndex >= to.size())		// Check if markIndex is at the end
			this.end = true;
	}
	
	/**
	 * Check if MarkSymbol is at the end of item
	 * @return end
	 */
	public boolean isEnd() {
		return this.end;
	}
	
	
}
