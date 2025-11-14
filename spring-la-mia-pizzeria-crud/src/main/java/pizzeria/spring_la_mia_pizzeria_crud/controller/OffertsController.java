package pizzeria.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.Offerts;
import pizzeria.spring_la_mia_pizzeria_crud.repository.OffertsRepository;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Controller
@RequestMapping("/Offerts")
public class OffertsController {

    @Autowired
    private OffertsRepository repository;
    @Autowired
    private PizzaRepository pizzaRepository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("Offerts") Offerts formOfferts, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Offerts/create";
        }
        Integer pizzaId = formOfferts.getPizza() != null ? formOfferts.getPizza().getId() : null;
        if (pizzaId != null) {
            Pizze pizza = pizzaRepository.findById(pizzaId).orElse(null);
            formOfferts.setPizza(pizza);
        }
        repository.save(formOfferts);
        if (pizzaId != null) {
            return "redirect:/pizza/" + pizzaId;
        }
        return "redirect:/";
    }
}

