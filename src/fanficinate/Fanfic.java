package fanficinate;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fanfic {
	public String fanfic;
	// Temporary alphabet for inserting random letters
	public String[] Alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	public JFrame mainJFrame;
	
	private Random rand = new Random();
	
	// Goes through the given fanfic and "improves" it
	public String Fanficinate() {
		// Split the fanfic by words
		String[] words = fanfic.split("\\s+");
		// Make a duplicate of the array to store the new fanfic
		String[] newWords = new String[words.length];
		
		// Loop through each word and fanficinate it
		for(int i = 0; i < words.length; i++) {
			// Decide how to fanficinate it by randomness
			int randomOperationDecider = rand.nextInt(2) + 1;
			System.out.println(randomOperationDecider);
			switch(randomOperationDecider) {
			case 1:
				// Randomize it
				newWords[i] = randomizeLetterPositions(words[i]) + " ";
				break;
			case 2:
				// Insert a random letter
				newWords[i] = addRandomLetter(words[i]) + " ";
				break;
			default:
				// Error message
				JOptionPane.showMessageDialog(mainJFrame, "Unknown fanficination method requested.");
			}
		}
		
		// Make a new StringBuilder for the result
		StringBuilder str = new StringBuilder();
		for(String s : newWords) {
			System.out.println(s);
			// Add each new word to the result
		    str.append(s);
		}
		
		return str.toString();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void showResult() {
		JFrame resultWindow = new JFrame("Processed Fanfic");
		resultWindow.setVisible(true);
		resultWindow.setBounds(mainJFrame.getX() + 100, mainJFrame.getY() + 100, 599, 300);
		TextArea resultTextArea = new TextArea(fanfic);
		resultTextArea.setEditable(false);
		resultTextArea.setBounds(0, 0, 599, 300);
		resultWindow.getContentPane().add(resultTextArea, BorderLayout.NORTH);
	}
	
	// Swaps two of the letters in the given word
	private String randomizeLetterPositions(String word) {
		// The first letter
		int randomLetter = rand.nextInt(word.length());
		// The second letter
		int randomPosition = rand.nextInt(word.length());
		
		// Get the characters of the word whose letters we're swapping
		char[] charWord = word.toCharArray();
		
		// Get the two letters
		char myLetter = charWord[randomLetter];
		char myPositionLetter = charWord[randomPosition];
		
		// Swap the letters
		charWord[randomPosition] = myLetter;
		charWord[randomLetter] = myPositionLetter;
		
		// For debugging purposes
		System.out.println(charWord);
		
		return new String(charWord);
	}
	
	// Inserts a new random letter into the given word
	private String addRandomLetter(String Word) {
		// The position we'll be inserting the new letter in
		int letterPosition = (int) (Math.random() * Word.length());
		
		StringBuilder newWord =  new StringBuilder(Word);
		// Insert the new letter
		newWord.insert(letterPosition, Alphabet[(int) Math.random()*26 ]);
		
		return newWord.toString();
	}
}
