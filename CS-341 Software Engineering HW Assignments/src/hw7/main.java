package hw7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class main {

	public static ArrayList<String> words = new ArrayList<>();
	public static HashMap<String, Integer> wordsValuesMap = new HashMap<>();
	public static HashMap<String, Integer> testValuesMap = new HashMap<>();
	public static HashMap<Character, Integer> letterValuesMap = new HashMap<>();
	public static HashMap<Character, Integer> gValuesMap = new HashMap<>();
	public static int totalLines = 0;
	public static int countComments = 0;
	public static int countBlankLines = 0;
	public static int finalLineCount = 0;
	public static int forLoopcount = 0;
	public static int whileLoopCount = 0;
	public static int ifStatementCount = 0;
	public static String filePath = "";
	public static String[] keywordArr = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "continue", "const", "default", "do", "double", "else", "enum", "exports", "extends", "final",
			"finally", "float", "for", "goto", "if", "implements", "imports", "instanced", "int", "interface", "long",
			"module", "native", "new", "package", "private", "protected", "public", "requires", "return", "short",
			"static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try",
			"var", "void", "volatile", "while" };
	public static HashMap<String, Integer> countingKeysinSourceCode = new HashMap<>();
	public static int g = 26; // to get fewer collisions
	public static int max = 0;

	public static void main(String[] args) {

		Stopwatch s1 = new Stopwatch();
		s1.start();
		CountLetters();
		initGValues();
		System.out.println("Letters Values Map (from keywords) - counting the # of occurances of the first and last letter " + letterValuesMap.toString());
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Words Values Map - calculating a value for each keyword using the first and last letter: " + wordsValuesMap.toString());
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Initial G Values Map " + gValuesMap.toString());
		System.out.println("Test Values Map " + testValuesMap.toString());
		testValuesMap = sortByValues(testValuesMap);
		System.out.println("SORTED Test Values Map " + testValuesMap.toString());
		

		// READING IN SOURCE CODE FILE
		readFile();
		lineCounter();

		countingKeysinSourceCode = sortByValues(countingKeysinSourceCode);
		s1.stop();
		System.out.println(
				"SORTED KeyWords by occurances in Java Source Code File: " + countingKeysinSourceCode.toString());

		// Output:
		if (!filePath.equals("selected cancel")) {
			System.out.println("\nTotal number of lines: " + totalLines);
			System.out.println("Number of lines with blanks: " + countBlankLines);
			System.out.println("Number of lines with comments: " + countComments);
			finalLineCount = totalLines - countBlankLines - countComments;
			System.out.println("Lines with 'for' loops in it: " + forLoopcount);
			System.out.println("Lines with 'while' loops in it: " + whileLoopCount);
			System.out.println("Lines with 'if' statements in it: " + ifStatementCount);
			System.out.println("Total number of lines without comments and blanks: " + finalLineCount + "\n");
			System.out.println("Elapsed Time: " + s1.getElapsedTime() + " milliseconds");
		}
	}

	/**
	 * This is to calculate "h"
	 * 
	 * @param word
	 * @return
	 */

	public static int h(String word) {

		char first = word.charAt(0);
		char last = word.charAt(word.length() - 1);
		return (word.length() + (g * first) + (g * last)) % keywordArr.length;
	}

	/**
	 * This method puts the letters in the letterValuesMap
	 */

	public static void CountLetters() {

		for (int i = 0; i < keywordArr.length; i++) {
			char first = keywordArr[i].charAt(0);
			char last = keywordArr[i].charAt(keywordArr[i].length() - 1);
			// first = Character.toLowerCase(first);

			// to check if the letterValueMap has the key of "first"
			if (!letterValuesMap.containsKey(first)) {
				letterValuesMap.put(first, 1);
			} else {
				letterValuesMap.put(first, letterValuesMap.get(first) + 1);
			}

			// to check if the letterValueMap has the key of "last"

			if (!letterValuesMap.containsKey(last)) {
				letterValuesMap.put(last, 1);
			} else {
				letterValuesMap.put(last, letterValuesMap.get(last) + 1);
			}

		} // end of for loop

		// sort the list ... highest values first

		letterValuesMap = sortByValues(letterValuesMap);

		// calculate the first+last word value

		for (int i = 0; i < keywordArr.length; i++) {
			char first = keywordArr[i].charAt(0);
			char last = keywordArr[i].charAt(keywordArr[i].length() - 1);
			int sum = letterValuesMap.get(first) + letterValuesMap.get(last);
			wordsValuesMap.put(keywordArr[i], sum);
		}

		wordsValuesMap = sortByValues(wordsValuesMap);

		// pick max value (max occurrence of a letter/2)

		max = letterValuesMap.get(letterValuesMap.keySet().iterator().next()) / 2;

	}

	/**
	 * This method returns boolean
	 * 
	 * @param wordStack
	 * @return
	 */

	public static boolean cichelli(Stack wordStack) {
		initGValues();
		while (!wordStack.isEmpty()) {
			String word = (String) wordStack.pop();
			char first = word.charAt(0);
			char last = word.charAt(word.length() - 1);
			int wordLength = word.length();
			if (!letterValuesMap.containsKey(first) && !letterValuesMap.containsKey(first)) {
			}
		}
		return false;
	}

	public static void initGValues() {

		// init g map
		for (char c : letterValuesMap.keySet()) {
			gValuesMap.put(c, 0);
		}
		
		for (String word : wordsValuesMap.keySet()) {
			testValuesMap.put(word, h(word)); 
		}
	}

	/**
	 * This sorts our HashMap by values
	 * 
	 * @param map
	 * @return sorted hashmap by values
	 */

	private static HashMap sortByValues(HashMap map) {

		List<?> list = new LinkedList(map.entrySet());

		// Defined Custom Comparator here

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap

		// using LinkedHashMap to preserve the insertion order

		HashMap sortedHashMap = new LinkedHashMap();
		for (java.util.Iterator it = list.iterator(); ((java.util.Iterator) it).hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

	/**
	 * This method reads the file
	 */

	public static void readFile() {
		filePath = chooseFile();
		System.out.println();
	}

	/**
	 * This method returns the path of the file that we choose
	 * 
	 * @return String path of file
	 */

	public static String chooseFile() {

		// file chooser
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		// FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES",
		// "txt", "text");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA FILES", "java", "java");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new java.io.File("/Users/andrewyip/eclipse-workspace/"));
		fc.setDialogTitle("Select a File: ");
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath(); // once you click open, this gets returned
		} else {
			fc.hide();
			return "selected cancel";
		}
	}

	public static void lineCounter() {

		if (!filePath.equals("selected cancel")) {

			System.out.println("SOURCE CODE FILE PATH: " + filePath);
			System.out.println("----------------------");

			LineNumberReader reader = null;

			try {

				// to read the file
				reader = new LineNumberReader(new FileReader(new File(filePath)));
				String str;

				// Read file till the end
				while ((str = reader.readLine()) != null) {

					str = str.replaceAll("\\s+", "");
					totalLines++;

					// GOING THROUGH LINE AND CHECKING TO SEE IF IT CONTAINS KEYWORD

					for (int i = 0; i < keywordArr.length; i++) {

						// CHECKING IF THE LINE HAS THE KEYWORD

						if (str.contains(keywordArr[i])) {

							// CHECKING IF IT IS IN THE HASHMAP

							if (countingKeysinSourceCode.containsKey(keywordArr[i])) { // check if exist in hashmap
								countingKeysinSourceCode.put(keywordArr[i],
										countingKeysinSourceCode.get(keywordArr[i]) + 1);
							} else {
								countingKeysinSourceCode.put(keywordArr[i], 1);
							}

						}
					}

					// MICELLANEOUS

					if (str.equals("")) { // tabs or spaces
						countBlankLines++;
					}
					// If the beginning is a regular comment '//'
					if ((str.length() >= 2) && (str.startsWith("//"))) {
						countComments++;
					}

					// For Loops, While Loops, If Statements (actual code not comments)

					if (str.contains("for")) {
						forLoopcount++;
					}

					if (str.contains("while")) {
						whileLoopCount++;
					}

					if (str.contains("if")) {
						ifStatementCount++;
					}

					// If the string has the starting element of a block comment
					else if (str.contains("/*")) {
						countComments++; // increment the comments

						// while loop to go through the next lines until you see the ending comment
						// block
						// stops when it sees the '*/'
						while (((str = reader.readLine()) != null) && !(str.endsWith("*/"))) {
							totalLines++;
							countComments++;
						}

						// so you would then need to increment it at the end
						totalLines++;
						countComments++;
					}

					// TO CHECK ALL THE ONES WITH 0 AS THE VALUES

					for (int i = 0; i < countingKeysinSourceCode.size(); i++) {
						for (int j = 0; j < keywordArr.length; j++) {
							if (!countingKeysinSourceCode.containsKey(keywordArr[i])) {
								countingKeysinSourceCode.put(keywordArr[i], 0);
							}
						}
					}
				}
				System.out.println(
						"UNSORTED Counting Keys in Source Code HashMap : " + countingKeysinSourceCode.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("CANCEL BUTTON WAS SELECTED.");
		}
	}
}