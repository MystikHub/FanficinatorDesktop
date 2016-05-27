package fanficinate;

import java.util.Random;

public class Fanfic {
	public String fanfic;
	private Random rand = new Random();
	public String[] Alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public String Fanficinate() {
		String[] words = fanfic.split("\\s+");
		String[] newWords = new String[words.length];		
		for(int i = 0; i < words.length; i++) {
			int randomOperationDecider = (int) (Math.random()*3);
			System.out.println(randomOperationDecider);
			if (randomOperationDecider == 0) {	
				newWords[i] = randomizeLetterPositions(words[i]) + " ";
			}
			
			if (randomOperationDecider == 1) {
				newWords[i] = addRandomLetter(words[i]) + " ";
			}
			
			if (randomOperationDecider == 2) {
				newWords[i] = words[i] + " ";
			}
		}
		StringBuilder str = new StringBuilder();
		for(String s : newWords) {
			System.out.println(s);
		    str.append(s);
		}
		return str.toString();
			
	}
	
	private String randomizeLetterPositions(String word) {
		int randomLetter = rand.nextInt(word.length());
		int randomPosition = rand.nextInt(word.length());
		
		// Swap the letters
		char[] charWord = word.toCharArray();
		
		char myLetter = charWord[randomLetter];
		char myPositionLetter = charWord[randomPosition];
		
		charWord[randomPosition] = myLetter;
		charWord[randomLetter] = myPositionLetter;
		
		System.out.println(charWord);
		
		String newWord = new String(charWord);
		
		return newWord;
	}
	
	private String addRandomLetter(String Word) {
		int letterPosition = (int) (Math.random()*Word.length());
		StringBuilder str =  new StringBuilder(Word);
		str.insert(letterPosition, Alphabet[(int) Math.random()*26 ]);
		return str.toString();
	}
}
