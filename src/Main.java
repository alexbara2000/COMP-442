public class Main {
    public static void main(String[] args) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexnegativegrading.src");
        Token token = la.getNextToken();
        int currentLineNumber = 1;

        while(token != null){
            if(token.getLocation() != currentLineNumber){
                currentLineNumber = token.getLocation();
                System.out.println();
            }
            System.out.print(token);
            System.out.print(" ");
            token = la.getNextToken();
        }

    }
}