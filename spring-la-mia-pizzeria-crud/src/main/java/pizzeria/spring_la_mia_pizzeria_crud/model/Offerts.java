package pizzeria.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "Offerte")
public class Offerts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizze pizza;

    
    @NotNull(message = "the offerts date acnnot be null")
    @PastOrPresent(message = "the offert cannot be set in the future")
    private LocalDate offertDate;
   
    @NotNull(message = "the offerts date cannot be null")
    @PastOrPresent(message = "the offert cannot be set in the future")
    private LocalDate offertDateEnd;

    @Lob
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizze getPizza() {
        return pizza;
    }

    public void setPizza(Pizze pizza) {
        this.pizza = pizza;
    }

    public LocalDate getOffertDate() {
        return offertDate;
    }

    public void setOffertDate(LocalDate offertDate) {
        this.offertDate = offertDate;
    }

    public LocalDate getOffertDateEnd() {
        return offertDateEnd;
    }

    public void setOffertDateEnd(LocalDate offertDateEnd) {
        this.offertDateEnd = offertDateEnd;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
