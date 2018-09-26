/*
Method headers and structure written by Professor John Rager
All else by Sean Mebust
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ReadFile {
	private String fileName;
	
	public ReadFile(String s) {
		fileName = s;
	}
	
	public Vector<String> process() {
		
		File f = new File(fileName);
		Scanner sc = null;
		
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Is it possible that file "+fileName+" does not exist?");
			System.exit(-1);
		}
		
		Vector<String> toR = new Vector<String>();
		
		while (sc.hasNext()) {
			toR.add(sc.next());
		}
		
		return toR;
	}

	public void addFileToDict(CountingDictionary dict){
		
		Vector<String> toAdd = process();

		for(int i = 0; i<toAdd.size(); i++){
			dict.insert(toAdd.elementAt(i));
			//System.out.println(i);
		}
	}

	public void addFileToDict(CountingDictionary dict, CountingDictionary commonWords){
		
		Vector<String> toAdd = process();

		Vector<Word> toCheck = commonWords.allKeyValue();

		for(int i = 0; i<toAdd.size(); i++){
			boolean isIn = false;
			for(int j = 0; j<toCheck.size(); j++){
				if(toAdd.elementAt(i).equals(toCheck.elementAt(j).getWord())){
					//dict.insert(toAdd.elementAt(i));
					//System.out.println("new: " + j + " " + i);
					isIn = true;
				} 
			}
			if(isIn){
				//System.out.println("Not adding element");
			} else{
				//System.out.println("Adding element");
				dict.insert(toAdd.elementAt(i));
			}
		}
	}
}
