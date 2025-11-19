package pizzeria.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;

public interface PizzaService {

    List<Pizze> findAll();
    Pizze findById(Integer id);
    Pizze create(Pizze pizza);
    Pizze update(Integer id, Pizze pizza);
    void delete(Integer id);
    List<Pizze> searchByTitle(String title);
}
