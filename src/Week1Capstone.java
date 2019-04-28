import java.util.Scanner;

public class Week1Capstone {

	public static void main(String[] args) {

		// Pig Latin Translator
		// Alex Nouhan
		// Grand Circus 2019
		
//		Can accept lowercase, CAPITAL, and Title Case words
//		Will translate words that end with a "." or a ","
//		Special characters will stay in place
//		No special case for numbers or all-special-character groups
		

		// Create scanner and declare variables
		Scanner scan = new Scanner(System.in);
		Scanner retry = new Scanner(System.in);

		String userIn;
		char userChar;
		
		// Greet user

		System.out.println("Welcome to the Pig Latin Translator!");
		System.out.println("====================================");
		System.out.println();

		//main loop body
		do {
			
			//Prompt user and collect input
			System.out.print("Enter a line to be translated: ");
			userIn = scan.nextLine();
			
			//Break up line into word strings in string array
			String[] split = new String[userIn.split(" ", 0).length];
			
			split = userIn.split(" ", 0);

			System.out.println("-----------------------------");
			//put string array through translator method one index at a time
			for (int i = 0; i < split.length; i++) {
				 //looking for period at the end of the word, removes period for processing and adds it back for print
				if (split[i].contains(".") == true) {
					int dot = split[i].indexOf('.');
					String dotBody = split[i].substring(0, dot);
					
					int caseType = caseFinder(dotBody);
					if (caseType == 3) {
						dotBody = pigTranslateS(dotBody);
					} else {
					dotBody = pigTranslate(dotBody);
					dotBody = caseChanger(dotBody, caseType);
					}
					System.out.print(dotBody + ". ");
					//looking for comma at the end of the word, removes comma for processing and adds it back for print
				} else if (split[i].contains(",") == true) {
					int com = split[i].indexOf(',');
					String comBody = split[i].substring(0, com);
					
					int caseType = caseFinder(comBody);
					if (caseType == 3) {
						comBody = pigTranslateS(comBody);
					} else {
					comBody = pigTranslate(comBody);
					comBody = caseChanger(comBody, caseType);
					}
					System.out.print(comBody + ", ");
				//standard process for no punctuation
				} else {
				int caseType = caseFinder(split[i]);
				if (caseType == 3) {
					split[i] = pigTranslateS(split[i]);
				} else {
				split[i] = pigTranslate(split[i]);
				split[i] = caseChanger(split[i], caseType);
				}
				System.out.print(split[i] + " ");
				}
			}

			// ask to continue
			System.out.println();
			System.out.println("-----------------------------");
			System.out.print("Try again? (y/n): ");
			do {
				userChar = retry.next().charAt(0);
				userChar = Character.toLowerCase(userChar);
				if (userChar != 'y' && userChar != 'n') {
					System.out.println();
					System.out.print("Please, try again! (y/n): ");
				}
			} while (userChar != 'y' && userChar != 'n');
			System.out.println();
		} while (userChar == 'y');

		System.out.println("Goodbye!");

		scan.close();
		retry.close();
	}
	//pig latin translator with lowercase output
	public static String pigTranslate(String in) {
		String inLo = in.toLowerCase();
		// if the word starts with a vowel just add "way"
		if (firstVowel(inLo) == 0) {
			return inLo + "way";
		} else {// otherwise rearrange the word
			String firstHalf = inLo.substring(0, firstVowel(in));
			String secondHalf = inLo.substring(firstVowel(in));
			return secondHalf + firstHalf + "ay";
		}
	}
	//special method in case of strange capitalization, no case modification
	public static String pigTranslateS(String in) {
		String inLo = in.toLowerCase();
		// if the word starts with a vowel just add "way"
		if (firstVowel(inLo) == 0) {
			return in + "way";
		} else {// otherwise rearrange the word
			String firstHalf = in.substring(0, firstVowel(in));
			String secondHalf = in.substring(firstVowel(in));
			return secondHalf + firstHalf + "ay";
		}
	}
	//capitalizes first index
	public static String capIt(String in) {
		String cap = in.substring(0, 1);
		String tail = in.substring(1);
		return cap.toUpperCase() + tail;
	}
	//capitalization detector with 4 possible cases
	public static int caseFinder(String in) {
		// 0 = lowercase
		// 1 = all caps
		// 2 = title case
		// 3 = other, make no modification
		String inLo = in.toLowerCase();
		String inHi = inLo.toUpperCase();
		if (in.equals(inLo)) {
			return 0;
		} else if (in.equals(inHi)) {
			return 1;
		} else {
			if (in.substring(0, 1).equals(in.substring(0, 1).toUpperCase())
					&& in.substring(1).equals(in.substring(1).toLowerCase())) {
				return 2;
			} else {
				return 3;
			}
		}
	}

	//case changer to apply to lowercase translator output
	public static String caseChanger(String in, int caseType) {
		if (caseType == 1) {
			return in.toUpperCase();
		} else if (caseType == 2) {
			return capIt(in);
		} else {
			return in;
		}
	}

	//returns index of first vowel in the string
	public static int firstVowel(String in) {
		// array of the vowels we are looking for
		char[] vowels = { 'a', 'e', 'o', 'i', 'u' };
		String inLo = in.toLowerCase();
		// for each index in our string...
		for (int i = 0; i < inLo.length(); i++) {
			// check if that index contains a vowel
			for (int j = 0; j < 4; j++) {
				if (inLo.charAt(i) == vowels[j]) {
					// return the index of the first vowel we come across
					return i;
				}
			}
		}
		return 0;
	}
}
