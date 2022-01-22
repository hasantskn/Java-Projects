/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Hasan
 */
public interface HW4_Interface {
public Integer GetHash(String mystring);
public void ReadFileandGenerateHash(String filename, int size);
public void DisplayResult(String Outputfile);
public void DisplayResult();
public void DisplayResultOrdered(String Outputfile);
public int showFrequency(String myword);
public String showMaxRepeatedWord();
public int checkWord(String myword);
public float TestEfficiency();
public int NumberOfCollusion();
}
