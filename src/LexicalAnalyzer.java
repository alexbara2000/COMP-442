import java.io.File;
import java.io.FileInputStream;

public class LexicalAnalyzer {
    private int lineNumber;
    private final byte[] chars;
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
        StringBuilder word = new StringBuilder();

        GetRidOfBlankSpace();

        while (currentIndex<chars.length){
            currChar = (char)chars[currentIndex];
            currentIndex++;
            if(IsLetter(currChar)){
                word.append(currChar);
                while(currentIndex<chars.length){
                    currChar = (char)chars[currentIndex];
                    if(IsAlphanum(currChar)){
                        word.append(currChar);
                        currentIndex++;
                    }
                    else{
                        break;
                    }
                }
                return CheckIfReservedWord(word.toString());
            }
            else if(IsDigit(currChar)){
                word.append(currChar);
                while(currentIndex<chars.length){
                    currChar = (char) chars[currentIndex];
                    if(IsPartOfNumberAlphabet(currChar)){
                        currentIndex++;
                        word.append(currChar);
                    }
                    else {
                        break;
                    }
                }
                if(validateInteger(word.toString())){
                    return new Token(TokenType.INTEGER, word.toString(), lineNumber);
                }
                if(validateFloat(word.toString())){
                    return new Token(TokenType.FLOATNUM, word.toString(), lineNumber);
                }
                return new Token(TokenType.INVALID, word.toString(), lineNumber);


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
                        String comment = "/*";
                        currentIndex++;
                        // TODO add logic for embedded block comments
                        int numberOfOpenPar = 1;
                        while(currentIndex < chars.length && numberOfOpenPar > 0){
                            currChar = (char) chars[currentIndex];
                            if(currChar == '*' && currentIndex+1 < chars.length && chars[currentIndex+1] == '/'){
                                comment = comment + "*/";
                                numberOfOpenPar --;
                                currentIndex+=2;
                            }
                            else if(currChar == '/' && currentIndex+1 < chars.length && chars[currentIndex+1] == '*'){
                                comment = comment + "/*";
                                numberOfOpenPar ++;
                                currentIndex+=2;
                            }
                            else{
                                currentIndex++;
                                if(currChar == '\r'){
                                    comment = comment + "\\" +"n";
                                    lineNumber++;
                                }
                                else if(currChar == '\n'){
                                    // TODO Refactor this once \r an \n make more sense
                                }
                                else
                                    comment = comment + currChar;
                            }
                        }
                        return new Token(TokenType.BLOCKCMT, comment, lineNumber);
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
            else if(currChar == ':'){
                if(currentIndex < chars.length){
                    if(chars[currentIndex] == ':'){
                        currentIndex++;
                        return new Token(TokenType.SCOPEOP, "::", lineNumber);
                    }
                }
                return new Token(TokenType.COLON, ":", lineNumber);
            }
            else if (IsUnknown(currChar)){
                if(currChar == 13){
                    this.lineNumber++;
                }
            }

            word.append(currChar);
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
        if(word.startsWith("00"))
            return false;
        //returns true if it does not contain illegal characters
        return !word.contains(".") && !word.contains("e") && !word.contains("+") && !word.contains("-");
    }

    private boolean IsPartOfNumberAlphabet(char currChar) {
        return (currChar >= '0' && currChar <= '9') || currChar == 'e' || currChar == '+' || currChar == '-' || currChar == '.';
    }

    private static boolean IsLetter(char currChar){
        return (currChar >= 65 && currChar <= 90) || (currChar >= 97 && currChar <= 122);
    }
    private static boolean IsDigit(char currChar){
        return currChar >= '0' && currChar <= '9';

    }

    private static boolean IsNonNegativeDigit(char currChar){
        return currChar >= '1' && currChar <= '9';

    }

    private static boolean IsAlphanum(char currChar){
        return IsLetter(currChar) || IsDigit(currChar) || currChar == '_';
    }

    private static boolean IsUnknown(char currChar){
        return currChar <= 32 || currChar == 127;
    }

    private String GetIntegerIfAvailable(String word){
        char currChar;

        if(word.equals("0")){
            return word;
        }
        while(currentIndex<chars.length){
            currChar = (char)chars[currentIndex];
            if(IsDigit(currChar)){
                word+=currChar;
                currentIndex++;
            }
            else{
                return word;
            }
        }
        return word;
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
        try {
            return new Token(TokenType.valueOf(word.toUpperCase()), word, lineNumber);
        }
        catch (Exception e){
            return new Token(TokenType.ID, word, lineNumber);
        }
    }
}
