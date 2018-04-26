import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameCollector {

	private ArrayList<String> _names = new ArrayList<String>();
	
	public NameCollector()
	{
		try(Scanner reader = new Scanner(new File("names.txt")))
		{
			while(reader.hasNextLine())
			{
				_names.add(reader.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> names()
	{
		return _names;
	}
}
