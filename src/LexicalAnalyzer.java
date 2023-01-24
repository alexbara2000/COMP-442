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
        String word = "";

        GetRidOfBlankSpace();

        while (currentIndex<chars.length){
            currChar = (char)chars[currentIndex];
            currentIndex++;
            if(IsLetter(currChar)){
                word+=currChar;
                while(currentIndex<chars.length){
                    currChar = (char)chars[currentIndex];
                    if(IsAlphanum(currChar)){
                        word+=currChar;
                        currentIndex++;
                    }
                    else{
                        break;
                    }
                }
                return CheckIfReservedWord(word);
            }
            else if(IsDigit(currChar)){
                word+=currChar;
                // TODO check for double 0 at start
                word = GetIntegerIfAvailable(word);
                if(currentIndex<chars.length && chars[currentIndex] == '.'){
                    currChar = (char)chars[currentIndex];
                    currentIndex++;
                    word += currChar;
                    String afterDot = "";
                    word += GetIntegerIfAvailable(afterDot);
                    // TODO check to see if it works
                    if (word.length() >1 && word.toCharArray()[word.length()-1] == '0'){
                        return new Token(TokenType.INTEGER, "***********", lineNumber);
                    }
                    return new Token(TokenType.FLOATNUM, word, lineNumber);
                }
                else{
                    return new Token(TokenType.INTEGER, word, lineNumber);
                }

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

            word= word+currChar;
        }
        return null;
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
        switch (word){
            case "or":
                return new Token(TokenType.OR, word, lineNumber);
            case "and":
                return new Token(TokenType.AND, word, lineNumber);
            case "not":
                return new Token(TokenType.NOT, word, lineNumber);
            case "integer":
                return new Token(TokenType.INTEGER, word, lineNumber);
            case "float":
                return new Token(TokenType.FLOAT, word, lineNumber);
            case "void":
                return new Token(TokenType.VOID, word, lineNumber);
            case "class":
                return new Token(TokenType.CLASS, word, lineNumber);
            case "self":
                return new Token(TokenType.SELF, word, lineNumber);
            case "isa":
                return new Token(TokenType.ISA, word, lineNumber);
            case "while":
                return new Token(TokenType.WHILE, word, lineNumber);
            case "if":
                return new Token(TokenType.IF, word, lineNumber);
            case "then":
                return new Token(TokenType.THEN, word, lineNumber);
            case "else":
                return new Token(TokenType.ELSE, word, lineNumber);
            case "read":
                return new Token(TokenType.READ, word, lineNumber);
            case "write":
                return new Token(TokenType.WRITE, word, lineNumber);
            case "return":
                return new Token(TokenType.RETURN, word, lineNumber);
            case "localvar":
                return new Token(TokenType.LOCALVAR, word, lineNumber);
            case "constructor":
                return new Token(TokenType.CONSTRUCTOR, word, lineNumber);
            case "attribute":
                return new Token(TokenType.ATTRIBUTE, word, lineNumber);
            case "function":
                return new Token(TokenType.FUNCTiON, word, lineNumber);
            case "public":
                return new Token(TokenType.PUBLIC, word, lineNumber);
            case "private":
                return new Token(TokenType.PRIVATE, word, lineNumber);
            default:
                return new Token(TokenType.ID, word, lineNumber);
        }
    }
}
