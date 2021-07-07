package maciej.grochowski.grocerystore.error;

public class IncorrectProductData extends RuntimeException {

    private static final String MESSAGE = "Please, make sure that all Product parameters are correct!";

    public IncorrectProductData() {
        super(MESSAGE);
    }
}
