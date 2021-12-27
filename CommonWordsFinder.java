/**
 * File: CommonWordsFinder.java
 * Author: Tamsin Rogers
 * Date: 4/23/20
 */
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File; 
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*; 
import java.util.*; 
import java.util.Comparator;
import java.lang.Object;
import java.util.Arrays;

/* reads a word count file and reports the most common words */
public class CommonWordsFinder
{
	private Compare c = new Compare();
	private PQHeap< KeyValuePair<String, Integer> > heap = new PQHeap< KeyValuePair<String, Integer> >(c);
	int count;	//total word count

	/* reads the word count file and puts the words into a PQHeap, returns the heap */
	public PQHeap< KeyValuePair<String, Integer> > read(String filename)
	{
		try
		{
			FileReader fileReader = new FileReader(filename);				
			BufferedReader bufferedReader = new BufferedReader(fileReader);	
			String line = bufferedReader.readLine();					
			
			line = bufferedReader.readLine();									// read the first line (header)

			while(line != null)													// go through the line
			{
				String[] values = line.split(" ");								// split the line into values (split at the space)
				String key = values[0];											// the key is the first value in the line
				int value = Integer.parseInt(values[1]);						// the value is the second value in the line (cast to an int)
				
				count = count+value;
				
				KeyValuePair<String,Integer> kvp = new KeyValuePair<String,Integer>(key,value);
				heap.add(kvp);													// add the pair to the heap
				line = bufferedReader.readLine();								// read the next line
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) 
		{
			System.out.println("Board.read():: unable to open file " + filename );
		}
		catch(IOException ex) 
		{
			System.out.println("Board.read():: error reading file " + filename);
		}
		//System.out.println(heap.toString());	// print the heap as a string
		return heap;
	}
	
	/* returns the frequency of the word in the list of words */
	public double getFrequency( KeyValuePair<String,Integer> kvp )
	{
		float totalwords = (float)(this.count);	
		float value = kvp.getValue();
		float frequency = value/totalwords;
		return frequency;
	}
	
	/* writes a frequency file */
	public void writeFrequency( PQHeap< KeyValuePair<String, Integer> > data, String filename, int N )
	{
		try 
    	{
      		File file = new File(filename);											// create the file
      		FileWriter writer = new FileWriter(filename);							// the file writer
      		
      		String s = "totalWordCount: " + this.totalWordCount() + "\n";			// the header (total word count of the file)
      		writer.write(s);														// write the header
      		
      		for(int i=0; i<N; i++)													// repeat N times
			{
				KeyValuePair<String,Integer> kvp = data.get(i);
 				String s = kvp.getKey() + " " +  this.getFrequency(kvp) + "\n";		// space out the words and frequencies
				writer.write(s);													// write each line
			}
			writer.close();															// close the file
      	} 
      	catch (IOException e) 
      	{
      		System.out.println("error");
    	}
	}
	
	public static void main(String a[])
	{
		CommonWordsFinder test = new CommonWordsFinder();

		System.out.println("enter a word count file name:");
		Scanner scan1 = new Scanner(System.in);
		String name = scan1.nextLine(); 
		
		ArrayList<String> names = new ArrayList<String>();
		names.add(name);
		
		System.out.println("enter 1 to continue entering files, enter 2 to start analyzing");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt(); 
		
		while(choice == 1)
		{
			System.out.println("enter file name:");
			Scanner scanner = new Scanner(System.in);
			String nextname = scanner.nextLine(); 
			names.add(nextname);
			
			System.out.println("enter 1 to continue entering files, enter 2 to start analyzing");
			Scanner s = new Scanner(System.in);
			choice = s.nextInt(); 
		}
		
		if(choice == 2)
		{
		
			System.out.println("enter N:");
			Scanner scan2 = new Scanner(System.in);
			int N = scan1.nextInt(); 
		
			for(String file : names)
			{
				PQHeap< KeyValuePair<String, Integer> > heap = test.read(file);													// read the file, create the heap 
				System.out.println("writing " + N + " most frequent words of " + file + " to " + "freq_"+file);
				test.writeFrequency(heap, ("freq_"+file), N);
			}
		}
	}
}