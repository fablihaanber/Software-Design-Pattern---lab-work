/*
	 * Software Design Pattern
	 * Lab Assignment 2
	 * @author Fabliha Anber
	 * Roll -56
	 * 
	 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

interface IStoreWordsBehavior {  //This interface implemes the behaviours to store words

  
  public String[] storeWords(String[] internalWordString);

  

}

interface IConcatenateWordsBehavior { //This interface implemes the behaviours to concat words

	  
	  public String concatenateWords(String[] internalWordString);

}

interface IPickWordsBehevior {  //This interface implemes the behaviours to pick words from the vocabulary

	  
	  public String[] pickWords(String[] internalWordString);

}

class StoreLowerCase implements IStoreWordsBehavior {

	  public String[] storeWords(String[] internalWordString) { //The words are converted to lower case and stored in the vocabulary
		
		  for(int counter = 0; counter < internalWordString.length; counter++) {
			  internalWordString[counter] = internalWordString[counter].toLowerCase();
		  }
		   
		return internalWordString;
	  }

}
class StoreUpperCaseAndReverse implements IStoreWordsBehavior { //The words are converted to upper case and reversed and stored in the vocabulary

	  public String[] storeWords(String[] internalWordString) {
		  
		  for(int counter = 0; counter < internalWordString.length; counter++) {
			  internalWordString[counter] = internalWordString[counter].toUpperCase();
			  String reverseString = new StringBuilder(internalWordString[counter]).reverse().toString();
			  internalWordString[counter] = reverseString;
		  }
		  
		return internalWordString;
	  }

}
class PickWordsRandomly implements IPickWordsBehevior { //Random amount of words are picked randomly from the vocabulary in this class

	  public String[] pickWords(String[] internalWordString) {
		    Random random = new Random();
			int randomNumberIndex;
			int randomNumberArray;
			while(true) {
				
				randomNumberArray = random.nextInt(15);
				if(randomNumberArray!=0) {
					break;
				}
			}
			String[] randomWords = new String[randomNumberArray];
			
			for(int counter = 0; counter < randomNumberArray; counter++) {
				randomNumberIndex = random.nextInt(internalWordString.length);
				randomWords[counter] = internalWordString[randomNumberIndex];
				
			}
			 
		return randomWords;
	  }

}
class PickWordsRandomlyAndSortAlphabetically implements IPickWordsBehevior { //The randomly picked words are stoted alphabetically

	  public String[] pickWords(String[] internalWordString) {
		    Random random = new Random();
			int randomNumberIndex;
			
			int randomNumberArray;
			while(true) {
				
				randomNumberArray = random.nextInt(15);
				if(randomNumberArray!=0) {
					break;
				}
			}
			String[] randomWords = new String[randomNumberArray];
			
			for(int counter = 0; counter < randomNumberArray; counter++) {
				
				randomNumberIndex = random.nextInt(internalWordString.length);
				randomWords[counter] = internalWordString[randomNumberIndex];
				
			}
			
			Arrays.sort(randomWords); 
		return randomWords;
		
	  }

}
class PickWordsRandomlyAndSortInOrder implements IPickWordsBehevior { //The randomly picked words are stoted in order

	  public String[] pickWords(String[] internalWordString) {
		    Random random = new Random();
			int randomNumberIndex;
			
			int randomNumberArray;
			while(true) {
				
				randomNumberArray = random.nextInt(15);
				if(randomNumberArray!=0) {
					break;
				}
			}
			int[] randomIntArray = new int[randomNumberArray];
			String[] randomWords = new String[randomNumberArray];
			
			for(int counter = 0; counter < randomNumberArray; counter++) {
				
				randomNumberIndex = random.nextInt(internalWordString.length);
				randomIntArray[counter] = randomNumberIndex;
				randomWords[counter] = internalWordString[randomNumberIndex];
				
			}
			//sort in order the original indexes
			Arrays.sort(randomIntArray);
			
			for(int counter = 0; counter < randomNumberArray; counter++) {
				
				
				randomWords[counter] = internalWordString[randomIntArray[counter]];
				
			} 
			
			
			
			
			
		return randomWords;
	  }

}
class ConcatenateUsingSpace implements IConcatenateWordsBehavior { //Words are concataneted ad stored in a string

	  public String concatenateWords(String[] finalWordsString) {
		  
		//displaying words
			String concatenatedWords = "";  //The words stored in the vacabulary of internal emmeory will be stored
			for(int count=0; count<finalWordsString.length; count++) { //The words from the internal memory vocabulary is printed bt concatenating each word with a space in between
				//System.out.println(words[count]);
				if(count==finalWordsString.length-1) {
					concatenatedWords += finalWordsString[count];
				}
				else {
			        concatenatedWords += finalWordsString[count] + " ";	
				}
			
				
			}
			
			
		return concatenatedWords;
	  }

}


class SentenceGeneratorContext { //This is the context class which selects of behaviors of these algorithm during runtime
	
	static IStoreWordsBehavior thisstoreWordsBehavior;
	static IPickWordsBehevior thispickWordsBehevior;
	static IConcatenateWordsBehavior thisconcatenateWordsBehavior;
	public static  void setStrategy(IStoreWordsBehavior storeWordsBehavior,IPickWordsBehevior pickWordsBehevior,IConcatenateWordsBehavior concatenateWordsBehavior){
		thisstoreWordsBehavior = storeWordsBehavior;
		thispickWordsBehevior = pickWordsBehevior;
		thisconcatenateWordsBehavior = concatenateWordsBehavior;
				
	}
	//another internal words vocabulary
	
	//Executing strategy
	public static String[] executeStoreWords(String[] internalWordString) {
		return thisstoreWordsBehavior.storeWords(internalWordString);
	  }
	public static String[] executepickWords(String[] internalWordString) {
		return thispickWordsBehevior.pickWords(internalWordString);
	  }
	public static String executeconcatenateWords(String[] internalWordString) {
		return thisconcatenateWordsBehavior.concatenateWords(internalWordString);
	  }
	
	
  
}

//The client code picks a concrete strategy and passes it to SentenceGeneratorContext class.
//The client should be aware of the differences between strategies in order to make the right choice
public class SentenceGenerator56 {
	public static void main(String[] args) {
		System.out.println("AssalamuAlaikum World");
		
		/*
		String[] randomSentenceGeneratorWords = new String[10];
		String[] sortedSentenceGeneratorWords = new String[10];
		String[] orderedSentenceGeneratorWords = new String[10];
		*/
		
		//Setting the variables for options of sentence generators
		final int randomSentenceGenerator = 1;
		final int sortedSentenceGenerator = 2;
		final int orderedSentenceGenerator = 3;
		final int exit = 4;
		final int addWords = 1;
		final int generateNewSentences = 2;
		final int exitRandomSentenceGenerator = 3;
		final int exitOrderedSentenceGenerator = 3;
		final int exitSortedSentenceGenerator = 3;
		//This string will store the words for random sentence generator
		String[] randomSentenceGeneratorWords = new String[0];   //Initially the String is empty
		//This string will store the words for Sorted sentence generator
		String[] sortedSentenceGeneratorWords =new String[0];   //Initially the String is empty
		//This string will store the words for ordered sentence generator
		String[] orderedSentenceGeneratorWords = new String[0];   //Initially the String is empty
		
		
		while(true) {
			//The options of choosing Sentence Generators will be given to user
			System.out.println("Menu - Choose any sentence generators");
			System.out.println("1. Random Sentence Generator");
			System.out.println("2. Sorted Sentence Generator");
			System.out.println("3. Ordered Sentence Generaor");
			System.out.println("4. Exit");
			System.out.println("Press 1,2 or 3 and hit Enter to continue.");
			
			
			int chooseOption;                        //The input of the choice of user is take with this variable
			Scanner input = new Scanner(System.in);  //The input from keyboard will be scanned
			chooseOption = input.nextInt();
			
			//The behaviors of each sentence generator will be chosen during runtime 
			if(chooseOption==randomSentenceGenerator) {
				
				while(true) {
					
					//The behaviors of random sentence generator is chosen
					SentenceGeneratorContext.setStrategy(new StoreLowerCase(), new PickWordsRandomly(), new ConcatenateUsingSpace() );
					
					if(randomSentenceGeneratorWords.length==0) {
						System.out.println("Initially the vocabulary is empty. You must add words first.");
						randomSentenceGeneratorWords = addWordsToVocabulary(randomSentenceGeneratorWords);
					}
					
					
					System.out.println("Menu- Choose any of the two options");
					System.out.println("1. Add words to vacabulary using Random Sentence Generator");
					System.out.println("2. Generate Sentence using Random Sentence Generator");
					System.out.println("3. Exit Random Sentence Generator");
					
					
					chooseOption = input.nextInt();
					
					if(chooseOption==addWords) {
						
						randomSentenceGeneratorWords = addWordsToVocabulary(randomSentenceGeneratorWords);
						
						//System.out.println(generateSentence(randomSentenceGeneratorWords));
					}
					
					else if(chooseOption==generateNewSentences) {
						System.out.println(generateSentence(randomSentenceGeneratorWords));
					}
					else if(chooseOption==exitRandomSentenceGenerator) {
						break;
					}
				}
				
				continue;
				
				
			}
			
			else if(chooseOption==sortedSentenceGenerator) {
				
				
				while(true) {
					
					//The behaviors of sorted sentence generator is chosen
					SentenceGeneratorContext.setStrategy(new StoreLowerCase(), new PickWordsRandomlyAndSortAlphabetically(), new ConcatenateUsingSpace() );
					
					if(sortedSentenceGeneratorWords.length==0) {
						System.out.println("Initially the vocabulary is empty. You must add words first.");
						sortedSentenceGeneratorWords = addWordsToVocabulary(sortedSentenceGeneratorWords);
					}
					
					
					System.out.println("Menu- Choose any of the two options");
					System.out.println("1. Add words to vacabulary using Sorted Sentence Generator");
					System.out.println("2. Generate Sentence using Sorted Sentence Generator");
					System.out.println("3. Exit Sorted Sentence Generator");
					
					
					chooseOption = input.nextInt();
					
					if(chooseOption==addWords) {
						sortedSentenceGeneratorWords = addWordsToVocabulary(sortedSentenceGeneratorWords);
					}
					
					else if(chooseOption==generateNewSentences) {
						System.out.println(generateSentence(sortedSentenceGeneratorWords));
					}
					else if(chooseOption==exitSortedSentenceGenerator) {
						break;
					}
				}
				
				continue;
				
				
				
//				
//				
//				
//				sortedSentenceGeneratorWords = SentenceGeneratorContext.executeStoreWords(sortedSentenceGeneratorWords);
//				String sortedlyPickedWords[] = SentenceGeneratorContext.executepickWords(sortedSentenceGeneratorWords);
//				String sortedconcatenatedWord = SentenceGeneratorContext.executeconcatenateWords(sortedlyPickedWords);
//				System.out.println(sortedconcatenatedWord);		
				
			}
			
			else if(chooseOption==orderedSentenceGenerator) {
				
//				//The behaviors of ordered sentence generator is chosen
//				SentenceGeneratorContext.setStrategy(new StoreUpperCaseAndReverse(), new PickWordsRandomlyAndSortInOrder(), new ConcatenateUsingSpace() );
				
				
				while(true) {
					
					//The behaviors of ordered sentence generator is chosen
					SentenceGeneratorContext.setStrategy(new StoreUpperCaseAndReverse(), new PickWordsRandomlyAndSortInOrder(), new ConcatenateUsingSpace() );
					
					if(orderedSentenceGeneratorWords.length==0) {
						System.out.println("Initially the vocabulary is empty. You must add words first.");
						orderedSentenceGeneratorWords = addWordsToVocabulary(orderedSentenceGeneratorWords);
					}
					
					
					System.out.println("Menu- Choose any of the two options");
					System.out.println("1. Add words to vacabulary using Ordered Sentence Generator");
					System.out.println("2. Generate Sentence using Ordered Sentence Generator");
					System.out.println("3. Exit Ordered Sentence Generator");
					
					
					chooseOption = input.nextInt();
					
					if(chooseOption==addWords) {
						
						orderedSentenceGeneratorWords = addWordsToVocabulary(orderedSentenceGeneratorWords);
					}
					
					else if(chooseOption==generateNewSentences) {
						System.out.println(generateSentence(orderedSentenceGeneratorWords));
//						orderedSentenceGeneratorWords = SentenceGeneratorContext.executeStoreWords(orderedSentenceGeneratorWords);
//						String orderlyPickedWords[] = SentenceGeneratorContext.executepickWords(orderedSentenceGeneratorWords);
//						String orderedconcatenatedWord = SentenceGeneratorContext.executeconcatenateWords(orderlyPickedWords);
//						System.out.println(orderedconcatenatedWord);
					}
					else if(chooseOption==exitOrderedSentenceGenerator) {
						break;
					}
				}
				
				continue;
				
				
				
				
				
			}
			if(chooseOption==exit) {
				System.exit(0);
			}
		}
		
		
		
		
	}

	private static String[] addWordsToVocabulary(String[] sentenceGeneratorWords) {
		// TODO Auto-generated method stub
		System.out.println("How many words do you want to take?");
		
		String[] wordArray = inputWordsFromClient(); //This array will store the words given by the user
		String[] combinedStringArray = combineTwoStringArrays(sentenceGeneratorWords, wordArray); //This array will store the combined words of previous vocabulary and the newly added words 
		
		sentenceGeneratorWords = new String[combinedStringArray.length]; //The combined vocabulary of user words and internal vocabulary
		sentenceGeneratorWords = combinedStringArray;
		System.out.println("Words have been added to internal memory of the CHOSEN Sentence Generator");
		
		return sentenceGeneratorWords;
	}

	private static String generateSentence(String[] sentenceGeneratorWords) {
		// TODO Auto-generated method stub
		String Words[] = SentenceGeneratorContext.executeStoreWords(sentenceGeneratorWords);
		String pickedWords[] = SentenceGeneratorContext.executepickWords(Words);
		String concatenatedWord = SentenceGeneratorContext.executeconcatenateWords(pickedWords);
		
		return concatenatedWord;
	}

	
	private static String[] combineTwoStringArrays(String[] words1, String[] words2) {
		// TODO Auto-generated method stub
		int wordLength = words1.length + words2.length;
		String[] words = new String[wordLength];
		int position = 0;
		for(String word: words1) {
			words[position] = word;
			position++;
		}
		for(String word:words2) {
			words[position] = word;
			position++;
		}
		return words;
		
	}

	private static String[] inputWordsFromClient() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int wordsCounter = input.nextInt();
		String[] wordArray = new String[wordsCounter]; //the words obtained from the input of the user will be stored in this array
		
		System.out.println("Enter words without space in between. Press enter to input next word");
		
		for(int count=0; count<wordsCounter; count++) {
			System.out.println("Enter word" + (count+1) + ":");
			String word = input.next(); //Reading the string value from user and storing it in word string
			wordArray[count] = word; //Storing the string wordArray in the array 
			
		}
		return wordArray;
		
	}
}
