package guavapay.guavapay.repository;

import guavapay.guavapay.model.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType,Long> {

}
