public class Main {
    public static void main(String[] args) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer("assignment1.COMP442-6421.paquet.2023.4/lexpositivegrading.src");
        Token token = la.getNextToken();
        int currentLineNumber = 1;

        while(token != null){
            while(token.getLocation() != currentLineNumber){
                currentLineNumber++;
                System.out.println();
            }
            System.out.print(token);
            token = la.getNextToken();
        }

    }
}