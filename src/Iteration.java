import java.util.ArrayList;

public class Iteration {

	private ArrayList<LRItem> closures;			// Set of LRItem
	private ArrayList<String> closureString;	// Set of items in String
	
	public Iteration() {
	}
	
	public Iteration(ArrayList<LRItem> closures) {
		this.closures = closures;
		
		// Make items into String to check if there is same Iteration and to print
		this.closureString = new ArrayList<String>();
		for (LRItem cl : this.closures) {
			this.closureString.add(cl.getItemInString());
		}
	}
	
	/* Getter */

	public ArrayList<LRItem> getClosure() {
		return this.closures;
	}
	
	public ArrayList<String> getClosureString() {
		return this.closureString;
	}
}
