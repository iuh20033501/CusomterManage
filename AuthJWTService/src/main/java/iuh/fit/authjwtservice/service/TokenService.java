package iuh.fit.authjwtservice.service;

import iuh.fit.authjwtservice.entity.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);

}
