package Test;
import Common.Token;
import Common.TokenType;
import LexicalAnalysis.LexicalAnalyzer;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

class LexicalAnalyzerPositiveGradingTest {
    LexicalAnalyzer la = null;
    ArrayList<Token> tokens = new ArrayList<>();
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        la = new LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexpositivegrading.src");
        Token token = la.getNextToken();
        while(token != null){
            tokens.add(token);
            token = la.getNextToken();
        }
    }

    @org.junit.jupiter.api.Test
    void testValidOperators(){
        Assertions.assertEquals(new Token(TokenType.EQ, "==", 1), tokens.get(0));
        Assertions.assertEquals(new Token(TokenType.PLUS, "+", 1), tokens.get(1));
        Assertions.assertEquals(new Token(TokenType.OR, "or", 1), tokens.get(2));
        Assertions.assertEquals(new Token(TokenType.OPENPAR, "(", 1), tokens.get(3));

        Assertions.assertEquals(new Token(TokenType.NOTEQ, "<>", 2), tokens.get(8));
        Assertions.assertEquals(new Token(TokenType.MINUS, "-", 2), tokens.get(9));
        Assertions.assertEquals(new Token(TokenType.AND, "and", 2), tokens.get(10));
        Assertions.assertEquals(new Token(TokenType.CLOSEPAR, ")", 2), tokens.get(11));

        Assertions.assertEquals(new Token(TokenType.LT, "<", 3), tokens.get(16));
        Assertions.assertEquals(new Token(TokenType.MULT, "*", 3), tokens.get(17));
        Assertions.assertEquals(new Token(TokenType.NOT, "not", 3), tokens.get(18));
        Assertions.assertEquals(new Token(TokenType.OPENCUBR, "{", 3), tokens.get(19));

        Assertions.assertEquals(new Token(TokenType.GT, ">", 4), tokens.get(24));
        Assertions.assertEquals(new Token(TokenType.DIV, "/", 4), tokens.get(25));
        Assertions.assertEquals(new Token(TokenType.CLOSECUBR, "}", 4), tokens.get(26));

        Assertions.assertEquals(new Token(TokenType.LEQ, "<=", 5), tokens.get(31));
        Assertions.assertEquals(new Token(TokenType.ASSIGN, "=", 5), tokens.get(32));
        Assertions.assertEquals(new Token(TokenType.OPENSQBR, "[", 5), tokens.get(33));

        Assertions.assertEquals(new Token(TokenType.GEQ, ">=", 6), tokens.get(38));
        Assertions.assertEquals(new Token(TokenType.CLOSESQBR, "]", 6), tokens.get(39));
    }

    @org.junit.jupiter.api.Test
    void testValidPunctuation(){
        Assertions.assertEquals(new Token(TokenType.SEMI, ";", 1), tokens.get(4));
        Assertions.assertEquals(new Token(TokenType.COMMA, ",", 2), tokens.get(12));
        Assertions.assertEquals(new Token(TokenType.DOT, ".", 3), tokens.get(20));
        Assertions.assertEquals(new Token(TokenType.COLON, ":", 4), tokens.get(27));
        Assertions.assertEquals(new Token(TokenType.RETURNTYPE, "=>", 5), tokens.get(34));
        Assertions.assertEquals(new Token(TokenType.SCOPEOP, "::", 6), tokens.get(40));
    }

    @org.junit.jupiter.api.Test
    void testValidReservedWords(){
        Assertions.assertEquals(new Token(TokenType.INTEGER, "integer", 1), tokens.get(5));
        Assertions.assertEquals(new Token(TokenType.WHILE, "while", 1), tokens.get(6));
        Assertions.assertEquals(new Token(TokenType.LOCALVAR, "localvar", 1), tokens.get(7));

        Assertions.assertEquals(new Token(TokenType.FLOAT, "float", 2), tokens.get(13));
        Assertions.assertEquals(new Token(TokenType.IF, "if", 2), tokens.get(14));
        Assertions.assertEquals(new Token(TokenType.CONSTRUCTOR, "constructor", 2), tokens.get(15));

        Assertions.assertEquals(new Token(TokenType.VOID, "void", 3), tokens.get(21));
        Assertions.assertEquals(new Token(TokenType.THEN, "then", 3), tokens.get(22));
        Assertions.assertEquals(new Token(TokenType.ATTRIBUTE, "attribute", 3), tokens.get(23));

        Assertions.assertEquals(new Token(TokenType.CLASS, "class", 4), tokens.get(28));
        Assertions.assertEquals(new Token(TokenType.ELSE, "else", 4), tokens.get(29));
        Assertions.assertEquals(new Token(TokenType.FUNCTION, "function", 4), tokens.get(30));

        Assertions.assertEquals(new Token(TokenType.SELF, "self", 5), tokens.get(35));
        Assertions.assertEquals(new Token(TokenType.READ, "read", 5), tokens.get(36));
        Assertions.assertEquals(new Token(TokenType.PUBLIC, "public", 5), tokens.get(37));

        Assertions.assertEquals(new Token(TokenType.ISA, "isa", 6), tokens.get(41));
        Assertions.assertEquals(new Token(TokenType.WRITE, "write", 6), tokens.get(42));
        Assertions.assertEquals(new Token(TokenType.PRIVATE, "private", 6), tokens.get(43));

        Assertions.assertEquals(new Token(TokenType.RETURN, "return", 7), tokens.get(44));
    }

    @org.junit.jupiter.api.Test
    void testValidIntNum(){
        Assertions.assertEquals(new Token(TokenType.INTNUM, "0", 13), tokens.get(45));
        Assertions.assertEquals(new Token(TokenType.INTNUM, "1", 14), tokens.get(46));
        Assertions.assertEquals(new Token(TokenType.INTNUM, "10", 15), tokens.get(47));
        Assertions.assertEquals(new Token(TokenType.INTNUM, "12", 16), tokens.get(48));
        Assertions.assertEquals(new Token(TokenType.INTNUM, "123", 17), tokens.get(49));
        Assertions.assertEquals(new Token(TokenType.INTNUM, "12345", 18), tokens.get(50));
    }

    @org.junit.jupiter.api.Test
    void testValidFloatNum(){
        Assertions.assertEquals(new Token(TokenType.FLOATNUM, "1.23", 20), tokens.get(51));
        Assertions.assertEquals(new Token(TokenType.FLOATNUM, "12.34", 21), tokens.get(52));
        Assertions.assertEquals(new Token(TokenType.FLOATNUM, "120.34e10", 22), tokens.get(53));
        Assertions.assertEquals(new Token(TokenType.FLOATNUM, "12345.6789e-123", 23), tokens.get(54));
    }

    @org.junit.jupiter.api.Test
    void testValidId(){
        Assertions.assertEquals(new Token(TokenType.ID, "abc", 25), tokens.get(55));
        Assertions.assertEquals(new Token(TokenType.ID, "abc1", 26), tokens.get(56));
        Assertions.assertEquals(new Token(TokenType.ID, "a1bc", 27), tokens.get(57));
        Assertions.assertEquals(new Token(TokenType.ID, "abc_1abc", 28), tokens.get(58));
        Assertions.assertEquals(new Token(TokenType.ID, "abc1_abc", 29), tokens.get(59));
    }

    @org.junit.jupiter.api.Test
    void testValidComment(){
        Assertions.assertEquals(new Token(TokenType.INLINECMT, "// this is an inline comment", 31), tokens.get(60));
        Assertions.assertEquals(new Token(TokenType.BLOCKCMT, "/* this is a single line block comment */", 33), tokens.get(61));
        Assertions.assertEquals(new Token(TokenType.BLOCKCMT, "/* this is a\\nmultiple line\\nblock comment \\n*/", 35), tokens.get(62));
        Assertions.assertEquals(new Token(TokenType.BLOCKCMT, "/* this is an imbricated \\n/* block comment\\n*/\\n*/", 40), tokens.get(63));
    }
}