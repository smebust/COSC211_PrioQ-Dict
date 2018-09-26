/*
This interface was written and provided by Prof. John Rager
 */

import java.util.Vector;

public interface CS211PriorityQueueInterface<V extends Comparable> {

	
	public void insert(V w);
	
	public V peek();
	
	public V remove();
	
	public void build(Vector<V> words);
	
	public boolean isEmpty();
}
