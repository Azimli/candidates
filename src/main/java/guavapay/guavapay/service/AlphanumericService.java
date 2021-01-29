package guavapay.guavapay.service;

import guavapay.guavapay.model.Orders;


public interface AlphanumericService {
    public String generatealphanumeric(Long orders_id);

    public String generateCardNumber();
}
