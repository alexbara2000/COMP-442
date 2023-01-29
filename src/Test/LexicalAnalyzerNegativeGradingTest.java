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
        Assertions.assertEquals(new Token(TokenType.INVALIDCHAR, "@", 1), tokens.get(0));
        Assertions.assertEquals(new Token(TokenType.INVALIDCHAR, "#", 1), tokens.get(1));
        Assertions.assertEquals(new Token(TokenType.INVALIDCHAR, "$", 1), tokens.get(2));
        Assertions.assertEquals(new Token(TokenType.INVALIDCHAR, "'", 1), tokens.get(3));
        Assertions.assertEquals(new Token(TokenType.INVALIDCHAR, "\\", 1), tokens.get(4));
        Assertions.assertEquals(new Token(TokenType.INVALIDCHAR, "~", 1), tokens.get(5));
    }

    @org.junit.jupiter.api.Test
    void testInvalidNums(){
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "00", 3), tokens.get(6));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "01", 4), tokens.get(7));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "010", 5), tokens.get(8));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "0120", 6), tokens.get(9));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "01230", 7), tokens.get(10));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "0123450", 8), tokens.get(11));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "01.23", 10), tokens.get(12));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "012.34", 11), tokens.get(13));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "12.340", 12), tokens.get(14));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "012.340", 13), tokens.get(15));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "012.34e10", 15), tokens.get(16));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "12.34e010", 16), tokens.get(17));
        Assertions.assertEquals(new Token(TokenType.INVALIDNUM, "1abc", 19), tokens.get(19));
    }

    @org.junit.jupiter.api.Test
    void testInvalidId(){
        Assertions.assertEquals(new Token(TokenType.INVALIDID, "_abc", 18), tokens.get(18));
        Assertions.assertEquals(new Token(TokenType.INVALIDID, "_1abc", 20), tokens.get(20));

    }
}