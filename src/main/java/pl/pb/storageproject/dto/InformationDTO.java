package pl.pb.storageproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pb.storageproject.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationDTO {

    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters long")
    @NotBlank(message = "Pole nie moze byc puste!")
    private String title;

    @NotBlank(message = "Pole nie moze byc puste!")
    @Size(min = 5, max = 500, message = "Content must be between 3 and 500 characters long")
    private String content;

    @NotNull
    private Category category;


}
