package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder()).usersByUsernameQuery("select login,password ,enabled from user where login=?")
                .authoritiesByUsernameQuery(
                        "select login,role from user where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/**").access("hasRole('user')")
                .antMatchers(HttpMethod.GET,"/user/**").access("hasRole('user')")
                .antMatchers(HttpMethod.GET,"/conversation/**").access("hasRole('user')")
                .antMatchers(HttpMethod.POST,"/conversation/**").access("hasRole('user')")
                .antMatchers(HttpMethod.GET,"/message/**").access("hasRole('user')")
                .antMatchers(HttpMethod.POST,"/message/**").access("hasRole('user')")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("//localhost:63343/Chat_Sombra_client/index.html?_ijt=o7j01j1d17tlr0292eq890j6gq#/chat", true)
                .failureUrl("//localhost:63343/Chat_Sombra_client/index.html?_ijt=o7j01j1d17tlr0292eq890j6gq#/login").permitAll()
                .and().headers().disable();

        //Cross Site Request Forgery
        http.csrf().disable();



    }
}
