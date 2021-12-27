/**
 * File: WordTrendsFinder.java
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

/* determines the frequency of a set of words across different word files */
public class WordTrendsFinder
{
    WordCounter2 counter = new WordCounter2("hashmap");
    
    /* read the word count file and build a hashmap */
    public void read(String filename)
    {
    	ArrayList<String> words = counter.readWords(filename);
    	counter.analyze(filename);
    	counter.buildMap(words);

		counter.readWordCount(filename);
    }

	/* returns the number of times the word occurred in the list of words */
	public int getCount( String word )
	{
		return counter.getCount(word);
	}
	
	public int totalWords()
	{
		return counter.totalWordCount();
	}
	
	/* returns the frequency of the word in the list of words */	
	public double getFrequency( String word )
	{
		return counter.getFrequency(word);
	}
	
	public static void main(String a[])
	{
		WordTrendsFinder test = new WordTrendsFinder();
		
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
			System.out.println("enter a word count file name:");
			Scanner scanner = new Scanner(System.in);
			String nextname = scanner.nextLine(); 
			names.add(nextname);
			
			System.out.println("enter 1 to continue entering files, enter 2 to start analyzing");
			Scanner s = new Scanner(System.in);
			choice = s.nextInt(); 
		}
		
		if(choice == 2)
		{
		
			System.out.println("enter a series of words:");
			Scanner scan2 = new Scanner(System.in);
			String series = scan2.nextLine(); 
			
			String[] words = series.split(" ");
			
			for(String current: names)
			{
				test.read(current);
				for(String word: words)
				{
					System.out.println("FILE: " + current);
					System.out.println("WORD: " + word);
					System.out.println("frequency: " + test.getFrequency(word));
					System.out.print("\n");
				}
			}

		}
	
	}

}