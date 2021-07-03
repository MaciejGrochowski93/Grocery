package maciej.grochowski.grocerystore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RegistrationRequest {

    @NotNull
    @Length(min = 6, max = 30, message = "Your email must consist of 6 to 30 letters.")
    private String email;

    @NotNull
    @Length(min = 6, max = 30, message = "Your password must consist of 6 to 30 letters.")
    private String password;

    @NotNull
    @Length(min = 1, max = 30, message = "Your name must consist of 1 to 30 letters.")
    private String firstName;
}