package guavapay.guavapay.exception;

public class OrdersNotFoundException extends NotFoundException{

    private static final long serialVersionUID = 5843213248811L;

    public static final String MESSAGE = "Orders %s does not exist.";

    public OrdersNotFoundException(String msg) {
        super(MESSAGE);
    }
}
