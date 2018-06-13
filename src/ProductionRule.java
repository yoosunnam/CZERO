import java.util.ArrayList;

public class ProductionRule {
	
	private Symbol from;			// Symbol before transformation
	private ArrayList<Symbol> to;	// String(Set of Symbol) after transformation
	
	
	/* Constructor */
	public ProductionRule() {
	}
	
	public ProductionRule(String rule) {
		// Parse String rule into from and to
		String[] parserule = rule.split(">");
		
		this.from = new Symbol(parserule[0].charAt(0));
		
		this.to = new ArrayList<Symbol>();
		for (int i = 0; i < parserule[1].length(); i++)
			this.to.add(new Symbol(parserule[1].charAt(i)));
	}
	
	
	
	/* Getter */
	
	public Symbol getFrom() {
		return this.from;
	}
	
	public ArrayList<Symbol> getTo() {
		return this.to;
	}
	
	// Get Symbol of first Symbol of to
	public char getToCharofIdx0() {
		return this.to.get(0).getSymbol();
	}
	
}
