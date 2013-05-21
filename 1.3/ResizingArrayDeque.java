//1.3.33
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Iterable<Item>
{
	private Item[] deque=(Item[])new Object[2];
	public int leftPosition=0,rightPosition=1,leftStart=0,rightStart=1;
	
	private void rebase(int newSize)
	{
		Item[] newDeque=(Item[])new Object[newSize];
		int newLeftStart=newSize/2-1;
		int newRightStart=newSize/2;
		int j=newLeftStart;
		for(int i=leftStart;i>=0;i--)
		{
			if(deque[i]!=null)
			{
				newDeque[j]=deque[i];
				j--;
			}
			else break;
		}
		int k=newRightStart;
		for(int i=rightStart;i<=deque.length-1;i++)
		{
			if(deque[i]!=null)
			{
				newDeque[k]=deque[i];
				k++;
			}
		}
		
		deque=newDeque;
		leftStart=newLeftStart;
		rightStart=newRightStart;
		leftPosition=j;
		rightPosition=k;
	}
	
	public boolean isEmpty(){return leftPosition==leftStart&&rightPosition==rightStart;}
	
	public int size(){return leftStart-leftPosition+rightPosition-rightStart;}
	
	public void pushLeft(Item item)
	{
		deque[leftPosition]=item;
		leftPosition--;
		if(leftPosition<0)
			rebase(2*deque.length);
	}
	
	public void pushRight(Item item)
	{
		deque[rightPosition]=item;
		rightPosition++;
		if(rightPosition==deque.length)
			rebase(2*deque.length);
	}
	
	public Item popLeft()
	{
		if(isEmpty())
			return null;
		if(leftPosition==leftStart)
			if(rightPosition-rightStart>1)
				rebase(size());
			else
				return popRight();
		leftPosition++;
		Item item=deque[leftPosition];
		deque[leftPosition]=null;
		if(size()>=2&&rightPosition-leftPosition<=deque.length/4)
			rebase(size()/2);
		return item;
	}
	
	public Item popRight()
	{
		if(isEmpty())
			return null;
		if(rightPosition==rightStart)
			if(leftStart-leftPosition>1)
				rebase(size());
			else
				return popLeft();
		rightPosition--;
		Item item=deque[rightPosition];
		deque[rightPosition]=null;
		if(size()>=2&&rightPosition-leftPosition<=deque.length/4)
			rebase(size()/2);
		return item;
	}
	
	public Iterator<Item> iterator()
	{
		return new ArrayDequeIterator();
	}
	
	private class ArrayDequeIterator implements Iterator<Item>
	{
		private int position=leftPosition+1;
		
		public boolean hasNext(){return position<rightPosition;}
		
		public Item next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			Item item=deque[position];
			position++;
			return item;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args)
	{
		ResizingArrayDeque<String> deque=new ResizingArrayDeque<String>();
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			if(s.substring(0,2).equals(">|"))
				deque.pushLeft(s.substring(2,s.length()));
			else if(s.substring(0,2).equals("<|"))
				StdOut.print(deque.popLeft()+" ");
			else if(s.substring(0,2).equals("|<"))
				deque.pushRight(s.substring(2,s.length()));
			else if(s.substring(0,2).equals("|>"))
				StdOut.print(deque.popRight()+" ");
		}
		
		StdOut.println("("+deque.size()+" items left in deque)");
		StdOut.println(deque.leftPosition+" "+deque.leftStart+" "+deque.rightStart+" "+deque.rightPosition);
		for(String s:deque)
			StdOut.print(s+" ");
	}
}