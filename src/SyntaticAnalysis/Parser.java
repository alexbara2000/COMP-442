package SyntaticAnalysis;

import Common.AST;
import Common.SemanticConcepts;
import Common.Token;
import Common.TokenType;
import LexicalAnalysis.LexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Parser {
    //input
    public LexicalAnalyzer input;
    private String production = "";
    private ArrayList<String> derivations = new ArrayList<>();
    private Map<String, ArrayList<TokenType>> followSet = new HashMap<>();
    private Map<String, ArrayList<TokenType>> firstSet = new HashMap<>();
    private ArrayList<String> nullable = new ArrayList<>();
    private ArrayList<String> endable = new ArrayList<>();
    //Stack
    Stack<String> stack= new Stack<>();
    Stack<AST> ASTStack = new Stack<>();
    Map<String, String> data;
    ArrayList<TokenType> terminals = new ArrayList<>();
    ArrayList<String> nonTerminals = new ArrayList<>();
    FileWriter outDerivationWriter;
    FileWriter outSyntaxErrorsWriter;


    public Parser(String path) throws Exception {

        String pathPrefix = path.split("\\.")[0];
        outDerivationWriter = new FileWriter(pathPrefix+".outderivation");
        outSyntaxErrorsWriter = new FileWriter(pathPrefix+".outsyntaxerrors");
        populateFirstAndFollowSet();
        
        this.input= new LexicalAnalyzer(path);

        String csvFile = "src/Common/grammarTable.csv";
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

    private void populateFirstAndFollowSet() {
        String firstSetFile = "src/Common/firstfollowSet.csv";
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
                    if(firstSplit[i].contains("∅") || firstSplit[i].contains("eof")){
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
                            case "CONSTRUCTORKEYWORD" -> firstValues.add(TokenType.CONSTRUCTOR);
                            case "SR" -> firstValues.add(TokenType.SCOPEOP);
                            default -> firstValues.add(TokenType.valueOf(valueToAdd));
                        }
                    }
                }
                firstSet.put(key, firstValues);

                ArrayList<TokenType> followValues = new ArrayList<>();
                for(int i=0; i<followSplit.length; i++){
                    if(followSplit[i].contains("∅") || followSplit[i].contains("eof")){
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
                            case "CONSTRUCTORKEYWORD" -> followValues.add(TokenType.CONSTRUCTOR);
                            case "SR" -> followValues.add(TokenType.SCOPEOP);
                            default -> followValues.add(TokenType.valueOf(valueToAdd));
                        }
                    }
                }
                followSet.put(key, followValues);
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
                case "CONSTRUCTORKEYWORD" -> terminals.add(TokenType.CONSTRUCTOR);
                case "SR" -> terminals.add(TokenType.SCOPEOP);
                default -> terminals.add(TokenType.valueOf(value.toUpperCase()));
            }
        }
        catch (Exception e){
            terminals.add(TokenType.EMPTY);
        }
    }

    //algorithm
    public void parse() throws IOException {
        stack.push("$");//
        stack.push("START");
        derivations.add("START");

        Token token=input.getNextToken();
        Token previousToken = token;
        String top;
        boolean isValid = true;

        while(!stack.peek().equals("$") && !stack.peek().equals("eof")) {
            try {
                while (token.getType() == TokenType.BLOCKCMT || token.getType() == TokenType.INLINECMT) {
                    previousToken = token;
                    token = input.getNextToken();
                }
            } catch (Exception e) {

            }
            if (token == null && nullable.contains(stack.peek()) && !stack.peek().startsWith("SA")) {
                derivations.remove(stack.peek());
                outDerivationWriter.write(String.join(" ", derivations) + "\n");

                if (endable.contains(stack.peek())) {
                    break;
                }
                stack.pop();
                continue;
            }

            top = stack.peek();

            if (top.startsWith("SA")) {
                String semanticAction = stack.pop();
                switch (top){
                    case "SA1" -> AST.makeNode(previousToken);
                    case "SA2" -> AST.makeNode(new Token(TokenType.EMPTY, "epsilon", token.getLocation()));
                    case "SA3" -> AST.makeNull();
                    case "SA4" -> AST.makeFamily("array Size", -1);
                    case "SA5" -> AST.makeFamily("variable", -1);
                    case "SA6" -> AST.makeFamily("local variable", -1);
                    case "SA7" -> AST.makeFamily("statement", -1);
                    case "SA8" -> AST.makeFamily("expression", -1);
                    case "SA9" -> AST.makeFamily("arithmetic expression", -1);
                    case "SA10" -> AST.makeFamily("term", -1);
                    case "SA11" -> AST.makeFamily("factor", -1);
                    case "SA12" -> AST.makeFamily("recursive arithmetic expression", -1);
                    case "SA13" -> AST.makeFamily("recursive term", -1);
                    case "SA14" -> AST.makeFamily("rel expression", -1);
                    case "SA15" -> AST.makeFamily("if statement", -1);
                    case "SA16" -> AST.makeFamily("while loop", -1);
                    case "SA17" -> AST.makeFamily("read", -1);
                    case "SA18" -> AST.makeFamily("write", -1);
                    case "SA19" -> AST.makeFamily("return", -1);
                    case "SA20" -> AST.makeFamily("function body", -1);
                    case "SA21" -> AST.makeFamily("function definition", -1);
                    case "SA22" -> AST.makeFamily("class definition", -1);
                    case "SA23" -> AST.makeFamily("start", -1);
                    case "SA24" -> AST.makeFamily("program", -1);
                    case "SA25" -> AST.makeFamily("inheritance", -1);
                    case "SA26" -> AST.makeFamily("rept member declaration", -1);
                    case "SA27" -> AST.makeFamily("member declaration", -1);
                    case "SA28" -> AST.makeFamily("member function declaration", -1);
                    case "SA29" -> AST.makeFamily("member variable declaration", -1);
                    case "SA30" -> AST.makeFamily("function params", -1);
                    case "SA31" -> AST.makeFamily("function head", -1);
                    case "SA32" -> AST.makeFamily("function tail", -1);
                }

//                switch (top) {
//                    case "SA1" -> AST.makeNode(previousToken);
//                    case "SA2" -> AST.makeNull();
//                    case "SA3" -> AST.makeFamily("array Size", -1);
//                    case "SA4" -> AST.makeFamily("rept idnest", -1);
//                    case "SA5" -> AST.makeFamily("aParams", -1);
//                    case "SA6" -> AST.makeFamily("expr", 1);
//                    case "SA7" -> AST.makeFamily("expr", 3);
//                    case "SA8" -> AST.makeFamily("add", 3);
//                    case "SA9" -> AST.makeFamily("rel Expr", 3);
//                    case "SA10" -> AST.makeFamily("mult", 3);
//                    case "SA11" -> AST.makeFamily("data member", 2);
//                    case "SA12" -> AST.makeFamily("dot", 2);
//                    case "SA13" -> AST.makeFamily("funct call", 2);
//                    case "SA14" -> AST.makeFamily("factor", 1);
//                    case "SA15" -> AST.makeFamily("factor", -1);
//                    case "SA16" -> AST.makeFamily("read stat", 1);
//                    case "SA17" -> AST.makeFamily("stat", 1);
//                    case "SA18" -> AST.makeFamily("write stat", 1);
//                    case "SA19" -> AST.makeFamily("return stat", 1);
//                    case "SA20" -> AST.makeFamily("while stat", 2);
//                    case "SA21" -> AST.makeFamily("if stat", 3);
//                    case "SA22" -> AST.makeFamily("assign stat", 2);
//                    case "SA23" -> AST.makeFamily("stat block", -1);
//                    case "SA24" -> AST.makeFamily("prog", -1);
//                    case "SA25" -> AST.makeFamily("inherit list", -1);
//                    case "SA26" -> AST.makeFamily("var decl", 3);
//                    case "SA27" -> AST.makeFamily("function param", 3);
//                    case "SA28" -> AST.makeFamily("function param list", -1);
//                    case "SA29" -> AST.makeFamily("function head", 3);
//                    case "SA30" -> AST.makeFamily("function body", -1);
//                    case "SA31" -> AST.makeFamily("function def", 2);
//                    case "SA32" -> AST.makeFamily("rept struct decl", -1);
//                    case "SA33" -> AST.makeFamily("struct decl", 3);
//                    case "SA34" -> AST.makeFamily("rept impl def", -1);
//                    case "SA35" -> AST.makeFamily("impl def", 2);
//                    case "SA36" -> AST.makeNode(new Token(TokenType.EMPTY, "", token.getLocation()));
//                    case "SA37" -> AST.makeFamily("not", 2);
//                    case "SA38" -> AST.makeFamily("sign", 2);
//                }

//                sa8:makeAddOpNode(addOp, pop, pop, pop)
//                sa10:makeMultOpNode(multOp, pop, pop, pop)
//                sa36:makeNodeEmptySizeArray(token)
//                sa37:makeNotNode(not)
//                sa38:makeSignNode(sign)


            } else {
                outDerivationWriter.write(String.join(" ", derivations) + "\n");
                try {
                    if (terminals.contains(getActualTop(top)) && getActualTop(top) == token.getType()) {
                        stack.pop();
                        previousToken = token;
                        token = input.getNextToken();
                    } else {
                        previousToken = token;
                        token = skipError(token);
                        isValid = false;
                    }
                } catch (Exception e) {
                    try {
                        if (data.get(nonTerminals.indexOf(top) + "," + terminals.indexOf(token.getType())) != null) {
                            String popedString = stack.pop();
                            int index = derivations.indexOf(popedString);
                            derivations.remove(popedString);
                            inverseRHSMMultiPush(data.get(nonTerminals.indexOf(top) + "," + terminals.indexOf(token.getType())), index);
                        } else {
                            previousToken = token;
                            token = skipError(token);
                            isValid = false;
                        }
                    } catch (Exception b) {
                        top = stack.pop();
                        System.out.println(top);
                    }
                }
            }
        }
        if(isValid){
            System.out.println("Input is accepted by the grammar");
        }
        else {
            System.out.println("Input is not accepted by the grammar");
        }

        System.out.println(AST.treeToString());

        outSyntaxErrorsWriter.close();
        outDerivationWriter.close();
    }

    private Token skipError(Token token) throws IOException {
        outSyntaxErrorsWriter.write("Syntax error for the following token" + token + "\n");
        String top = stack.peek();
        System.out.println(token);
        System.out.println(top);
        Stack<String> tempStack = (Stack<String>)stack.clone();
        if(!followSet.containsKey(tempStack.peek())){
            return input.getNextToken();
        }
        while(!followSet.containsKey(tempStack.peek())){
            tempStack.pop();
        }
        top = tempStack.peek();
        if(token == null || followSet.get(top).contains(token.getType())){
            stack = tempStack;
            stack.pop();
            System.out.println(stack.peek());
        }
        else {
            while ( (!firstSet.get(top).contains(token.getType()) || nullable.contains(top)) && !followSet.get(top).contains(token.getType())){

                token = input.getNextToken();
                System.out.println(token);
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
            case "CONSTRUCTORKEYWORD" -> {
                return TokenType.CONSTRUCTOR;
            }
            case "RETURNTYPE" -> {
                throw new RuntimeException();
            }
            default -> {
                return TokenType.valueOf(top.toUpperCase());
            }
        }
    }


    private void inverseRHSMMultiPush(String rule, int index){
        String[] productions = rule.split(" ");

        for (int i = productions.length - 1; i>=2; i--){
            if(productions[i].equals("&epsilon")){
                //stack.push("");
            }
            else{
                stack.push(productions[i]);
            }
        }
        for (int i = 2; i< productions.length; i++){
            if(productions[i].equals("&epsilon")){
            }
            else{
                derivations.add(index + i -2,productions[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String fileToParse = "example-bubblesort.src";
        Parser parser=new Parser(fileToParse);
        parser.parse();

    }

}