package pl.pb.storageproject.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pole nie moze byc puste!")
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection< User > users;

    public Role(String name) {
        this.name = name;
    }
}
