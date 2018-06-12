import java.util.ArrayList;

public class Iteration {

	private ArrayList<LRItem> closures;
	
	public Iteration(ArrayList<LRItem> closure) {
		this.closures = closure;
	}
	
	
	/* Getter */
	public ArrayList<LRItem> getClosure() {
		return closures;
	}
}
