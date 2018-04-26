import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Testing {
	
	private static final Character[] v = {'a','e','i','o','u','y'};
	private static final Set<Character> VOWELS =
		new HashSet<Character>(Arrays.asList(v));
	private static final Set<Character> CONSONANTS = new HashSet<Character>();
	static {
		for(int i = 0; i < 26; ++i)
			CONSONANTS.add((char) (97+i));
		CONSONANTS.removeAll(VOWELS);
		CONSONANTS.add('y');
	}
	
	private LetterAssociation[] letters = new LetterAssociation[26];

	public Testing()
	{
		for(int i = 0; i < 26; ++i) letters[i] = new LetterAssociation((char) (97+i));
		createCommonMap();
		try(PrintWriter nameMaker = new PrintWriter(new FileOutputStream("names_generated.txt",false)))
		{
			for(int i = 0; i<9999; ++i)
				nameMaker.println(run());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i <10; ++i)
			System.out.println(run());
	}
	public static void main(String[] args) {
		new Testing();
	}
	private String run() {
		int length = new Random().nextInt(4)+3;
		String name = "" + letters[new Random().nextInt(letters.length)].getLetter();
		for(int i = 0; i < length; ++i)
			name+=nextChar(name);
		String lastname = "" + letters[new Random().nextInt(letters.length)].getLetter();
			for(int i = 0; i < length; ++i)
				lastname+=nextChar(lastname);
		name = name.toUpperCase().substring(0,1) + name.substring(1);
		lastname = lastname.toUpperCase().substring(0,1) + lastname.substring(1);
		return name + " " + lastname;
	}
	
	private char nextChar(String current)
	{
		for(LetterAssociation e : letters)
		{
			if(e.getLetter()==current.charAt(current.length()-1))
				{
				char letter = e.getLetter();
				char nextLetter = ' ';
				if(isVowel(letter))
				{
					do{nextLetter = e.getRandomAssociation();}
					while(isVowel(nextLetter));
				}
				else
				{
					do{nextLetter = e.getRandomAssociation();}
					while(!isVowel(nextLetter));
				}
				return nextLetter;
				}
		}
		return ' ';
	}
	
	private static boolean isVowel(char d)
	{
		for(char a : VOWELS)
			if(d==a) return true;
		return false;
	}
	
	static class Chooser<E>
	{
		public E choose(E[] collection)
		{
			return collection[new Random().nextInt(collection.length)];
		}
	}
	
	private void createCommonMap()
	{
		ArrayList<String> names = new NameCollector().names();
		for(String name : names)
		{
			name=name.toLowerCase();
			while(name.length()>1)
			{
				letters[name.charAt(0)-97].addAssociation(name.charAt(1));
				name = name.substring(1);
			}
		}
	}
	
	
}
