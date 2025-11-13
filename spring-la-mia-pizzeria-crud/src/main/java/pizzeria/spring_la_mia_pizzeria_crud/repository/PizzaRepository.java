package pizzeria.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;

public interface PizzaRepository extends JpaRepository<Pizze, Integer>{
	// Search by name or description (case-insensitive, partial match)
	List<Pizze> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
