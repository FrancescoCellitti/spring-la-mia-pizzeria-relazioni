package pizzeria.spring_la_mia_pizzeria_crud.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingridients")
public class Ingridients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "La descrizione non può essere vuota")
    private String description;

    @ManyToMany(mappedBy = "ingridients")
    private List<Pizze> pizze;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pizze> getPizze() {
        return pizze;
    }

    public void setPizze(List<Pizze> pizze) {
        this.pizze = pizze;
    }

    
    
    
}
