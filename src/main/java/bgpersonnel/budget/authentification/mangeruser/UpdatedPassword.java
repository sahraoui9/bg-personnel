package bgpersonnel.budget.authentification.mangeruser;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatedPassword {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
