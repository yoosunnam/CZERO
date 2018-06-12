
public class Symbol {
	
	private char symbolChar;
	private boolean terminal;
	
	
	/* Constructor */
	public Symbol() {
	}
	
	public Symbol(char symbolc) {
		this.symbolChar = symbolc;
		
		if ('A' <= symbolc && symbolc <= 'Z')
			terminal = false;
		else
			terminal = true;
	}
	
	
	/* Getter */
	public char getSymbol() {
		return this.symbolChar;
	}
	
	public boolean isNonTerminal() {
		return !this.terminal;
	}
	

}
