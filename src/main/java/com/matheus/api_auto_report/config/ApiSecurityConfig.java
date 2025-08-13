package com.matheus.api_auto_report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    //    csrf().disable() → desativa CSRF (proteção de formulário). Normal em APIs REST.
//    authorizeHttpRequests(...).anyRequest().authenticated() → todas as rotas precisam de login.
//    httpBasic() → habilita autenticação HTTP Basic, ou seja, você pode passar username + password no Postman.
//    sessionManagement(...STATELESS) → não usa sessão; cada request precisa mandar autenticação.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer -> customizer.disable()).authorizeHttpRequests(request -> request.anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS))).build();
    }


    //define a regra de validar a autenticacao de quem pode acessar a API
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);


        return provider;
    }

    //define o único usuario permitido a acessar a API
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("1234567"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
