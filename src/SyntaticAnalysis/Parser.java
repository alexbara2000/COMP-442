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
    private String production = "";
    private Map<String, ArrayList<TokenType>> followSet = new HashMap<>();
    private Map<String, ArrayList<TokenType>> firstSet = new HashMap<>();
    private ArrayList<String> nullable = new ArrayList<>();
    private ArrayList<String> endable = new ArrayList<>();
    //Stack
    Stack<String> stack= new Stack<>();
    Map<String, String> data;
    ArrayList<TokenType> terminals = new ArrayList<>();
    ArrayList<String> nonTerminals = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    ArrayList<String> nullableNonTerminals = new ArrayList<>(List.of("REPTSTART0"));


    public Parser(String path) throws Exception {

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
        String firstSetFile = "src/Common/firstAndFollowSet.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(firstSetFile))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] initialSplit = line.split(",");
                String key = initialSplit[0];
                if(initialSplit[3].contains("yes")){
                    nullable.add(key.toUpperCase());
                }
                if(initialSplit[3].contains("no")){
                    endable.add(key.toUpperCase());
                }
                String[] firstSplit = initialSplit[1].split(" ");
                String[] followSplit = initialSplit[2].split(" ");

                ArrayList<TokenType> firstValues = new ArrayList<>();
                for(int i=0; i<firstSplit.length; i++){
                    if(firstSplit[i].contains("∅")){
                        firstValues.add(TokenType.EPSILON);
                    }
                    else{
                        String valueToAdd = firstSplit[i].toUpperCase();
                        switch (valueToAdd) {
                            case "RPAR" -> firstValues.add(TokenType.CLOSEPAR);
                            case "LPAR" -> firstValues.add(TokenType.OPENPAR);
                            case "RCURBR" -> firstValues.add(TokenType.CLOSECUBR);
                            case "LCURBR" -> firstValues.add(TokenType.OPENCUBR);
                            case "NEQ" -> firstValues.add(TokenType.NOTEQ);
                            case "RSQBR" -> firstValues.add(TokenType.CLOSESQBR);
                            case "LSQBR" -> firstValues.add(TokenType.OPENSQBR);
                            case "FLOATLIT" -> firstValues.add(TokenType.FLOATNUM);
                            case "INTLIT" -> firstValues.add(TokenType.INTNUM);
                            case "ARROW" -> firstValues.add(TokenType.RETURNTYPE);
                            case "EQUAL" -> firstValues.add(TokenType.ASSIGN);
                            case "SR" -> firstValues.add(TokenType.SCOPEOP);
                            default -> firstValues.add(TokenType.valueOf(valueToAdd.toUpperCase()));
                        }
                    }
                }
                firstSet.put(key, firstValues);

                ArrayList<TokenType> followValues = new ArrayList<>();
                for(int i=0; i<followSplit.length; i++){
                    if(followSplit[i].contains("∅")){
                        followValues.add(TokenType.EPSILON);
                    }
                    else{
                        String valueToAdd = followSplit[i].toUpperCase();
                        switch (valueToAdd) {
                            case "RPAR" -> followValues.add(TokenType.CLOSEPAR);
                            case "LPAR" -> followValues.add(TokenType.OPENPAR);
                            case "RCURBR" -> followValues.add(TokenType.CLOSECUBR);
                            case "LCURBR" -> followValues.add(TokenType.OPENCUBR);
                            case "NEQ" -> followValues.add(TokenType.NOTEQ);
                            case "RSQBR" -> followValues.add(TokenType.CLOSESQBR);
                            case "LSQBR" -> followValues.add(TokenType.OPENSQBR);
                            case "FLOATLIT" -> followValues.add(TokenType.FLOATNUM);
                            case "INTLIT" -> followValues.add(TokenType.INTNUM);
                            case "ARROW" -> followValues.add(TokenType.RETURNTYPE);
                            case "EQUAL" -> followValues.add(TokenType.ASSIGN);
                            case "SR" -> followValues.add(TokenType.SCOPEOP);
                            default -> followValues.add(TokenType.valueOf(valueToAdd.toUpperCase()));
                        }
                    }
                }
                firstSet.put(key, followValues);
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
            if(token == null && nullable.contains(stack.peek())){
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
        if(!followSet.containsKey(top) || !firstSet.containsKey(top)){
            stack.pop();
            return token;
        }
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
        Parser parser=new Parser("assignment2.COMP442-6421.paquet.2023.4/example-bubblesort.src");
        parser.parse();

    }

}