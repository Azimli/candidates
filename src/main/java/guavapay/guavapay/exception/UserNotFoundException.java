package guavapay.guavapay.exception;

public class UserNotFoundException extends NotFoundException{

    private static final long serialVersionUID = 5843213248811L;

    public static final String MESSAGE = "Users %s does not exist.";

    public UserNotFoundException(String msg) {
        super(MESSAGE);
    }


}
