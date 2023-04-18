package Common.Errors;

import java.util.ArrayList;
import java.util.Comparator;

public class ErrorLogger {
    private static ErrorLogger instance;
    private ArrayList<CompilerError> errors;

    private ErrorLogger(){
        errors = new ArrayList<>();
    }

    public static ErrorLogger getInstance(){
        if(instance == null){
            instance = new ErrorLogger();
        }
        return instance;
    }

    public void add(CompilerError error){
        errors.add(error);
    }

    public void deleteAll(){
        errors.clear();
    }

    public String getLexErrors(){
        errors.sort(Comparator.comparingInt(CompilerError::getLineNumber));

        StringBuilder lexErrors = new StringBuilder();
        for(var error: errors){
            if(error.getErrorType() == ErrorType.LexError){
                lexErrors.append(error).append("\n");
            }
        }
        return lexErrors.toString();
    }
    public String getSyntaxErrors(){
        errors.sort(Comparator.comparingInt(CompilerError::getLineNumber));

        StringBuilder lexErrors = new StringBuilder();
        for(var error: errors){
            if(error.getErrorType() == ErrorType.SyntaxError){
                lexErrors.append(error).append("\n");
            }
        }
        return lexErrors.toString();
    }
    public String getSemanticErrors(){
        errors.sort(Comparator.comparingInt(CompilerError::getLineNumber));

        StringBuilder lexErrors = new StringBuilder();
        for(var error: errors){
            if(error.getErrorType() == ErrorType.SemanticError || error.getErrorType() == ErrorType.SemanticWarning){
                lexErrors.append(error).append("\n");
            }
        }
        return lexErrors.toString();
    }
    public String getErrors(){
        errors.sort(Comparator.comparingInt(CompilerError::getLineNumber));

        StringBuilder lexErrors = new StringBuilder();
        for(var error: errors){
            lexErrors.append(error).append("\n");
        }
        return lexErrors.toString();
    }

    public boolean isAccepted(){
        for(var error: errors){
            if(error.getErrorType() != ErrorType.SemanticWarning)
                return false;
        }
        return true;
    }


    public static void main(String[] args){
        ErrorLogger el = ErrorLogger.getInstance();
        el.add(new CompilerError(ErrorType.LexError, "Error1", 1));
        el.add(new CompilerError(ErrorType.SemanticError, "Error1", 2));
        el.add(new CompilerError(ErrorType.SyntaxError, "Error1", 3));
        el.add(new CompilerError(ErrorType.LexError, "Error1", 2));
        el = ErrorLogger.getInstance();
        el.add(new CompilerError(ErrorType.SyntaxError, "Error1", 2));

        System.out.println(el.getLexErrors());
        System.out.println("****");
        System.out.println(el.getSemanticErrors());
        System.out.println("****");
        System.out.println(el.getSyntaxErrors());
        System.out.println("****");
        System.out.println(el.getErrors());

    }
}
