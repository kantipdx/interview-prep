package systemdesign.coffeemachine.exception;

public class NotSufficientIngredientException extends RuntimeException {
    private String message;
    private String ingredientName;

    public NotSufficientIngredientException(String ingredientName, String message){
        this.ingredientName = ingredientName;
        this.message = message;
    }

    @Override
    public String getMessage(){
        return ingredientName + message;
    }
}
