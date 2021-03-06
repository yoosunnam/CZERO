public class Symbol {
	
	private char symbolChar;
	private boolean terminal;
	
	
	/* Constructor */
	public Symbol() {
	}
	
	public Symbol(char symbolc) {
		this.symbolChar = symbolc;
		
		//If symbol is Capital, then it is non-terminal
		if ('A' <= this.symbolChar && this.symbolChar <= 'Z')
			this.terminal = false;	// false if non-terminal
		else
			this.terminal = true;	// true if terminal
	}
	
	
	/* Getter */
	public char getSymbol() {
		return this.symbolChar;
	}
	
	public boolean isNonTerminal() {
		return !this.terminal;
	}
	

}
