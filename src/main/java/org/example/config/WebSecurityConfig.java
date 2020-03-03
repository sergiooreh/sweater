package org.example.config;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)                          //для UserController - @PreAuthorize("hasAuthority('ADMIN')")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   /* @Autowired
    private DataSource dataSource;                                           */   //что-бы можно было входить в БД
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);                            //надёжность ключа шифрования (от 0 до ...)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()            //в нём создаем авторизацию
                    .antMatchers("/","/registration","/static/**","/activate/*").permitAll()        //the/, /registration and /static resources paths are configured to not require any authentication.
                    .anyRequest().authenticated()                                   // All other paths must be authenticated.
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .rememberMe()       //когда сессия expires (? 30 min), то Спринг будет искать вас повторно(все сессии хранит в БД)
                .and()
                     .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {              //во время проверки при log in используется encoder
        auth.userDetailsService(userService)
                    .passwordEncoder(passwordEncoder);
            //    .passwordEncoder(NoOpPasswordEncoder.getInstance());                   // шифрует пароли(deprecated)

    }
}
