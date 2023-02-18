package SyntaticAnalysis;

import Common.Token;
import Common.TokenType;
import LexicalAnalysis.LexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    //input
    public LexicalAnalyzer input;
    public int lengthofInput;
    private int indexOfInput=-1;
    private String production = "";
    private Map<String, ArrayList<TokenType>> followSet = new HashMap<>();
    private Map<String, ArrayList<TokenType>> firstSet = new HashMap<>();
    //Stack
    Stack<String> stack= new Stack<>();
    Map<String, String> data;
    ArrayList<TokenType> terminals = new ArrayList<>();
    ArrayList<String> nonTerminals = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    ArrayList<String> nullableNonTerminals = new ArrayList<>(List.of("REPTSTART0"));


    public Parser(String path) throws Exception {

        populateFollowSet();
        populateFirstSet();
        
        this.input= new LexicalAnalyzer(path);

        String csvFile = "src/Common/updatedGrammar.csv";
        String line = "";
        String csvSplitBy = ",";
        data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                nonTerminals.add(values[0]);

                for (int col = 0; col < values.length; col++) {
                    data.put(row + "," + col, values[col]);
                    if (row == 0){
                        AddTerminals(values[col]);
                    }
                }

                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateFirstSet() {
        String firstSetFile = "assignment2.COMP442-6421.paquet.2023.4/COMP442.grammar.grm.first";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(firstSetFile))) {
            while ((line = br.readLine()) != null) {
                String[] initialSplit = line.split(" ");
                String key = initialSplit[0].substring(7, initialSplit[0].length()-3).replace("-", "").toUpperCase();
                ArrayList<TokenType> values = new ArrayList<>();
                for(int i=1; i<initialSplit.length; i++){
                    if(initialSplit[i].contains("EPSILON")){
                        values.add(TokenType.EPSILON);
                    }
                    else{
                        String valueToAdd = initialSplit[i].substring(initialSplit[i].indexOf('\'')+1, initialSplit[i].lastIndexOf('\'')).toUpperCase();
                        switch (valueToAdd) {
                            case "$" -> values.add(TokenType.EPSILON);
                            case ")" -> values.add(TokenType.CLOSEPAR);
                            case "(" -> values.add(TokenType.OPENPAR);
                            case "}" -> values.add(TokenType.CLOSECUBR);
                            case "{" -> values.add(TokenType.OPENCUBR);
                            case "NEQ" -> values.add(TokenType.NOTEQ);
                            case "]" -> values.add(TokenType.CLOSESQBR);
                            case "[" -> values.add(TokenType.OPENSQBR);
                            case "FLOATLIT" -> values.add(TokenType.FLOATNUM);
                            case "INTLIT" -> values.add(TokenType.INTNUM);
                            case "ARROW" -> values.add(TokenType.RETURNTYPE);
                            case "EQUAL", "=" -> values.add(TokenType.ASSIGN);
                            case "SR" -> values.add(TokenType.SCOPEOP);
                            case "," -> values.add(TokenType.COMMA);
                            case "-" -> values.add(TokenType.MINUS);
                            case "+" -> values.add(TokenType.PLUS);
                            case "/" -> values.add(TokenType.DIV);
                            case "*" -> values.add(TokenType.MULT);
                            case ";" -> values.add(TokenType.SEMI);
                            case "." -> values.add(TokenType.DOT);
                            default -> values.add(TokenType.valueOf(valueToAdd.toUpperCase()));
                        }
                    }
                }
                firstSet.put(key, values);
            }
        } catch (Exception e) {
        }
    }

    private void populateFollowSet() {
        String firstSetFile = "assignment2.COMP442-6421.paquet.2023.4/COMP442.grammar.grm.follow";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(firstSetFile))) {
            while ((line = br.readLine()) != null) {
                String[] initialSplit = line.split(" ");
                String key = initialSplit[0].substring(8, initialSplit[0].length()-3).replace("-", "").toUpperCase();
                ArrayList<TokenType> values = new ArrayList<>();
                for(int i=1; i<initialSplit.length; i++){
                    if(initialSplit[i].contains("EPSILON") || initialSplit[i].contains("$")){
                        values.add(TokenType.EPSILON);
                    }
                    else{
                        String valueToAdd = initialSplit[i].substring(initialSplit[i].indexOf('\'')+1, initialSplit[i].lastIndexOf('\'')).toUpperCase();
                        switch (valueToAdd) {
                            case "$" -> values.add(TokenType.EPSILON);
                            case ")" -> values.add(TokenType.CLOSEPAR);
                            case "(" -> values.add(TokenType.OPENPAR);
                            case "}" -> values.add(TokenType.CLOSECUBR);
                            case "{" -> values.add(TokenType.OPENCUBR);
                            case "NEQ" -> values.add(TokenType.NOTEQ);
                            case "]" -> values.add(TokenType.CLOSESQBR);
                            case "[" -> values.add(TokenType.OPENSQBR);
                            case "FLOATLIT" -> values.add(TokenType.FLOATNUM);
                            case "INTLIT" -> values.add(TokenType.INTNUM);
                            case "ARROW" -> values.add(TokenType.RETURNTYPE);
                            case "EQUAL", "=" -> values.add(TokenType.ASSIGN);
                            case "SR" -> values.add(TokenType.SCOPEOP);
                            case "," -> values.add(TokenType.COMMA);
                            case "-" -> values.add(TokenType.MINUS);
                            case "+" -> values.add(TokenType.PLUS);
                            case "/" -> values.add(TokenType.DIV);
                            case "*" -> values.add(TokenType.MULT);
                            case ";" -> values.add(TokenType.SEMI);
                            case "." -> values.add(TokenType.DOT);
                            default -> values.add(TokenType.valueOf(valueToAdd.toUpperCase()));
                        }
                    }
                }
                followSet.put(key, values);
            }
        } catch (Exception e) {
        }
    }

    private void AddTerminals(String value) {
        try {
            value = value.toUpperCase();
            switch (value) {
                case "$" -> terminals.add(TokenType.EPSILON);
                case "RPAR" -> terminals.add(TokenType.CLOSEPAR);
                case "LPAR" -> terminals.add(TokenType.OPENPAR);
                case "RCURBR" -> terminals.add(TokenType.CLOSECUBR);
                case "LCURBR" -> terminals.add(TokenType.OPENCUBR);
                case "NEQ" -> terminals.add(TokenType.NOTEQ);
                case "RSQBR" -> terminals.add(TokenType.CLOSESQBR);
                case "LSQBR" -> terminals.add(TokenType.OPENSQBR);
                case "FLOATLIT" -> terminals.add(TokenType.FLOATNUM);
                case "INTLIT" -> terminals.add(TokenType.INTNUM);
                case "ARROW" -> terminals.add(TokenType.RETURNTYPE);
                case "EQUAL" -> terminals.add(TokenType.ASSIGN);
                case "SR" -> terminals.add(TokenType.SCOPEOP);
                default -> terminals.add(TokenType.valueOf(value.toUpperCase()));
            }
        }
        catch (Exception e){
            terminals.add(TokenType.EMPTY);
            System.out.println("CAUGTH EXCEPTION: "+value);
        }
    }

    //algorithm
    public void parse()
    {
        stack.push("$");//
        stack.push("START");
        //Read one token from input
        Token token=input.getNextToken();
        String top;
        System.out.println(token);
        boolean isValid = true;

        while(!stack.peek().equals("$"))
        {
            try{
                while(token.getType() == TokenType.BLOCKCMT || token.getType() == TokenType.INLINECMT){
                    token = input.getNextToken();
                }
                //System.out.println(token);
            }
            catch (Exception e){

            }
            if(token == null && nullableNonTerminals.contains(stack.peek())){
                stack.pop();
                continue;
            }
            top=stack.peek();
            System.out.println(production);
            try{
                if(terminals.contains(getActualTop(top)) && getActualTop(top) == token.getType()){
                    // add if statement
                    production += stack.pop()+ " ";
                    token = input.getNextToken();
                }
                else{
                    token = skipError(token);
                    isValid = false;
                }
            }
            catch (Exception e){
                if(data.get(nonTerminals.indexOf(top) +","+terminals.indexOf(token.getType())) != null){
                    stack.pop();
                    inverseRHSMMultiPush(data.get(nonTerminals.indexOf(top) +","+terminals.indexOf(token.getType())));
                }
                else {
                    token = skipError(token);
                    isValid = false;
                }
            }
        }
        if(isValid){
            System.out.println("Input is accepted by the grammar");
        }
        else {
            System.out.println("Input is not accepted by the grammar");
        }

    }

    private Token skipError(Token token) {
        System.out.println(token.getLocation());
        String top = stack.peek();
        System.out.println(top);
        if(token == null || followSet.get(top).contains(token.getType())){
            stack.pop();
        }
        else {
            while ( (!firstSet.get(top).contains(token.getType()) || firstSet.get(top).contains(TokenType.EPSILON)) && !followSet.get(top).contains(token.getType())){
                token = input.getNextToken();
                if(token == null){
                    return null;
                }
            }
        }
        return token;
    }

    private TokenType getActualTop(String top) {
        switch (top.toUpperCase()) {
            case "$" -> {
                return TokenType.EPSILON;
            }
            case "RPAR" -> {
                return TokenType.CLOSEPAR;
            }
            case "LPAR" -> {
                return TokenType.OPENPAR;
            }
            case "RCURBR" -> {
                return TokenType.CLOSECUBR;
            }
            case "LCURBR" -> {
                return TokenType.OPENCUBR;
            }
            case "NEQ" -> {
                return TokenType.NOTEQ;
            }
            case "RSQBR" -> {
                return TokenType.CLOSESQBR;
            }
            case "LSQBR" -> {
                return TokenType.OPENSQBR;
            }
            case "FLOATLIT" -> {
                return TokenType.FLOATNUM;
            }
            case "INTLIT" -> {
                return TokenType.INTNUM;
            }
            case "ARROW" ->
            {
                return TokenType.RETURNTYPE;
            }
            case "EQUAL" -> {
                return TokenType.ASSIGN;
            }
            case "SR" -> {
                return TokenType.SCOPEOP;
            }
            case "RETURNTYPE" -> {
                throw new RuntimeException();
            }
            default -> {
                return TokenType.valueOf(top.toUpperCase());
            }
        }
    }


    private void inverseRHSMMultiPush(String rule){
        String[] productions = rule.split(" ");

        for (int i = productions.length - 1; i>=2; i--){
            if(productions[i].equals("&epsilon")){
                //stack.push("");
            }
            else{
                stack.push(productions[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Parser parser=new Parser("assignment2.COMP442-6421.paquet.2023.4/example-bubblesort.src");
        parser.parse();

    }

}