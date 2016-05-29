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
			//int randomOperationDecider = (int) (Math.random()*5);
			int randomOperationDecider = 1;
			if (randomOperationDecider == 0) {	
				newWords[i] = randomizeLetterPositions(words[i]) + " ";
			}
			
			if (randomOperationDecider == 1) {
				newWords[i] = addRandomLetter(words[i]) + " ";
			}
			
			if (randomOperationDecider == 2) {
				newWords[i] = removeRandomLetter(words[i]) + " ";
			}
			
			if (randomOperationDecider >= 3) {
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
		// Moves a random letter in the String forward or backwards one space
		int randomLetter = rand.nextInt(word.length());
		int setPosition = 0;
		if (word.length() >=2) {
			//Decide whether to move back 1 or forward 1
			switch((int) Math.random()*2) {
				case 0 : {
					setPosition = -1;
					break;
				}
				case 1 : {
					setPosition = 1;
				}
			}
			// Swap the letters
			char[] charWord = word.toCharArray();
			
			//Stop crashing if first letter tries to go back 1
			if (randomLetter == 0) {
				setPosition = 1;
			}
			
			char myLetter = charWord[randomLetter];
			char myPositionLetter = charWord[randomLetter + setPosition];
		
			charWord[randomLetter + setPosition] = myLetter;
			charWord[randomLetter] = myPositionLetter;
		
			System.out.println(charWord);
		
			String newWord = new String(charWord);
		
			return newWord;
		}else{
			return word;
		}
	}
	
	private String addRandomLetter(String Word) {
		//Inserts a random letter into the String
		int letterPosition = (int) (Math.random()*Word.length());
		int randomLetter = (int) (Math.random()*26);
		System.out.println(randomLetter);
		StringBuilder str =  new StringBuilder(Word);
		str.insert(letterPosition, Alphabet[randomLetter]);
		System.out.println(str.toString());
		return str.toString();
	}
	
	private String removeRandomLetter(String Word) {
		//Removes a random char from String
		int letterPosition = (int) (Math.random()*Word.length());
		char[] charWord = Word.toCharArray();
		StringBuilder str = new StringBuilder();
		for (int j=0; j < Word.length(); j++) {
			if (j != letterPosition) {
				str.append(charWord[j]);
			}
		}	
		return str.toString();
	}
}
