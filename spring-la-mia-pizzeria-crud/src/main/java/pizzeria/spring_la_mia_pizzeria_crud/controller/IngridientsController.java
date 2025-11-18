package pizzeria.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.Ingridients;
import pizzeria.spring_la_mia_pizzeria_crud.repository.IngridientsRepository;

@Controller
@RequestMapping("/ingridients")
public class IngridientsController {

    @Autowired
    private IngridientsRepository repository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingridients", repository.findAll());
        return "ingridients/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("ingridient", new Ingridients());
        return "ingridients/createOrEdit";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("ingridient") Ingridients form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return "ingridients/createOrEdit";
        }
        repository.save(form);
        return "redirect:/ingridients";
    }
}
