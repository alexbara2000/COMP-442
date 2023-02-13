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
    //Stack
    Stack<String> stack= new Stack<>();
    Map<String, String> data = null;
    //Table of rules
    String [][] table=
            {
                    {"E",null,null,"E",null,null}
                    ,
                    {"TK",null,null,"TK",null,""}
                    ,
                    {null,"+TK",null,null,"",""}
                    ,
                    {"FH",null,null,"FH",null,null}
                    ,
                    {null,"","*FH",null,"",""}
                    ,
                    {"a",null,null,"(E)",null,null}


            };
    //ArrayList<String> nonTerminals = new ArrayList<>(Arrays.asList("S","E","K","T","H","F"));
    //ArrayList<String> terminals = new ArrayList<>(Arrays.asList("a","+","*","(",")","$"));
    ArrayList<TokenType> terminals = new ArrayList<>();
    ArrayList<String> nonTerminals = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    ArrayList<String> nullableNonTerminals = new ArrayList<>(Arrays.asList("K","H"));


    public Parser(String path) throws Exception {
        this.input= new LexicalAnalyzer(path);

        String csvFile = "src/Common/grammar.csv";
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

        // now you can access the data in your Map
//        for (Map.Entry<String, String> entry : data.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
        for(var token: terminals){
            System.out.println(token);
        }
        System.out.println();
        for(var token: nonTerminals){
            System.out.println(token);
        }

        System.out.println(nonTerminals.indexOf("APARAMS"));
        System.out.println(terminals.indexOf(TokenType.ID));
        System.out.println(data.get(nonTerminals.indexOf("APARAMS") + "," + terminals.indexOf(TokenType.ID)));

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

        while(!stack.peek().equals("$"))
        {
            while(token.getType() == TokenType.BLOCKCMT || token.getType() == TokenType.BLOCKCMT){
                token = input.getNextToken();
            }
            System.out.println(token);
            if(token == null && nullableNonTerminals.contains(stack.peek())){
                stack.pop();
                continue;
            }
            top=stack.peek();
            System.out.println(stack);
            System.out.println(production);
            try{
                if(terminals.contains(getActualTop(top))){
                    production += stack.pop()+ " ";
                    token = input.getNextToken();
                }
                else{
                    System.out.println("error1");
                    return;
                }
            }
            catch (Exception e){
                System.out.println("******");
                System.out.println(top);
                System.out.println(nonTerminals.indexOf(top));


                if(data.get(nonTerminals.indexOf(top) +","+terminals.indexOf(token.getType())) != null){
                    stack.pop();
                    inverseRHSMMultiPush(data.get(nonTerminals.indexOf(top) +","+terminals.indexOf(token.getType())));
                }
                else {
                    System.out.println("error2");
                    return;
                }
            }
        }
        System.out.println("Input is Accepted by LL1");
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
            default -> {
                return TokenType.valueOf(top.toUpperCase());
            }
        }
    }


    private void inverseRHSMMultiPush(String rule){
        String[] productions = rule.split(" ");

        for (int i = productions.length - 1; i>=2; i--){
            if(productions[i].equals("&epsilon")){
                stack.push("");
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