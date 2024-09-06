package com.emazon.stock.infraestructure.config.security;

import com.emazon.stock.domain.puertos.out.security.TokenProviderPort;
import com.emazon.stock.infraestructure.adapters.security.JwtIOTokenAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanSecurityConfiguration {

    @Bean
    public TokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
