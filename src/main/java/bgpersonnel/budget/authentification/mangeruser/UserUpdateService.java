package bgpersonnel.budget.authentification.mangeruser;


public interface UserUpdateService {

    void updateUserInfo(UserUpdateRequest userUpdateRequest);

    void updatePassword(UpdatedPassword updatedPassword);

}

