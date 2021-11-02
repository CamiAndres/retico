package co.usa.ciclo3.ciclo3.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@Entity
@Table(name="category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    
    
    
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "category")
    @JsonIgnoreProperties({"category","message"})//NUEVO MESSAGE
    private List<Audience> audiences;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    


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

    public List<Audience> getAudiences() {
        return audiences;
    }

    public void setAudiences(List<Audience> audiences) {
        this.audiences = audiences;
    }
}
