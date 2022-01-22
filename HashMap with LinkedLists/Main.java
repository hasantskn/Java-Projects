public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HW4_Hash table = new HW4_Hash();
          
        table.ReadFileandGenerateHash("src/HW4_txt.txt", 1000);
        System.out.println(table.GetHash("EEM480"));
        table.DisplayResult();
        table.checkWord("the");
        table.DisplayResult("src/map.txt");
        System.out.println(table.showFrequency("the"));
        System.out.println(table.showMaxRepeatedWord());
       //table.DisplayResultOrdered("src/map.txt");  
        table.NumberOfCollusion();

    }
    
}
