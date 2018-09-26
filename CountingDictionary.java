/*
Structure and method headers provided by prof John Rager
 */

import java.util.Vector;

public class CountingDictionary implements CS211CountingDictionaryInterface{

	private Word[] hashTbl;

	public CountingDictionary(int size){
		hashTbl = new Word[size];
	}

    public void insert(String key){
		//System.out.println(key);
		key = key.toLowerCase();
		int index = hashFn(key);
		if (hashTbl[index] == null){
			hashTbl[index] = new Word(key, 1);
		} else {
			if (hashTbl[index].getWord().equals(key)) {
				hashTbl[index].inc();
			} else {
				int keepTrack = 0;
				while(hashTbl[index] != null){
					if(index+1>=hashTbl.length){
						index = 0;
					} else {
						index++;
					}
					keepTrack++;
					if(keepTrack>hashTbl.length){
						reHash();
					} 
				}
				hashTbl[index] = new Word(key, 1);
			}
		}
    } 
	/* insert the given key storing 1 as the value if the key does not already exist
	*  if the key already
	*  exists, increment the associated value 
	*/
	
	public boolean delete(String key){
		int index = hashFn(key);
		if (hashTbl[index] == null){
			return false;
		} else {
			if(hashTbl[index].getWord().equals(key)){
				hashTbl[index] = null;
			} else {
				while(hashTbl[index].getWord().equals(key) != true){
					if(index+1>=hashTbl.length){
						index = index - hashTbl.length;
					} else {
						index++;
					}
				}
				hashTbl[index] = null;
			}
			return true;
		}
    }
	/* delete the given key, if the key was in the dictionary, return true
	 * otherwise, return false  Implementing this is optional unless you use 
	 * an array as your D/S, in which case it is required
	 */
	
	public int find(String key){
		int index = hashFn(key);
		if (hashTbl[index] == null){
			return -1;
		} else {
			if(hashTbl[index].getWord().equals(key)){
				return hashTbl[index].getCount();	
			} else {
				while(hashTbl[index].getWord().equals(key) != true){
					if(index+1>=hashTbl.length){
						index = index - hashTbl.length;
					} else {
						index++;
					}
				}
				return hashTbl[index].getCount();
			}
			 
		}
    }
	/* return the value associated with the given key,  If the key is NOT in the dictionary,
	 * return -1
	 */
	
	public Vector<Word> allKeyValue(){
		Vector<Word> listAll = new Vector<Word>();
		for (int i = 0; i < hashTbl.length; i++){
			if(hashTbl[i] != null){
			listAll.add(hashTbl[i]);
			}
		}
		return listAll;
    }
	/* return a list of all the key-value Words in the dictionary.  If your D/S is 
	 * a BST or an ordered array, they should be in order.
	 */

	public int hashFn(String key){
		int toReturn;
		double num = 0;

		for(int i = 0; i<key.length(); i++){
			int c = key.charAt(i);
			num = num + c;
		}
		
		num = (num/key.length() * hashTbl.length) / Math.sqrt(num + (Math.PI));

		num = num * 1000000;

		toReturn = (int) num % 100000;

		if(toReturn > hashTbl.length) toReturn = (int) (num % hashTbl.length);
		//System.out.println(toReturn + "HASH FUNCTION CALL");
		return toReturn;

	 }

	 public void reHash(){
		Vector<Word> toHash = this.allKeyValue();
		int newL = this.hashTbl.length*2;
		this.hashTbl = new Word[newL];

		for(int i = 0; i < toHash.size(); i++){
			insert(toHash.elementAt(i).getWord());
		}
	 }
	
}