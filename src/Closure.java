import java.util.ArrayList;

public class Closure{
	
	private LR0Item origin;
	private ArrayList<LR0Item> closures;
	
	
	public Closure(LR0Item origin, ArrayList<LR0Item> closures) {
		this.origin = origin;
		this.closures = closures;
	}
	
	
	/* Getter */
	public LR0Item getOrigin() {
		return origin;
	}
	
	public ArrayList<LR0Item> getClosures() {
		return closures;
	}
}
