package pizzeria.spring_la_mia_pizzeria_crud.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;

public interface PizzaRepository extends JpaRepository<Pizze, Integer> {

    // ricerca per campo 'name'
    List<Pizze> findByNameContainingIgnoreCase(String name);

    // opzionale: name OR description (richiede 2 parametri)
    List<Pizze> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
