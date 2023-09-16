package pl.pb.storageproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "INFORMATION")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters long")
    private String title;

    @Column(length = 500, nullable = false)
    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 3, max = 500, message = "Content must be between 3 and 500 characters long")
    private String content;

    @JsonProperty("start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    @Valid
    private Category category;

    @OneToOne
    private User user;

    /*
    public Information() {}

    public Information(String title, String content, LocalDate date, Category category, User user) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
        this.user = user;
    }
     */

}
