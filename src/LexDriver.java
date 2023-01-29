import Common.Token;
import Common.TokenType;
import LexicalAnalysis.LexicalAnalyzer;

import java.io.FileWriter;
import java.io.IOException;

public class LexDriver {
    public static void main(String[] args) throws Exception {
        LexicalAnalysis.LexicalAnalyzer la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexnegativegrading.src");
        createOutFiles(la, "lexnegativegrading");

        la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexpositivegrading.src");
        createOutFiles(la, "lexpositivegrading");

        la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/example-polynomial.src");
        createOutFiles(la, "example-polynomial");

        la = new LexicalAnalysis.LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/example-bubblesort.src");
        createOutFiles(la, "example-bubblesort");
    }

    private static void createOutFiles(LexicalAnalyzer la, String fileName) throws IOException {
        Token token = la.getNextToken();
        int currentLineNumber = 1;

        FileWriter outLexTokenWriter = new FileWriter(fileName+"outlextokens");
        FileWriter outLexErrorsWriter = new FileWriter(fileName+".outlexerrors");
        while(token != null){
            if(token.getLocation() != currentLineNumber){
                currentLineNumber = token.getLocation();
                outLexTokenWriter.write("\r\n");
            }
            outLexTokenWriter.write(token + " ");


            if(token.getType() == TokenType.INVALIDID || token.getType() == TokenType.INVALIDCHAR ||
                    token.getType() == TokenType.INVALIDCOMMENT ||token.getType() == TokenType.INVALIDNUM){
                outLexErrorsWriter.write(token.getErrorMessage() + "\r\n");
            }
            token = la.getNextToken();
        }
        outLexErrorsWriter.close();
        outLexTokenWriter.close();
    }
}