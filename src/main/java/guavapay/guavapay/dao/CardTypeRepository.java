package guavapay.guavapay.dao;

import guavapay.guavapay.model.CardType;
import guavapay.guavapay.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardTypeRepository extends JpaRepository<CardType,Long> {

}
