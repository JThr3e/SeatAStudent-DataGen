import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class SASDataGen {
	
	public static double hateChance = 0.1;
	public static int maxNumOfTimes = 5;
	
	public static void main(String [] args)
	{
		ArrayList<String> studentsInClass = new ArrayList<String>();
		try {
			Scanner file = new Scanner(new File("students.txt"));
			while (file.hasNextLine()) {
				String student = file.nextLine();
				studentsInClass.add(student);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < studentsInClass.size(); i++)
		{
			int randPerson;
			try {
				PrintWriter writer = new PrintWriter(studentsInClass.get(i)+".txt", "UTF-8");
				writer.println("#[STUDENT SEATING PREFRENCES] -> NAME THIS FILE YOUR NAME AS IT APPEARS ON THE LIST");
				writer.println("#Students I Would Like to Sit Next to(Use names that are included on the list):");
				ArrayList<String> writePrefToFile = new ArrayList<String>();
				writePrefToFile.clear();
				//int numOfTimes = (int)((Math.random()*(maxNumOfTimes+1)));
				for(int j = 0; j < 4; j++)
				{
					do{
						randPerson = (int)(Math.random()*studentsInClass.size());
					}while(writePrefToFile.contains(studentsInClass.get(randPerson)) || studentsInClass.get(randPerson).contains(studentsInClass.get(i)));
					writePrefToFile.add(studentsInClass.get(randPerson));
				}
				for(String str : writePrefToFile)
				{
					writer.println(str);
				}
				writer.println("#Students I Would Reeeaallly Perfer Not to Sit Next to:");
				if(Math.random() < hateChance)
				{
					do{
						randPerson = (int)(Math.random()*studentsInClass.size());
					}while(writePrefToFile.contains(studentsInClass.get(randPerson)));
					writer.println(studentsInClass.get(randPerson));
				}
				writer.println("#Window Seat(put either yes, no, or idc):");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finished");
	}

}
