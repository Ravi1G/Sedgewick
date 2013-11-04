//3.5.2 (page 507)

public class SequentialSearchSET<Key>
{
	private class Node
	{
		private Key key;
		private Node next;
		
		public Node(Key key, Node node)
		{
			this.key=key;
			this.node=node;
		}
	}
	
	private Node first;
	
	private int size=0;
	
	public void put(Key key)
	{
		first=new Node(key,first);
		size++;
	}
	
	public Key get(Key key)
	{
		for(Node node=first;node!=null;node=node.next)
		{
			if(node.key.equals(key)) return key;
		}
		
		return null;
	}

	public void delete(Key key)
	{
		first=delete(key,first);
	}

	private Node delete(Key key,Node node)
	{
		if(node==null) return null;

		if(node.key.equals(key))
		{
			size--;
			return node.next;
		}
		node.next=delete(key,node.next);
		return node;
	}

	public boolean contains(Key key)
	{
		return get(key)!=null;
	}

	public boolean isEmpty()
	{
		return size==0;
	}

	public int size()
	{
		return size;
	}

	public Iterable<Key> KEYS()
	{
		List<Key> keys=new ArrayList<Key>();
		for(Node node=first;node!=null;node=node.next)
			keys.add(node.key);
		return keys;
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);

		SequentialSearchSET<String> set=new SequentialSearchSET<String>();
		while(input.hasNext())
			set.put(input.next());

		for(String s:set.keys())
			output.print(s+" ");
		output.println();
	}

}