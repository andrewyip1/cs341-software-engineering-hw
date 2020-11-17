package hw6;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class main {

	// Variables

	public static int totalLines = 0;
	public static int countComments = 0;
	public static int countBlankLines = 0;
	public static int finalLineCount = 0;
	public static int forLoopcount = 0;
	public static int whileLoopCount = 0;
	public static int ifStatementCount = 0;

	public static void main(String[] args) {

		// TASK 1: SELECT THE FILE AND STORE INTO STRING

		String filePath = chooseFile();

		// TASK 2: RUN TRY CATCH STATEMENT

		// SUBTASK A: IF SELECTED CANCEL PRINTS CANCEL, OTHERWISE RUNS FILE AND SCANS IT

		if (!filePath.equals("selected cancel")) {

			System.out.println("File path: " + filePath);

			LineNumberReader reader = null;

			try {

				// to read the file
				reader = new LineNumberReader(new FileReader(new File(filePath)));
				String str;

				// Read file till the end
				while ((str = reader.readLine()) != null) {

					// REMOVES ALL WHITE SPACES
					str = str.replaceAll("\\s+", "");

					totalLines++; // always increment the total number of lines
					// Empty Strings or Tabs
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
				}
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

			// Output:

			System.out.println("Total number of lines: " + totalLines);
			System.out.println("Number of lines with blanks: " + countBlankLines);
			System.out.println("Number of lines with comments: " + countComments);
			finalLineCount = totalLines - countBlankLines - countComments;
			System.out.println("Lines with 'for' loops in it: " + forLoopcount);
			System.out.println("Lines with 'while' loops in it: " + whileLoopCount);
			System.out.println("Lines with 'if' statements in it: " + ifStatementCount);
			System.out.println("Total number of lines without comments and blanks: " + finalLineCount);

		}

		else {
			System.out.println("CANCEL BUTTON WAS SELECTED.");
		}
	}

	/**
	 * This method is to selected the file that we want to count the lines for
	 * 
	 * @return This returns the path of the file that we choose
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
}
