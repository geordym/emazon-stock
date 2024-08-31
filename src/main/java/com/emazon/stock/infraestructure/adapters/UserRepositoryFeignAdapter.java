package com.emazon.stock.infraestructure.adapters;


import com.emazon.stock.domain.model.User;
import com.emazon.stock.domain.puertos.out.UserRepositoryPort;
import com.emazon.stock.infraestructure.client.UserFeignClient;
import com.emazon.stock.infraestructure.client.dto.UserInfoDto;
import com.emazon.stock.infraestructure.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryFeignAdapter implements UserRepositoryPort{

    private final UserFeignClient userRepository;


    @Override
    public Optional<User> findUserByToken(String token) {
        UserInfoDto userInfoDto = userRepository.authenticateUserByToken(token);
        User user = new User();
        user.setEmail(userInfoDto.getUsername());
        user.setRole(RoleEnum.fromName(userInfoDto.getRoles().get(0)).toModel());
        user.setPassword(token);
        return Optional.of(user);
    }


}
