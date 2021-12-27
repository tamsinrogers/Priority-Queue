/**
 * File: PQHeap.java
 * Author: Tamsin Rogers
 * Date: 4/23/20
 */
 
import java.util.*;
import java.util.Comparator;
import java.lang.Object;
import java.util.Arrays;
import java.util.Collections;

/* priority queue heap, stores elements of type T */
@SuppressWarnings("unchecked")
public class PQHeap<T>
{
	private Object[] data;					// the heap data
	private int size;						// the number of elements in the heap
	private Comparator<T> comp;		
	
	// Index of the last element in the array.
    private int index = 0;
	
	/* constructor */
	public PQHeap(Comparator<T> comparator)
	{
		this.data = new Object[10];			// initialize the empty heap
		this.size = 0;						// set size of heap to 0
		this.comp = comparator;				// store the comparator
	}
	
	/* returns the element at the given index */
	public T get(int i)
	{
		return (T)(data[i]);
	}
	
	/* takes an index and returns the number of elements in the heap */
	public int size()
	{
		return this.size;
	}

	/* takes an index and returns the index of the i node's left child */
	private int leftChildIndex(int i) 
	{
		return ((2*i)+1);
	}

	/* takes an index and returns the index of the i node's right child */
	private int rightChildIndex(int i) 
	{
		return ((2*i)+2);
	}

	/* returns the index of the i node's parent */
	private int parentIndex(int i) 
	{
		return (i-1)/2;
	}
	
	/* adds the value to the heap and increments the size */
    public void add(T obj)
    {
		if(size == data.length)									// double the size of the heap if it gets too big
		{
			Object[] newData = new Object[size*2];
			System.arraycopy(data, 0, newData, 0, size);	// copy the data already in the heap into the new array
			data = newData;
		}
		data[size] = obj;										// place the new element at the end of the array
		moveUp(size);											// move the element to the correct index
		size++;													// increment the size of the heap
    }
    
    /* moves the element at i into the right place in the heap */
    public void moveUp(int i)
    {
		T mover = (T)(data[i]);									// the element being moved
		int child = i;				
		while(child>0)
		{
			int parent = parentIndex(child);
			if(comp.compare(mover, (T)(data[parent])) <= 0)		// if the element being moved is lower priority than the parent
			{
				break;											// keep the order the same
			}
			data[child] = data[parent];							// go to the next parent
			child = parent;										// swap the child with its parent
		}
		data[child] = mover;
    }

    /* removes and returns the highest priority element from the heap */
    public T remove() 
    {
		if(size == 0)											// if the heap is empty
		{
			return null;										// cannot remove any elements
		}
		T toRemove = (T)(data[0]);								// the root element is highest priority
		data[0] = data[size-1];									// move this element to the end of the heap
		size--;													// decrement the size of the heap
		moveDown(0);											// move the rest of the elements into the right places
		return toRemove;										// return the removed element
    }	
    
    /* moves the element at i into the right place in the heap */
    public void moveDown(int i)
    {	
		T mover = (T)(data[i]);									// the element being moved
		int parent = i;
		int child = leftChildIndex(parent);
		
		while(child<size)	
		{
			if((child<size-1) && (comp.compare((T)(data[child]), (T)(data[child+1])) <0))	// if the child is higher priority
			{
				child = child+1;
			}
			if(comp.compare(mover, (T)(data[child])) >= 0)		// if the element being moved is higher priority than its child
			{
				break;											// keep the order the same
			}
			data[parent] = data[child];							// go to the next parent
			parent = child;										// swap the child with its parent
			child = leftChildIndex(parent);
		}
		data[parent] = mover;
    }
    
    /* returns a string version of the heap */
	public String toString()
	{
		String s = "";
		for(Object current : data)
		{
			s = s + current + ", ";
		}
		return s;
	}
    
    /* prints the parent element along with its children */
    private void printStructure()
    {
        for (int i=1; i<=size/2; i++ )
        {
        	T parent = (T)(data[i]);
        	T left = (T)(data[leftChildIndex(i)]);
        	T right = (T)(data[rightChildIndex(i)]);
        
            System.out.println("parent : " + parent + " left child: " + left + " right child: " + right);
        }
    }

	public static void main(String a[])
	{
        Compare c = new Compare();
		PQHeap< KeyValuePair<String, Integer> > heap = new PQHeap< KeyValuePair<String, Integer> >(c);
		
		KeyValuePair<String,Integer> kvp0 = new KeyValuePair<String,Integer>("six",6);
		KeyValuePair<String,Integer> kvp1 = new KeyValuePair<String,Integer>("one",1);
		KeyValuePair<String,Integer> kvp2 = new KeyValuePair<String,Integer>("four",4);
		KeyValuePair<String,Integer> kvp3 = new KeyValuePair<String,Integer>("two",2);
		KeyValuePair<String,Integer> kvp4 = new KeyValuePair<String,Integer>("ten",10);
		KeyValuePair<String,Integer> kvp5 = new KeyValuePair<String,Integer>("five",5);
		KeyValuePair<String,Integer> kvp6 = new KeyValuePair<String,Integer>("zero",0);
        
        heap.add(kvp0);
        heap.add(kvp1);
        heap.add(kvp2);
        heap.add(kvp3);
        heap.add(kvp4);
        heap.add(kvp5);
        heap.add(kvp6);
        
        System.out.println("heap: " + heap.toString());
        heap.printStructure();
        
        System.out.println("size: " + heap.size());
        System.out.println("removing: " + heap.remove());
        System.out.println("size: " + heap.size());
        
         System.out.println("heap: " + heap.toString());
    }
}