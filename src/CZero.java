import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CZero {
	private ArrayList<String> rules;
	private ArrayList<String[]> result;
	
	private ArrayList<ProductionRule> productionRules;
	private ArrayList<Iteration> iteration;
	
	/* Constructor */
	public CZero () {
		this.rules = new ArrayList<String>();
		this.result = new ArrayList<String[]>();
		
		this.productionRules = new ArrayList<ProductionRule>();
		this.iteration = new ArrayList<Iteration>();
	}
	
	/* Public Function */
	public void implementCzero() {
		this.makeProductionRule();
		
		
	}
	
	
	/* Private Function */
	private void makeProductionRule() {
		// Make Starting Rule
		rules.add(0, "S>" + rules.get(0).substring(0, 1));
		
		// Store production rules
		for (int i = 0; i < rules.size(); i++)		
			productionRules.add(new ProductionRule(rules.get(i), i));
		
		for(ProductionRule pr : productionRules) {
			System.out.println(pr.getFrom().getSymbol());
		}
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
		FileWriter fw = null ;
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
