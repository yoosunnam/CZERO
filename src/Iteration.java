
public class Iteration {

	private Closure closure;
	private int iteration;
	
	public Iteration(Closure closure, int i) {
		this.closure = closure;
		this.iteration = i;
	}
	
	
	/* Getter */
	public Closure getClosure() {
		return closure;
	}
	
	public int getIteration() {
		return iteration;
	}
}
