import java.util.ArrayList;

public class ProductionRule {
	
	private String rule;
	
	private Symbol from;
	private ArrayList<Symbol> to;
	
	
	/* Constructor */
	public ProductionRule() {
	}
	
	public ProductionRule(String rule, int id) {
		this.rule = rule;
		
		this.parseRule();
	}
	
	/* Private Function */
	private void parseRule() {
		String[] parserule = rule.split(">");
		
		this.from = new Symbol(parserule[0].charAt(0));
		
		this.to = new ArrayList<Symbol>();
		for (int i = 0; i < parserule[1].length(); i++)
			to.add(new Symbol(parserule[1].charAt(i)));
	}
	
	
	
	/* Getter */
	public Symbol getFrom() {
		return this.from;
	}
	
	public char getFromSymbol() {
		return this.from.getSymbol();
	}
	
	public ArrayList<Symbol> getTo() {
		return this.to;
	}
	
	public char getToSymbolofIdx(int i) {
		return this.to.get(i).getSymbol();
	}
	
	public String getStringRule() {
		return rule;
	}
	
}
