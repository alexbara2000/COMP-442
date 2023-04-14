package LexicalAnalysis;

import Common.Token.Token;
import Common.Token.TokenType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LexDriver {
    public static void main(String[] args) throws Exception {
        List<String> files = Stream.of(Objects.requireNonNull(new File("Files/Source").listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
        if(args != null && args.length >= 1){
            files = List.of(args);
        }
        for(var file : files){
            LexicalAnalyzer la = new LexicalAnalysis.LexicalAnalyzer(file);
            createOutFiles(la, file.substring(0, file.length()-4));
        }
    }

    private static void createOutFiles(LexicalAnalyzer la, String fileName) throws IOException {
        Token token = la.getNextToken();
        int currentLineNumber = 1;

        FileWriter outLexTokenWriter = new FileWriter("Files/LexTokens/"+fileName+".outlextokens");
        FileWriter outLexErrorsWriter = new FileWriter("Files/LexErrors/"+fileName+".outlexerrors");
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