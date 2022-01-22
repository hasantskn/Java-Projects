/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
/**
 *
 * @author Hasan
 */
public class HW4_Hash implements HW4_Interface{
    DataItem[] hashArray = new DataItem[23];
    int arraySize;
    int size=0;  //counter for number of elements in hash table
    HashLink hashTab;
    

    public boolean isPrime(int number){
        if (number <= 1)
            return false;

        if (number == 2)
            return true;

        if ((number % 2) == 0)
            return false;

        for (int i=3; i < (number/2); i=i+2) {
            if ( (number % i) == 0 )
                return false;
        }
        return true;
    }
    public int getNextPrime(int minNumber){
        for(int i=minNumber;true;i++){
            if(isPrime(i)){
                return i;
            }
        }
    }
    
     class DataItem { // It holds word and frequency data for each hash table part

        public String word;
        public int frequency;
    }
     
    @Override
    public Integer GetHash(String mystring) { // Function to insert a value/element
               hashTab.insert(mystring);
        return hashTab.hash(mystring);
    }

    @Override
    public void ReadFileandGenerateHash(String fileName, int size) {
        if(isPrime(size)){
            hashArray=new DataItem[size];
            arraySize=size;
        }else{
            int primeCount=getNextPrime(size);
            hashArray=new DataItem[primeCount];
            arraySize=primeCount;
            System.out.println("Hash table given size: "+size+ " is not a prime");
            System.out.println("Hash table given size changed to: "+primeCount);

        }
        hashTab = new HashLink(arraySize);
        try {
            Scanner s = new Scanner(new File(fileName));
            
            while (s.hasNext()) {
                
                String word = s.nextLine().replaceAll("\\p{Punct}", " ");
                String[] tempArray = word.split(" "); // it takes each data with splitting the white spaces
                
                for (int i = 0; i < tempArray.length; i++) {  //turn for total data number
                    
                    String tempWord = tempArray[i].toLowerCase(new Locale("en")); //converts words to lowercase letters and adds them to the table in the English language
                    
                    if (!"".equals(tempWord.trim())) {
                        
                        hashTab.insert(tempWord);
                    }
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DisplayResult(String Outputfile) {
        try {
            File file = new File(Outputfile);

            FileWriter fileWrite = new FileWriter(file);
            String outputWord = "";
            System.out.println(); 
            for (int i = 0; i < hashTab.hashTable.length; i++) { // for hash array total part number
                    HashLinkNode start = hashTab.hashTable[i];
                    while (start != null) {
                        if(!outputWord.contains(start.data)){
                            outputWord += start.data + " : frequency : " + showFrequency(start.data) + "\n";  
                        }
                        start = start.next;     
                    }
            }
            fileWrite.write(outputWord);
            fileWrite.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void DisplayResult() {
        System.out.println();
        for (int i = 0; i < hashTab.hashTable.length; i++) {
            System.out.print("index " + i + ": ");

            HashLinkNode start = hashTab.hashTable[i];

            while (start != null) {
                System.out.print(start.data + " ");
                start = start.next;
            }

            System.out.println();
        }
    }
   
    

    @Override
    public void DisplayResultOrdered(String Outputfile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public int showFrequency(String myword) {
        int temp = 0;
        for (int i = 0; i < hashTab.hashTable.length; i++) {
            HashLinkNode start = hashTab.hashTable[i];
            while (start != null) {
                if(start.data.equals(myword)){
                    temp +=1;
                }
                start = start.next;
            }
        }
        if(temp == 0){
            return -1;
        }else{
            return temp;
        }
    }

    @Override
    public String showMaxRepeatedWord() {
        int count = 0;
        int tempCount;
        String result = "";
        for (int i = 0; i < hashTab.hashTable.length; i++) {
            HashLinkNode start = hashTab.hashTable[i];
            while (start != null) {
                tempCount = showFrequency(start.data);
                if(tempCount > count){
                    count = tempCount;
                    result = start.data;
                }
                start = start.next;
            }
        }
        return result + " : " + showFrequency(result);
    }

    @Override
    public int checkWord(String myword) {
        int temp = 0;
        for (int i = 0; i < hashTab.hashTable.length; i++) {
            HashLinkNode start = hashTab.hashTable[i];
            while (start != null) {
                if(start.data.equals(myword)){
                    temp = i;
                }
                start = start.next;
            }
            
        }
        //voice is found in location 5 and number of occurrences is 1
        //Lutfullah is not found in the text
        if(temp!=0){
            System.out.println(myword + " is found in location " + temp + " and number of occurences is " + showFrequency(myword));
        }else{
            System.out.println(myword+ " is not fount in the text");
        }
        return temp;
    }
        
    

    @Override
    public float TestEfficiency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int NumberOfCollusion() {
        System.out.println("there are "+ hashTab.numOfCol+ " collusions occured");
        return hashTab.numOfCol;
    }
    
}
