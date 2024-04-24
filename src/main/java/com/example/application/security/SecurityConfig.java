package com.example.application.security;

import com.example.application.views.list.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    // Сам по себе класс является связанным с LoginView, так как без метода configure не вышло бы никак войти после ввода данных
    // Так же, если объяснять его методику работы, то работает он следующим образом.
    // Мы передаём в configure класс Http Security с названием http, добавляя сюда Exception для ловли ошибок.
    // После же, через Лямда выражение мы запрашиваем получение авторизации, если всё прошло успешно, мы проходим дальше, если нет - нам выдаёт что "Логин или пароль не действительны".
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

   /* @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("Lucius")
                // password = password with this hash, don't tell anybody :-)
                .password("Yes")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/
}