package guavapay.guavapay.exception;

public class CardsNotFoundException extends NotFoundException{

    private static final long serialVersionUID = 5843213248811L;

    public static final String MESSAGE = "Cards %s does not exist.";

    public CardsNotFoundException(String msg) {
        super(MESSAGE);
    }
}
