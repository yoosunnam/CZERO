import java.util.ArrayList;

public class ProductionRule {
	
	private String rule;
	
	private Symbol from;
	private ArrayList<Symbol> to;
	
	private int id;
	private boolean visited;
	
	/* Constructor */
	public ProductionRule() {
	}
	
	public ProductionRule(String rule, int id) {
		this.rule = rule;
		this.id = id;
		this.visited = false;
		
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
	
	
	/* Setter */
	public void checkVisited() {
		this.visited = true;
	}
	
	public void resetVisited() {
		this.visited = false;
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
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public String getStringRule() {
		return rule;
	}
	
}
