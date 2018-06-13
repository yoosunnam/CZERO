import java.util.ArrayList;

public class Iteration {

	private ArrayList<LRItem> closures = new ArrayList<LRItem>();		// Set of LRItem
	private ArrayList<String> closureString = new ArrayList<String>();	// Set of items in String
	
	
	public Iteration(ArrayList<LRItem> closures) {
		this.closures = closures;
		
		// Make items into String to check if there is same Iteration and to print
		this.closureString = new ArrayList<String>();
		for (LRItem cl : closures) {
			closureString.add(cl.getIteminString());
		}
	}
	
	/* Getter */
	public ArrayList<String> getClosureString() {
		return this.closureString;
	}
	public ArrayList<LRItem> getClosure() {
		return this.closures;
	}
}
