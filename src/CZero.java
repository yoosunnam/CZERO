import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class CZero {
	private ArrayList<String> rules;
	private ArrayList<String[]> result;

	private ArrayList<ProductionRule> productionRules;
	private HashMap<Character, ArrayList<ProductionRule>> prMap;

	private LinkedHashMap<Character, ArrayList<LRItem>> itemMap;

	private ArrayList<Iteration> iteration;

	/* Constructor */
	public CZero() {
		this.rules = new ArrayList<String>();
		this.result = new ArrayList<String[]>();

		this.productionRules = new ArrayList<ProductionRule>();
		this.prMap = new HashMap<Character, ArrayList<ProductionRule>>();

		this.iteration = new ArrayList<Iteration>();
	}

	/* Public Function */
	public void implementCzero() {
		this.makeProductionRule();
		this.start();
	}

	/* Private Function */
	private void makeProductionRule() {
		// Make Starting Rule
		rules.add(0, "S>" + rules.get(0).substring(0, 1));

		// Store production rules
		for (int i = 0; i < rules.size(); i++)
			productionRules.add(new ProductionRule(rules.get(i), i));

		// Make same starting symbol as a set
		for (ProductionRule pr : productionRules) {
			char from = pr.getFromSymbol();

			ArrayList<ProductionRule> samepr = prMap.get(from);
			if (samepr == null)
				samepr = new ArrayList<ProductionRule>();

			samepr.add(pr);
			prMap.put(from, samepr);
		}
	}

	private void start() {
		LRItem start = new LRItem(productionRules.get(0), 0);
		
		ArrayList<LRItem> closures = new ArrayList<LRItem>();
		closures = getClosureSet(start);
		
		iteration.add(new Iteration(closures));
		
		int i = 0;
		do {
			doGOTO(iteration.get(i).getClosure());
			i++;
		} while(i < iteration.size());
		
		// Print
		i = 0;
		for (Iteration it : iteration) {
			ArrayList<String> sl = it.getClosureString();
			System.out.println("\n\nI" + i++);
			for (String s : sl)
				System.out.print(s + " ");
		}
		System.out.println();
	}

	private void doGOTO(ArrayList<LRItem> origins) {
		itemMap = new LinkedHashMap<Character, ArrayList<LRItem>>();

		// Make same Mark Symbol as a set
		for (LRItem item : origins) {
			if (!item.isEnd()) {	// If . is not at the end
				char startSymbol = item.getMarkSymbol().getSymbol();	// get mark symbol

				ArrayList<LRItem> gotoItems = itemMap.get(startSymbol);
				if (gotoItems == null)
					gotoItems = new ArrayList<LRItem>();

				item.addMarkIndex();	// add mark index 1
				gotoItems.add(item);
				itemMap.put(startSymbol, gotoItems);	// add key & arrayList
			}
		}
		
		Set<Character> keySet = itemMap.keySet();
		Iterator<Character> iter = keySet.iterator();

		// Iterate all mark symbols
		while (iter.hasNext()) {
			Character key = (Character) iter.next();
			getClosure(itemMap.get(key), key);	// Get closure for certain markSymbol
		}

	}

	private void getClosure(ArrayList<LRItem> origins, char markSymbol) {
		ArrayList<LRItem> items = new ArrayList<LRItem>();
		ArrayList<String> stringItem = new ArrayList<String>();
		
		// Get all the closures of origins
		for (LRItem item : origins)
			items.addAll(getClosureSet(item));
		
		for (LRItem item : items) {
			stringItem.add(item.getStringItem());
		}
		
		// Check if there is same Iteration element
		boolean isDiff = true;
		for (Iteration it : iteration) {
			ArrayList<String> closures = it.getClosureString();
			
			if (closures.size() == items.size()) {
				if (isEqual(stringItem, closures)) {
					isDiff = false;
					break;
				}
			}
		}
		
		if (isDiff) {
			iteration.add(new Iteration(items));
		}
			
	}

	/**
	 * Get all the closures of origin
	 * 
	 * @param origin
	 * @return ArrayList<LRItem> items
	 */
	private ArrayList<LRItem> getClosureSet(LRItem origin) {
		ArrayList<LRItem> items = new ArrayList<LRItem>();
		char ms = origin.getMarkSymbol().getSymbol();

		items.add(origin); // Add origin Item in List

		if (origin.getMarkSymbol().isNonTerminal()) { // If mark symbol is non-terminal

			ArrayList<ProductionRule> sameprs = prMap.get(ms); // Get all production rules that starts with ms

			for (ProductionRule pr : sameprs) {
				LRItem item = new LRItem(pr, 0);

				if (pr.getToSymbolofIdx(0) == ms)		// If start of To is same with ms (to prevent infinite loop)
					items.add(item); 					// just add to itemList
				else 									// If start of To is not same with ms
					items.addAll(getClosureSet(item));	// Get all closures of item and add all
			}
		}

		return items;
	}

	private boolean isEqual(ArrayList<String> items, ArrayList<String> saved) {
		for (int i = 0;  i < items.size(); i++) {
			if (!items.get(i).equals(saved.get(i)))
				return false;
		}
		return true;
	}
	
	
	/* Read and Write File */
	public void readFile(String inputFileName) {
		FileReader fr = null;
		BufferedReader br = null;

		System.out.println("Read File");
		try {
			fr = new FileReader(inputFileName);

			br = new BufferedReader(fr);

			String s = new String();

			while ((s = br.readLine()) != null) {
				s = s.replaceAll(" ", "");
				s = s.replaceAll("\t", "");
				s = s.replaceAll("\\p{Z}", "");

				if (s.contains(">"))
					this.rules.add(s);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Failed to read file.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
				}
		}
	}

	public void writeFile(String outputFileName) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		
		System.out.println();
		
		System.out.println("Write File");
		try {
			fw = new FileWriter(outputFileName);
			bw = new BufferedWriter(fw);

			int i = 0;
			for (Iteration it : iteration) {
				ArrayList<String> sl = it.getClosureString();
				bw.write("I" + i++);
				bw.newLine();
				for (String s : sl)
					bw.write(s);
				bw.newLine();
			}
			bw.flush();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to write file.");
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
				}
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
				}
		}
	}

	/* Main Function */
	public static void main(String[] args) {

		String inputFileName = new String("rule.txt");
		String outputFileName = new String("output.txt");

		CZero czero = new CZero();

		/* Open file and store rules in ArrayList<String> rule */
		czero.readFile(inputFileName);

		/* Implement C0 */
		czero.implementCzero();

		/* Write the results in output file */
		czero.writeFile(outputFileName);
	}

}
