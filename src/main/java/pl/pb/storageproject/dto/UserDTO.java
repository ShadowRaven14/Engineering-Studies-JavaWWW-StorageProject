package pl.pb.storageproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 3, max = 20, message = "Name must be between three and twenty characters long")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must consists of only letters and first must be capital")
    private String name;

    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 3, max = 50, message = "Surname must be between 3 and 50 characters long")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must consists of only letters and first must be capital")
    private String surname;

    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-z]+$", message = "Must contains only small letters")
    private String login;

    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 5, message = "Password must be longer than 5 characters long")
    private String password;

    @NotNull
    @Min(value = 18, message = "Age must be greater than 18")
    private Integer age;
}
