package pl.pb.storageproject.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 20, nullable = false)
    @Size(min = 3, max = 20, message = "Name must be between three and twenty characters long")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must consists of only letters and first must be capital")
    @NotBlank(message = "Pole nie moze byc puste!")
    private String name;


    @Column(length = 50, nullable = false)
    @Size(min = 3, max = 50, message = "Surname must be between 3 and 50 characters long")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must consists of only letters and first must be capital")
    @NotBlank(message = "Pole nie moze byc puste!")
    private String surname;


    @Column(length = 20, unique = true, nullable = false)
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-z]+$", message = "Must contains only small letters")
    @NotBlank(message = "Pole nie moze byc puste!")
    private String login;

    @Column(nullable = false)
    @Size(min = 5, message = "Password must be longer than 5 characters ")
    @NotBlank(message = "Pole nie moze byc puste!")
    private String password;

    @NotNull
    @Column(nullable = false)
    @Min(value = 18, message = "Age must be greater than 18")
    private Integer age;

    @Column(length = 50, nullable = false)
    @Email
    @NotBlank(message = "Pole nie moze byc puste!")
    private String email;

    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id")
    )
    private java.util.Set< Role > roles = new HashSet<>();

    public User(String name, String surname, String login, String password, Integer age, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }
}
