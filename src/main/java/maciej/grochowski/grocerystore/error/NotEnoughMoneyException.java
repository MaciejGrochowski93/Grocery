package maciej.grochowski.grocerystore.error;

public class NotEnoughMoneyException extends RuntimeException {

    private static final String MESSAGE = "Not enough money to complete the purchase";

    public NotEnoughMoneyException() {
        super(MESSAGE);
    }
}