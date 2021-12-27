/*
Tamsin Rogers
4/24/20
TComparator.java
CS 231 Project 9
*/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.stream.Stream;
import java.lang.Object;

public class Compare implements Comparator <KeyValuePair<String,Integer>>
{
	public int compare( KeyValuePair<String,Integer> a, KeyValuePair<String,Integer> b ) 
	{
		int x = a.getValue();
		int y = b.getValue();
		
		if(x>y)
		{
			//System.out.println(x + " is greater than " + y);
			return 1;
		}
		if(x<y)
		{
			//System.out.println(x + " is less than " + y);
			return -1;
		}
		else
		{
			//System.out.println(x + " equals " + y);
			return 0;
		}
	}
}

