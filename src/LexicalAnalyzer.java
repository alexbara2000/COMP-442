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

    public Token getNextToken(){
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
                return new Token(TokenType.PLUS, "+", lineNumber);
            }
            else if(currChar == '-'){
                return new Token(TokenType.MINUS, "i", lineNumber);
            }
            else if(currChar == '*'){
                return new Token(TokenType.MULT, "*", lineNumber);
            }
            else if(currChar == '/'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '*'){
                        currentIndex++;
                        return new Token(TokenType.BLOCKCMT, "/*", lineNumber);
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
                                    break;
                                }
                            }
                            comment = comment+currChar;
                        }
                        return new Token(TokenType.INLINECMT, comment, lineNumber);
                    }
                }
                return new Token(TokenType.DIV, "/", lineNumber);
            }
            else if(currChar == '='){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '='){
                        currentIndex++;
                        return new Token(TokenType.EQ, "==", lineNumber);
                    }
                    else if(chars[currentIndex] == '>'){
                        currentIndex++;
                        return new Token(TokenType.RETURNTYPE, "=>", lineNumber);
                    }
                }
                return new Token(TokenType.ASSIGN, "=", lineNumber);
            }
            else if(currChar == '<'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '='){
                        currentIndex++;
                        return new Token(TokenType.LEQ, "<=", lineNumber);
                    }
                    if(chars[currentIndex] == '>'){
                        currentIndex++;
                        return new Token(TokenType.NOTEQ, "<>", lineNumber);
                    }
                }
                return new Token(TokenType.LT, "<", lineNumber);
            }
            else if(currChar == '>'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '='){
                        currentIndex++;
                        return new Token(TokenType.GEQ, ">=", lineNumber);
                    }
                }
                return new Token(TokenType.GT, ">", lineNumber);
            }
            else if(currChar == '('){
                return new Token(TokenType.OPENPAR, "(", lineNumber);
            }
            else if(currChar == ')'){
                return new Token(TokenType.CLOSEPAR, ")", lineNumber);
            }
            else if(currChar == '{'){
                return new Token(TokenType.OPENCUBR, "{", lineNumber);
            }
            else if(currChar == '}'){
                return new Token(TokenType.CLOSECUBR, "}", lineNumber);
            }
            else if(currChar == '['){
                return new Token(TokenType.OPENSQBR, "[", lineNumber);
            }
            else if(currChar == ']'){
                return new Token(TokenType.CLOSESQBR, "]", lineNumber);
            }
            else if(currChar == ';'){
                return new Token(TokenType.SEMI, ";", lineNumber);
            }
            else if(currChar == ','){
                return new Token(TokenType.COMMA, ",", lineNumber);
            }
            else if(currChar == '.'){
                return new Token(TokenType.DOT, ".", lineNumber);
            }
            else if (IsUnknown(currChar)){
                if(currChar == 13){
                    this.lineNumber++;
                }
            }

            word= word+currChar;
        }
        return null;
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
                }
                currentIndex++;
            }
            else
                return;
        }
    }

    private Token CheckIfReservedWord(String word) {
        return new Token(TokenType.ID, word, lineNumber);
    }
}
