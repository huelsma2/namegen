import java.util.ArrayList;
import java.util.Random;

public class LetterAssociation {
	private char letter;
	private ArrayList<Character> associations = new ArrayList<Character>();
	
	public LetterAssociation(char letter)
	{
		this.letter = letter;
	}
	
	public void addAssociation(char which)
	{
		associations.add(which);
	}
	
	public char getRandomAssociation()
	{
		return associations.get(new Random().nextInt(associations.size()));
	}
	
	public char getLetter()
	{
		return letter;
	}
}
