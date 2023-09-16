package pl.pb.storageproject.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 20)
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-z]+$", message = "Must contains only small letters")
    @NotBlank(message = "Pole nie moze byc puste!")
    private String name;


    /*
    @OneToMany(mappedBy = "category")
    private Set< Information > informations = new HashSet<>();
    */

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}



