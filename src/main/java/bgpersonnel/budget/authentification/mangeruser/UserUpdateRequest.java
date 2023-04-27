package bgpersonnel.budget.authentification.mangeruser;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String email;
}
