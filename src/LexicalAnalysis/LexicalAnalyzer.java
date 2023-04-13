package LexicalAnalysis;

import Common.Token.Token;
import Common.Token.TokenType;

import java.io.File;
import java.io.FileInputStream;

public class LexicalAnalyzer {
    private int lineNumber;
    private final byte[] chars;
    private int currentIndex;

    public LexicalAnalyzer(String fileName) throws Exception{
        this.lineNumber=1;
        File file = new File("Files/Source/"+fileName);

        FileInputStream fl = new FileInputStream(file);
        this.chars = new byte[(int)file.length()];
        fl.read(this.chars);

        this.currentIndex = 0;
    }

    public Token getNextToken(){
        char currChar;
        StringBuilder word = new StringBuilder();

        GetRidOfBlankSpace();

        if (currentIndex<chars.length){
            currChar = (char)chars[currentIndex];
            currentIndex++;
            if(isLetter(currChar) || currChar == '_'){
                word.append(currChar);
                while(currentIndex<chars.length){
                    currChar = (char)chars[currentIndex];
                    if(isAlphaNum(currChar)){
                        word.append(currChar);
                        currentIndex++;
                    }
                    else{
                        break;
                    }
                }
                return CheckIfReservedWordAndValidate(word.toString());
            }
            else if(isDigit(currChar)){
                word.append(currChar);
                // This takes care of the edge case where an id is 123abc
                if(currentIndex<chars.length && ((char)chars[currentIndex] == '_'  || (isLetter((char)chars[currentIndex])))){
                    while(currentIndex<chars.length){
                        currChar = (char) chars[currentIndex];
                        if(isAlphaNum(currChar)){
                            currentIndex++;
                            word.append(currChar);
                        }
                        else {
                            break;
                        }
                    }
                    return new Token(TokenType.INVALIDNUM, word.toString(), lineNumber);

                }
                while(currentIndex<chars.length){
                    currChar = (char) chars[currentIndex];
                    if(isPartOfNumberAlphabet(currChar)){
                        currentIndex++;
                        word.append(currChar);
                    }
                    else {
                        break;
                    }
                }
                if(validateInteger(word.toString())){
                    return new Token(TokenType.INTNUM, word.toString(), lineNumber);
                }
                if(validateFloat(word.toString())){
                    return new Token(TokenType.FLOATNUM, word.toString(), lineNumber);
                }
                return new Token(TokenType.INVALIDNUM, word.toString(), lineNumber);


            }
            else if(currChar == '+'){
                return new Token(TokenType.PLUS, "+", lineNumber);
            }
            else if(currChar == '-'){
                return new Token(TokenType.MINUS, "-", lineNumber);
            }
            else if(currChar == '*'){
                return new Token(TokenType.MULT, "*", lineNumber);
            }
            else if(currChar == '/'){
                int currentLineNumber = lineNumber;
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == '*'){
                        StringBuilder comment = new StringBuilder("/*");
                        currentIndex++;
                        int numberOfOpenPar = 1;
                        while(currentIndex < chars.length && numberOfOpenPar > 0){
                            currChar = (char) chars[currentIndex];
                            if(currChar == '*' && currentIndex+1 < chars.length && chars[currentIndex+1] == '/'){
                                comment.append("*/");
                                numberOfOpenPar --;
                                currentIndex+=2;
                            }
                            else if(currChar == '/' && currentIndex+1 < chars.length && chars[currentIndex+1] == '*'){
                                comment.append("/*");
                                numberOfOpenPar ++;
                                currentIndex+=2;
                            }
                            else{
                                currentIndex++;
                                if(currChar == '\n'){
                                    comment.append("\\").append("n");
                                    lineNumber++;
                                }
                                else if(currChar == '\r'){
                                    // Do nothing
                                }

                                else{
                                    comment.append(currChar);
                                }
                            }
                        }
                        if (numberOfOpenPar == 0){
                            return new Token(TokenType.BLOCKCMT, comment.toString(), currentLineNumber);
                        }
                        return new Token(TokenType.INVALIDCOMMENT, comment.toString(), currentLineNumber);
                    }
                    else if(chars[currentIndex] == '/'){
                        currentIndex++;
                        StringBuilder comment = new StringBuilder("//");
                        while(currentIndex<chars.length){
                            currChar = (char)chars[currentIndex];
                            currentIndex++;
                            if(isBlankSpace(currChar)){
                                if(currChar == '\n'){
                                    this.lineNumber++;
                                    break;
                                }
                                if(currChar == '\r'){
                                    continue;
                                }
                            }
                            comment.append(currChar);
                        }
                        return new Token(TokenType.INLINECMT, comment.toString(), currentLineNumber);
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
            else if(currChar == ':'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == ':'){
                        currentIndex++;
                        return new Token(TokenType.SCOPEOP, "::", lineNumber);
                    }
                }
                return new Token(TokenType.COLON, ":", lineNumber);
            }
            else if (isBlankSpace(currChar)){
                if(currChar == '\n'){
                    this.lineNumber++;
                }
            }
            return new Token(TokenType.INVALIDCHAR, Character.toString(currChar), lineNumber);
        }
        return null;
    }

    private boolean validateFloat(String word) {
        if(!word.contains("."))
            return false;
        String[] arrayOfNums = word.split("\\.");
        if (arrayOfNums.length != 2)
            return false;
        if(!validateInteger(arrayOfNums[0])){
            return false;
        }
        String[] arrayOfNumsAfterPeriod = arrayOfNums[1].split("e");
        if (arrayOfNumsAfterPeriod.length == 1 && validateFraction(arrayOfNums[1])){
            return true;
        }
        if (arrayOfNumsAfterPeriod.length != 2) {
            return false;
        }

        String toValidate = arrayOfNumsAfterPeriod[1];
        if (toValidate.equals("+") || toValidate.equals("-") || toValidate.equals("e")){
            return false;
        }
        if (toValidate.length() > 1 && (toValidate.startsWith("+") || toValidate.startsWith("-"))){
            toValidate = toValidate.substring(1);
        }
        return validateFraction(arrayOfNumsAfterPeriod[0]) && validateInteger(toValidate);
    }

    private boolean validateFraction(String word){
        if (word.length() < 1)
            return false;
        if (word.length() == 1 && word.toCharArray()[0] >= '0' && word.toCharArray()[0] <= '9')
            return true;
        //returns true if it does not contain illegal chars and does not end in a 0
        return !word.contains(".") && !word.contains("e") && !word.contains("+") && !word.contains("-") && !(word.toCharArray()[word.length() - 1] == '0');
    }

    private boolean validateInteger(String word) {
        if (word.length() == 0)
            return false;
        if(word.length() ==1)
            return true;
        if(word.startsWith("0"))
            return false;
        //returns true if it does not contain illegal characters
        return !word.contains(".") && !word.contains("e") && !word.contains("+") && !word.contains("-");
    }

    private boolean validateId(String word) {
        return word.length() >= 1 && word.charAt(0) != '_' && isLetter(word.charAt(0));
    }

    private boolean isPartOfNumberAlphabet(char currChar) {
        return (currChar >= '0' && currChar <= '9') || currChar == 'e' || currChar == '+' || currChar == '-' || currChar == '.';
    }

    private static boolean isLetter(char currChar){
        return (currChar >= 'A' && currChar <= 'Z') || (currChar >= 'a' && currChar <= 'z');
    }

    private static boolean isDigit(char currChar){
        return currChar >= '0' && currChar <= '9';

    }

    private static boolean isAlphaNum(char currChar){
        return isLetter(currChar) || isDigit(currChar) || currChar == '_';
    }

    private static boolean isBlankSpace(char currChar){
        return currChar == ' ' || currChar == '\t' || currChar == '\n' || currChar == '\r';
    }

    private void GetRidOfBlankSpace(){
        char currChar;
        while (currentIndex<chars.length){
            currChar = (char)chars[currentIndex];

            if (isBlankSpace(currChar)){
                if(currChar == '\n' ){
                    this.lineNumber++;
                }
                currentIndex++;
            }
            else
                return;
        }
    }

    private Token  CheckIfReservedWordAndValidate(String word) {
        try {
            return new Token(TokenType.valueOf(word.toUpperCase()), word, lineNumber);
        }
        catch (Exception e){
            if(validateId(word)){
                return new Token(TokenType.ID, word, lineNumber);
            }
            return new Token(TokenType.INVALIDID, word, lineNumber);

        }
    }
}
