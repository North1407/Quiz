package com.vti.examwebsise.examonline.sercutity;


import com.vti.examwebsise.examonline.sercutity.oauth.CustomerOAuth2User;
import com.vti.examwebsise.examonline.sercutity.oauth.CustomerOAuth2UserService;
import com.vti.examwebsise.examonline.sercutity.oauth.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerOAuth2UserService oAuth2UserService;
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    DatabaseLoginSuccessHandler databaseLoginSuccessHandler;
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/account").authenticated()
                .antMatchers("/exam/**", "/results/**").hasAuthority("ROLE_USER")
                .antMatchers("/manage/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successHandler(databaseLoginSuccessHandler)
                    .permitAll()
                .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .userInfoEndpoint()
                    .userService(oAuth2UserService)
                    .and()
                    .successHandler(oAuth2LoginSuccessHandler)
                .and().logout().permitAll()
                    .and()
                    .rememberMe()
                    .key("AbcDefgHijKlmnOpqrs_1234567890")
                    .tokenValiditySeconds(7 * 24 * 60 * 60);

        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }


}
