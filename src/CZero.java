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

		this.iteration = null;
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

		ArrayList<LRItem> items = new ArrayList<LRItem>();
		items = getClosureSet(start);

		// Print all closures
		for (LRItem item : items)
			System.out.println(item.getStringItem());
		
		doGOTO(items);
//		int i = 0;
//		do {
//			System.out.println("\nI" + i);
//			doGOTO(items);
//			i++;
//		} while(i < iteration.size());
	}

	private void doGOTO(ArrayList<LRItem> origins) {
		itemMap = new LinkedHashMap<Character, ArrayList<LRItem>>();

		// Make same Mark Symbol as a set
		for (LRItem item : origins) {
			if (!item.isEnd()) {
				char startSymbol = item.getMarkSymbol().getSymbol();

				ArrayList<LRItem> gotoItems = itemMap.get(startSymbol);
				if (gotoItems == null)
					gotoItems = new ArrayList<LRItem>();

				item.addMarkIndex(); // add mark index 1
				gotoItems.add(item);
				itemMap.put(startSymbol, gotoItems);
			}
		}

		Set<Character> keySet = itemMap.keySet();
		Iterator<Character> iter = keySet.iterator();

		// Iterate all markSymbols
		while (iter.hasNext()) {
			Character key = (Character) iter.next();
			getClosure(itemMap.get(key), key);	// Get closure for certain markSymbol
		}

	}

	private void getClosure(ArrayList<LRItem> origins, char markSymbol) {
		ArrayList<LRItem> items = new ArrayList<LRItem>();

		for (LRItem item : origins)
			items.addAll(getClosureSet(item));

		System.out.println("\n<<Enterd getClosure>> : " + markSymbol);
		for (LRItem item : items) {
			System.out.println(item.getStringItem());
		}

		// Iteration에 들어갈 수 있는 지 체크
		if (iteration == null) {
			this.iteration = new ArrayList<Iteration>();
			this.iteration.add(new Iteration(items));
		}
//		else {
//			int sameThing = 0;
//			boolean isSame = false;
//
//			for (Iteration it : iteration) {
//				sameThing = 0;
//				ArrayList<LRItem> itItems = it.getClosure();
//
//				if (itItems.size() == items.size()) {
//					for (LRItem itItem : itItems) {
//						for (LRItem item : items) {
//							if (itItem.getStringItem().equals(item.getStringItem()))
//								sameThing++;
//						}
//					}
//				}
//
//				if (sameThing == itItems.size()) {
//					isSame = true;
//					break;
//				}
//			}
//
//			if (!isSame) {
//				this.iteration.add(new Iteration(items));
////				for (LRItem item : items)
////					System.out.println(item.getStringItem());
//			}
//		}
//		

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

				if (pr.getToSymbolofIdx(0) != ms) // If start of To is not same with ms (to prevent infinite loop)
					items.addAll(getClosureSet(item)); // Get all closures of item and add all
				else // If start of To i same with ms
					items.add(item); // just add to itemList
			}
		}

		return items;
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

		System.out.println("Write File");
		try {
			fw = new FileWriter(outputFileName);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < result.size(); i++) {
				bw.write("I" + i);
				bw.newLine();
				for (int j = 0; j < result.get(i).length; j++)
					bw.write("[" + result.get(i)[j] + "]");
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
