package pizzeria.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pizzeria.spring_la_mia_pizzeria_crud.model.Pizze;
import pizzeria.spring_la_mia_pizzeria_crud.service.PizzaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {

   
    private PizzaService service;

    public PizzaRestController(PizzaService service){
        this.service = service;
    }

    @GetMapping
    public List<Pizze> index(@RequestParam(name = "title", required = false) String title) {
        return service.searchByTitle(title);
    }

    @GetMapping("/{id}")
    public Pizze show(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizze create(@RequestBody Pizze pizza) {
        return service.create(pizza);
    }

    @PutMapping("/{id}")
    public Pizze update(@PathVariable Integer id, @RequestBody Pizze pizza) {
        return service.update(id, pizza);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
    
    
    

}
