package com.emazon.stock.domain.puertos.out;

import com.emazon.stock.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

        Optional<User> findUserByToken(String token);

}
