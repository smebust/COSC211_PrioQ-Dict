/*
This testing code was written entirely by Sean Mebust
*/

import java.util.Vector;

public class TestLab3{

    public static void main(String[] args){
        CountingDictionary dict = new CountingDictionary(2000);

        ReadFile test = new ReadFile("TextFile1.txt");

        test.addFileToDict(dict);

        Vector<Word> reaDict = dict.allKeyValue();

        for(int i = 0; i<reaDict.size(); i++){
            if(reaDict.elementAt(i) != null) System.out.println(reaDict.elementAt(i));
        }
        
        
        PriorityQueue<Word> q = new PriorityQueue<Word>(true);

        q.build(reaDict);

        for(int i = 0; i<10; i++){
            Word w = q.remove();
            System.out.println(w);
        }

        PriorityQueue<Word> q2 = new PriorityQueue<>(false);

        q2.build(reaDict);

        for(int i = 0; i<10; i++){
            Word w = q2.remove();
            System.out.println(w);
        } 

        CountingDictionary cWords = new CountingDictionary(1000);
        CountingDictionary unCWords = new CountingDictionary(1000);

        ReadFile test1 = new ReadFile("CommonWords.txt");
        ReadFile test2 = new ReadFile("TextFile2.txt");

        test1.addFileToDict(cWords);

        test2.addFileToDict(unCWords, cWords);

        Vector<Word> reaDictUnC = unCWords.allKeyValue();

        for(int i = 0; i<reaDictUnC.size(); i++){
            System.out.println(reaDictUnC.elementAt(i));
        }
        
    }
}