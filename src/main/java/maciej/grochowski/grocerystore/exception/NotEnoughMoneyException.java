package maciej.grochowski.grocerystore.exception;

public class NotEnoughMoneyException extends Exception {

    private static final String MESSAGE = "Not enough money to complete the purchase. Exception";

    public NotEnoughMoneyException() {
        super(MESSAGE);
    }
}
