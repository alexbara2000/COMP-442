import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
public class LexicalAnalyzer {
    private int lineNumber;
    private byte[] chars;
    private int currentIndex;

    public LexicalAnalyzer(String pathName) throws Exception{
        this.lineNumber=1;
        File file = new File(pathName);

        FileInputStream fl = new FileInputStream(file);
        this.chars = new byte[(int)file.length()];
        fl.read(this.chars);

        this.currentIndex = 0;
    }

    public String getNextToken(){
        char currChar;
        String word = "";

        GetRidOfBlankSpace();

        while (currentIndex<chars.length){
            currChar = (char)chars[currentIndex];
            currentIndex++;
            if(IsLetter(currChar)){
                while (currentIndex+1<chars.length && !IsUnknown(currChar) ){
                    word+=currChar;
                    currentIndex++;
                    currChar = (char)chars[currentIndex];
                }
                return CheckIfReservedWord(word);
            }
            else if(IsDigit(currChar)){

            }
            else if(currChar == '+'){
                return "[plus, +, "+lineNumber+"]";
            }
            else if(currChar == '-'){
                return "[minus, -, "+lineNumber+"]";
            }
            else if(currChar == '*'){
                return "[mult, *, "+lineNumber+"]";
            }
            else if(currChar == '/'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '*'){
                        currentIndex++;
                        return "[blockcmt, /*, "+lineNumber+"]";
                    }
                    else if(chars[currentIndex] == '/'){
                        currentIndex++;
                        String comment = "//";
                        while(currentIndex<chars.length){
                            currChar = (char)chars[currentIndex];
                            currentIndex++;
                            if(IsUnknown(currChar)){

                                if(currChar == 13){
                                    this.lineNumber++;
                                    System.out.println();
                                    break;
                                }
                            }
                            comment = comment+currChar;
                        }
                        return "[inlinecmt, "+comment+", "+lineNumber+"]";
                    }
                }
                return "[div, /, "+lineNumber+"]";
            }
            else if(currChar == '='){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '='){
                        currentIndex++;
                        return "[eq, ==, "+lineNumber+"]";
                    }
                    else if(chars[currentIndex] == '>'){
                        currentIndex++;
                        return "[returntype, =>, "+lineNumber+"]";
                    }
                }
                return "[assign, =, "+lineNumber+"]";
            }
            else if(currChar == '<'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '='){
                        currentIndex++;
                        return "[leq, <=, "+lineNumber+"]";
                    }
                    if(chars[currentIndex] == '>'){
                        currentIndex++;
                        return "[noteq, <>, "+lineNumber+"]";
                    }
                }
                return "[lt, <, "+lineNumber+"]";
            }
            else if(currChar == '>'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '='){
                        currentIndex++;
                        return "[geq, >=, "+lineNumber+"]";
                    }
                }
                return "[gt, >, "+lineNumber+"]";
            }
            else if(currChar == '('){
                return "[openpar, (, "+lineNumber+"]";
            }
            else if(currChar == ')'){
                return "[closepar, ), "+lineNumber+"]";
            }
            else if(currChar == '{'){
                return "[opencubr, {, "+lineNumber+"]";
            }
            else if(currChar == '}'){
                return "[closecubr, }, "+lineNumber+"]";
            }
            else if(currChar == '['){
                return "[opensqbr, [, "+lineNumber+"]";
            }
            else if(currChar == ']'){
                return "[closesqbr, ], "+lineNumber+"]";
            }
            else if(currChar == ';'){
                return "[semi, ;, "+lineNumber+"]";
            }
            else if(currChar == ','){
                return "[comma, ,, "+lineNumber+"]";
            }
            else if(currChar == '.'){
                return "[dot, ., "+lineNumber+"]";
            }
            else if (IsUnknown(currChar)){
                if(currChar == 13){
                    this.lineNumber++;
                    System.out.println();
                }
                //System.out.println("There has been a space/tab/enter");
                return word;
            }

            word= word+currChar;
        }
        return "END";
    }

    private static boolean IsLetter(char currChar){
        return (currChar >= 65 && currChar <= 90) || (currChar >= 97 && currChar <= 122);
    }
    private static boolean IsDigit(char currChar){
        return currChar >= 48 && currChar <= 57;
    }

    private static boolean IsUnknown(char currChar){
        return currChar <= 32 || currChar == 127;
    }

    private void GetRidOfBlankSpace(){
        char currChar;
        while (currentIndex<chars.length){
            currChar = (char)chars[currentIndex];

            if (IsUnknown(currChar)){
                if(currChar == 13){
                    this.lineNumber++;
                    System.out.println();
                }
                currentIndex++;
            }
            else
                return;
        }
    }

    private String CheckIfReservedWord(String word) {

        return "[id, "+word+", "+lineNumber+"]";
    }
}
