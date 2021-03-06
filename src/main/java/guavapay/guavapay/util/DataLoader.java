package guavapay.guavapay.util;

import guavapay.guavapay.model.Orders;
import guavapay.guavapay.repository.CardTypeRepository;
import guavapay.guavapay.repository.OrderRepository;
import guavapay.guavapay.repository.UsersRepository;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private UsersRepository usersRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public DataLoader(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Users users =new Users();
        users.setUsername("admin");
        users.setPassword(passwordEncoder.encode("123456"));
        usersRepository.save(users);
    }
}
