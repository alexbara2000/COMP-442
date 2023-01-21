import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // Creating an object of File class and
        // providing local directory path of a file
        File file = new File("assignment1.COMP442-6421.paquet.2023.4/lexpositivegrading.src");

        FileInputStream fl = new FileInputStream(file);
//        char currChar;
//        String token = "";
//        String blocks ="";
//        int lineNumber = 1;
//        for(int i=0; i<(int)file.length(); i++){
//            currChar = (char)fl.read();
//
//            if(IsLetter(currChar)){
//                token = "Letter";
//            }
//            else if(IsDigit(currChar)){
//                token = "Digit";
//            }
//            else if (currChar == 9 || currChar == 32 || currChar == 13){
//                if(currChar == 13){
//                    lineNumber++;
//                }
//                //System.out.println("There has been a space/tab/enter");
//                System.out.println(blocks);
//                blocks="";
//                continue;
//            }
//            else{
//                token = "UNDEFINED";
//            }
//
//            blocks= blocks+currChar;
//
//            //System.out.println(token+": "+currChar+" "+lineNumber+ "----"+(int)currChar);
//        }
        LexicalAnalyzer la = new LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexpositivegrading.src");
        String token = "";
        while(true){
            token = la.getNextToken();
            if(token.equals("END"))
                break;
            System.out.print(token);
        }

    }

    public static boolean IsLetter(char currChar){
        if ((currChar >= 65 && currChar <= 90) ||  (currChar >= 97 && currChar <= 122))
            return true;
        return false;
    }
    public static boolean IsDigit(char currChar){
        if ((currChar >= 48 && currChar <= 57))
            return true;
        return false;
    }
}