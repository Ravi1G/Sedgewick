//page 155
//algorithm 1.4

import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class Bag<Item extends Comparable<Item>> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
	public boolean isEmpty(){return list.size()==0;}
	
	public int size(){return list.size();}
	
	public void add(Item item)
	{
		list.insertFirst(item);
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		Bag<String> b=new Bag<String>();

		while(input.hasNext())
			b.add(input.next());
		
		output.println("("+b.size()+" items in bag)");
		
		for(String s:b)
			output.print(s+" ");
			
		output.println();
	}
}