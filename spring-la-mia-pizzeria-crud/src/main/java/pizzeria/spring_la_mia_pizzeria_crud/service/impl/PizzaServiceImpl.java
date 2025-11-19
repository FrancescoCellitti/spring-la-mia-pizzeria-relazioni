package pizzeria.spring_la_mia_pizzeria_crud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pizzeria.spring_la_mia_pizzeria_crud.exception.ResourceNotFoundException;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.service.PizzaService;

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository repository;

    public PizzaServiceImpl(PizzaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizze> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pizze findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza", id));
    }

    @Override
    public Pizze create(Pizze pizza) {
        pizza.setId(null); // forza insert
        return repository.save(pizza);
    }

    @Override
    public Pizze update(Integer id, Pizze input) {
        Pizze existing = findById(id);
        // campi coerenti con la entity Pizze (in inglese)
        existing.setName(input.getName());
        existing.setDescription(input.getDescription());
        existing.setPrice(input.getPrice());
        existing.setImage(input.getImage());
        existing.setIngridients(input.getIngridients());
        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Pizza", id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizze> searchByTitle(String title) {
        if (title == null || title.isBlank()) return findAll();
        String q = title.trim();
        // solo name:
        return repository.findByNameContainingIgnoreCase(q);
        // oppure name OR description:
        // return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q);
    }
}