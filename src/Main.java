import Common.Token;
import Common.TokenType;
import LexicalAnalysis.LexicalAnalyzer;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
//        LexicalAnalyzer la = new LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexnegativegrading.src");
//        LexicalAnalysis.LexicalAnalyzer la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexpositivegrading.src");
//        LexicalAnalysis.LexicalAnalyzer la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/example-polynomial.src");
        LexicalAnalysis.LexicalAnalyzer la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/example-bubblesort.src");

        Token token = la.getNextToken();
        int currentLineNumber = 1;


        FileWriter outLexTokenWritter = new FileWriter("filename.outlextokens");
        FileWriter outLexErrorsWritter = new FileWriter("filename.outlexerrors");
        while(token != null){
            if(token.getLocation() != currentLineNumber){
                currentLineNumber = token.getLocation();
                outLexTokenWritter.write("\r\n");
            }
            outLexTokenWritter.write(token + " ");


            if(token.getType() == TokenType.INVALIDID || token.getType() == TokenType.INVALIDCHAR ||
                    token.getType() == TokenType.INVALIDCOMMENT ||token.getType() == TokenType.INVALIDNUM){
                outLexErrorsWritter.write(token.getErrorMessage() + "\r\n");
            }
            token = la.getNextToken();
        }
        outLexErrorsWritter.close();
        outLexTokenWritter.close();

    }
}