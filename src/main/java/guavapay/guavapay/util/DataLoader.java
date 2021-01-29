package guavapay.guavapay.util;

import guavapay.guavapay.dao.CardTypeRepository;
import guavapay.guavapay.dao.UsersRepository;
import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UsersRepository usersRepository;

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

        CardType cards = new CardType();

        cards.setCard_type("MC");

        cards.setHolder_name("Shakir Azimli");
        cards.setPeriod(24);
        cards.setCodeword("shakir");
        cards.setUrgent(true);
        cardTypeRepository.save(cards);
    }
}
