import java.util.ArrayList;

public class Iteration {

	private ArrayList<LRItem> closures = new ArrayList<LRItem>();
	private ArrayList<String> closureString = new ArrayList<String>();
	
	
	public Iteration(ArrayList<LRItem> closures) {
		this.closures = closures;
		
		this.closureString = new ArrayList<String>();
		for (LRItem cl : closures) {
			closureString.add(cl.getStringItem());
		}
		
//		System.out.println();
//		for(String s : closureString) {
//			System.out.println(s);
//		}
		
	}
	
	/* Getter */
	public ArrayList<String> getClosureString() {
		return this.closureString;
	}
	public ArrayList<LRItem> getClosure() {
		return this.closures;
	}
}
