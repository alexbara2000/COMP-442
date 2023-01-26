package Test;
import Common.Token;
import Common.TokenType;
import LexicalAnalysis.LexicalAnalyzer;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

class LexicalAnalyzerNegativeGradingTest {
    LexicalAnalyzer la = null;
    ArrayList<Token> tokens = new ArrayList<>();
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        la = new LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexnegativegrading.src");
        Token token = la.getNextToken();
        while(token != null){
            tokens.add(token);
            token = la.getNextToken();
        }
    }

    @org.junit.jupiter.api.Test
    void testInvalidChars(){
        Assertions.assertEquals(tokens.get(0), new Token(TokenType.INVALIDCHAR, "@", 1));
        Assertions.assertEquals(tokens.get(1), new Token(TokenType.INVALIDCHAR, "#", 1));
        Assertions.assertEquals(tokens.get(2), new Token(TokenType.INVALIDCHAR, "$", 1));
        Assertions.assertEquals(tokens.get(3), new Token(TokenType.INVALIDCHAR, "'", 1));
        Assertions.assertEquals(tokens.get(4), new Token(TokenType.INVALIDCHAR, "\\", 1));
        Assertions.assertEquals(tokens.get(5), new Token(TokenType.INVALIDCHAR, "~", 1));
    }

    @org.junit.jupiter.api.Test
    void testInvalidNums(){
        Assertions.assertEquals(tokens.get(6), new Token(TokenType.INVALIDNUM, "00", 3));
        Assertions.assertEquals(tokens.get(7), new Token(TokenType.INVALIDNUM, "01", 4));
        Assertions.assertEquals(tokens.get(8), new Token(TokenType.INVALIDNUM, "010", 5));
        Assertions.assertEquals(tokens.get(9), new Token(TokenType.INVALIDNUM, "0120", 6));
        Assertions.assertEquals(tokens.get(10), new Token(TokenType.INVALIDNUM, "01230", 7));
        Assertions.assertEquals(tokens.get(11), new Token(TokenType.INVALIDNUM, "0123450", 8));
        Assertions.assertEquals(tokens.get(12), new Token(TokenType.INVALIDNUM, "01.23", 10));
        Assertions.assertEquals(tokens.get(13), new Token(TokenType.INVALIDNUM, "012.34", 11));
        Assertions.assertEquals(tokens.get(14), new Token(TokenType.INVALIDNUM, "12.340", 12));
        Assertions.assertEquals(tokens.get(15), new Token(TokenType.INVALIDNUM, "012.340", 13));
        Assertions.assertEquals(tokens.get(16), new Token(TokenType.INVALIDNUM, "012.34e10", 15));
        Assertions.assertEquals(tokens.get(17), new Token(TokenType.INVALIDNUM, "12.34e010", 16));
        Assertions.assertEquals(tokens.get(19), new Token(TokenType.INVALIDNUM, "1abc", 19));
    }

    @org.junit.jupiter.api.Test
    void testInvalid(){
        Assertions.assertEquals(tokens.get(18), new Token(TokenType.INVALIDID, "_abc", 18));
        Assertions.assertEquals(tokens.get(20), new Token(TokenType.INVALIDID, "_1abc", 20));

    }
}