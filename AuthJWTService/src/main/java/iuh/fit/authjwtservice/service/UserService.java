package iuh.fit.authjwtservice.service;

import iuh.fit.authjwtservice.authen.UserPrincipal;
import iuh.fit.authjwtservice.entity.User;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
}
