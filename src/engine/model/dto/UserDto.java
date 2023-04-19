package engine.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
    @Email
    private String email;
    @Size(min = 5)
    private String password;
}
