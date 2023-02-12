package SyntaticAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
public class Parser {
    //input
    public String input="";
    public int lengthofInput;
    private int indexOfInput=-1;
    private String production = "";
    //Stack
    Stack<String> stack= new Stack<>();
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
    ArrayList<String> nonTerminals = new ArrayList<>(Arrays.asList("S","E","K","T","H","F"));
    ArrayList<String> terminals = new ArrayList<>(Arrays.asList("a","+","*","(",")","$"));
    ArrayList<String> nullableNonTerminals = new ArrayList<>(Arrays.asList("K","H"));


    public Parser(String in)
    {
        this.input=in;
        this.lengthofInput = input.length();
    }
    //algorithm
    public void parse()
    {
        stack.push("$");//
        stack.push("S");
        //Read one token from input
        String token=nextToken();
        String top;

        while(!stack.peek().equals("$"))
        {
            if(token == null && nullableNonTerminals.contains(stack.peek())){
                stack.pop();
                continue;
            }
            top=stack.peek();
            System.out.println(stack);
            System.out.println(production);
            if (terminals.contains(top)){
                if(top.equals(token)){
                    production += stack.pop();
                    token = nextToken();
                }
                else{
                    System.out.println("error");
                    return;
                }
            }
            else{
                if(table[nonTerminals.indexOf(top)][terminals.indexOf(token)] != null){
                    stack.pop();
                    inverseRHSMMultiPush(table[nonTerminals.indexOf(top)][terminals.indexOf(token)]);
                }
                else {
                    System.out.println("error");
                    return;
                }
            }
        }
        System.out.println("Input is Accepted by LL1");
    }


    private void inverseRHSMMultiPush(String rule){
        for (int i =rule.length()-1; i>=0; i--){
            stack.push(rule.charAt(i)+"");
        }
    }
    private String nextToken() {
        indexOfInput++;
        if(indexOfInput == lengthofInput){
            return null;
        }
        char ch=this.input.charAt(indexOfInput);

        return String.valueOf(ch);
    }
    public static void main(String[] args) {
        // TODO code application logic here

        Parser parser=new Parser("a+(a+a*(a)+a)");
        parser.parse();

    }

}