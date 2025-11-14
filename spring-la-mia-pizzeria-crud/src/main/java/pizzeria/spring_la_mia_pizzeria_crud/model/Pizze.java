package pizzeria.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="pizzas")
public class Pizze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name must not be null")
    @Column(name="name", nullable = false)
    private String name;

    @NotBlank(message = "description must not be null")
    @Column(name="description", nullable = false)
    private String description;
    
    public Pizze() {
        // no-arg constructor required by JPA
    }

    public Pizze(@NotBlank(message = "name must not be null") String name,
            @NotBlank(message = "description must not be null") String description) {
        this.name = name;
        this.description = description;
    }

    private String image;

    @Positive(message="price must be positive")
    @Column(name="price", nullable = false)
    private double price;
    
    @Lob
    private String synopsis;

    @OneToMany(mappedBy = "pizza")
    private List<Offerts> Offerts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Offerts> getOfferts() {
        return Offerts;
    }

    public void setOfferts(List<Offerts> offerts) {
        this.Offerts = offerts;
    }

    @Override
    public String toString(){
        return String.format("%s %s %.2f", this.name, this.description, this.price);
    }
}
