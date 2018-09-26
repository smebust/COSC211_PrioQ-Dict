/*
This code was written by Sean Mebust, with methods to be overriden provided 
in an interface written by prof. John Rager
*/

import java.util.Vector;

public class PriorityQueue<V extends Comparable> implements CS211PriorityQueueInterface<V>{

    private Comparable[] prioQ;
    private int count;
    private boolean min = false;

    //Constructor
    public PriorityQueue(boolean isMin){
        prioQ = new Comparable[400];
        if(isMin) min = true;
    }

    @Override

	//Return the smallest element, do not change the PQ
	public V peek(){
        return (V) prioQ[1];
    }

    //Insert the element w into the PQ
    public void insert(V w){
        count++;
        prioQ[count] = w;
        this.bUp(count);
    }

	//Remove and return the smallest element (change PQ)
	public V remove(){
        V toRet = (V) prioQ[1];
        V end = (V) prioQ[count];
        prioQ[1] = null;
        prioQ[count] = null;
        prioQ[1] = end;
        count--;
        this.bDown(1);
        return toRet;
    }

	//Add all the elements of the vector to the PQ
	public void build(Vector<V> words){
        for(int i = 0; i < words.size(); i++){
            if(words.elementAt(i) != null) insert(words.elementAt(i));
        }

    }

	//True iff PQ is empty
	public boolean isEmpty(){
        if(prioQ[1] == null){
            return true;
        } else {
            return false;
        }
    }

    public void bUp(int index){
        if(prioQ[index/2] != null){
            if(min){
                if(prioQ[index].compareTo(prioQ[index/2]) > 0){
                   /*if(prioQ[index-1].compareTo(prioQ[index/2]) < 0){
                        V a = (V) prioQ[index-1];
                        V b = (V) prioQ[index/2];
                        prioQ[index-1] = null;
                        prioQ[index/2] = null;
                        prioQ[index-1] = b;
                        prioQ[index/2] = a;
                        System.out.println("Switching min 1");
                        bUp(index/2);
                }*/
                } else {
                    V a = (V) prioQ[index];
                    V b = (V) prioQ[index/2];
                    prioQ[index] = null;
                    prioQ[index/2] = null;
                    prioQ[index] = b;
                    prioQ[index/2] = a;
                    bUp(index/2);
                    //System.out.println("Switching min 2");
                }
            } else {
                if(prioQ[index].compareTo(prioQ[index/2]) < 0){
                    /*if(prioQ[index-1].compareTo(prioQ[index/2]) > 0){
                        V a = (V) prioQ[index-1];
                        V b = (V) prioQ[index/2];
                        prioQ[index-1] = null;
                        prioQ[index/2] = null;
                        prioQ[index-1] = b;
                        prioQ[index/2] = a;
                        System.out.println("Switching max 1");
                        bUp(index/2);
                }*/
                } else {
                    V a = (V) prioQ[index];
                    V b = (V) prioQ[index/2];
                    prioQ[index] = null;
                    prioQ[index/2] = null;
                    prioQ[index] = b;
                    prioQ[index/2] = a;
                    //System.out.println("Switching max 2");
                    bUp(index/2);
                }
            }
        }
    }
    
    public void bDown(int index){
        if(prioQ[index*2] != null){
            if(min){
                if(prioQ[index].compareTo(prioQ[index*2]) < 0){
                    if (prioQ[(index*2) + 1] != null){
                        if(prioQ[index].compareTo(prioQ[(index*2) + 1]) > 0){
                            V a = (V) prioQ[index];
                            V b = (V) prioQ[(index*2) + 1];
                            prioQ[index] = null;
                            prioQ[(index*2) + 1] = null;
                            prioQ[index] = b;
                            prioQ[(index*2) + 1] = a;
                            //System.out.println("Bubble down right min");
                            bDown((index*2) + 1);
                        }
                    }
                } else {
                    V a = (V) prioQ[index];
                    V b = (V) prioQ[index*2];
                    prioQ[index] = null;
                    prioQ[index*2] = null;
                    prioQ[index] = b;
                    prioQ[index*2] = a;
                    //System.out.println("Bubble down left min");
                    bDown(index*2);
                }
            } else {
                if(prioQ[index].compareTo(prioQ[index*2]) > 0){
                    if (prioQ[(index*2) + 1] != null){
                        if(prioQ[index].compareTo(prioQ[(index*2) + 1]) < 0){
                            V a = (V) prioQ[index];
                            V b = (V) prioQ[(index*2) + 1];
                            prioQ[index] = null;
                            prioQ[(index*2) + 1] = null;
                            prioQ[index] = b;
                            prioQ[(index*2) + 1] = a;
                            //System.out.println("Bubble down right max");
                            bDown((index*2) + 1);
                        }
                    }
                } else {
                    V a = (V) prioQ[index];
                    V b = (V) prioQ[index*2];
                    prioQ[index] = null;
                    prioQ[index*2] = null;
                    prioQ[index] = b;
                    prioQ[index*2] = a;
                    //System.out.println("Bubble down left max");
                    bDown(index*2);
                }
            }
        }
    }
}