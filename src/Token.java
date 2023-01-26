public class Token {
    private final TokenType type;
    private final String lexeme;
    private final int location;


    public Token(TokenType type, String lexeme, int location){
        this.type = type;
        this.lexeme = lexeme;
        this.location = location;
    }

    public int getLocation(){
        return this.location;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "["+type.toString().toLowerCase()+", "+lexeme+", "+location+"]";
    }

    public String getErrorMessage() {
        StringBuilder errorMessage = new StringBuilder("Lexical error: Invalid ");
        if(type == TokenType.INVALIDID){
            errorMessage.append("identifier");
        }
        else if(type == TokenType.INVALIDCHAR){
            errorMessage.append("character");
        }
        else if(type == TokenType.INVALIDCOMMENT){
            errorMessage.append("comment");
        }
        else if(type == TokenType.INVALIDNUM){
            errorMessage.append("number");
        }
        errorMessage.append(": \""+lexeme+"\": line "+location+".");
        return errorMessage.toString();
    }
}
