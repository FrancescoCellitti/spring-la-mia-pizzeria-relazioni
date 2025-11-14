package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.model.Offerts;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Pizze> pizze;
        if (q != null && !q.isBlank()) {
            pizze = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q);
        } else {
            pizze = repository.findAll();
        }

        model.addAttribute("pizze", pizze);
        model.addAttribute("q", q);
        return "pizza/index";
    }

    @GetMapping("/pizza/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizze pizza = repository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizza/show";
    }

    @GetMapping("/pizza/addPizza")
    public String addForm(Model model) {
        model.addAttribute("pizza", new Pizze());
        return "pizza/addPizza";
    }

    @PostMapping("/pizza/addPizza")
    public String create(@Valid @ModelAttribute("pizza") Pizze formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", formPizza);
            return "pizza/addPizza";
        }

        repository.save(formPizza);
        return "redirect:/";
    }

    @GetMapping("/pizza/modifica/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Pizze pizza = repository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizza/modify"; // template: templates/pizza/modify.html
    }

    @PostMapping("/pizza/modifica/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("pizza") Pizze formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizza/modify";
        }

         repository.save(formPizza);
        return "redirect:/";
    }

    @PostMapping("/pizza/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        repository.deleteById(id);  
        return "redirect:/";
    }

    @GetMapping("/pizza/{id}/offerts")
    public String offertsCreateForm(@PathVariable("id") Integer id, Model model) {
        Pizze pizza = repository.findById(id).orElse(null);
        Offerts offerts = new Offerts();
        offerts.setPizza(pizza);
        model.addAttribute("pizza", pizza);
        model.addAttribute("Offerts", offerts);
        return "Offerts/create";
    }

}
