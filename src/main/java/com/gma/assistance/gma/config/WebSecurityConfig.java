package com.gma.assistance.gma.config;

import com.gma.assistance.gma.service.impl.UserDetailPasswordService;
import com.gma.assistance.gma.service.impl.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;
    @Autowired
    private UserDetailPasswordService userDetailPasswordService;


//    public WebSecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService, UserDetailPasswordService userDetailPasswordService) {
//        this.userPrincipalDetailsService = userPrincipalDetailsService;
//        this.userDetailPasswordService = userDetailPasswordService;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/index.html").permitAll()
//                .antMatchers("/profile/**").authenticated()
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/management/**").hasAnyAuthority("ADMIN", "MANAGER")
//                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginProcessingUrl("/signin")
                        .loginPage("/login").permitAll()
                        .usernameParameter("txtUsername")
                        .passwordParameter("txtPassword")
//                        .defaultSuccessUrl("/administration")
//                        .defaultSuccessUrl("/register")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                );
        http
                .logout(logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID", "remember-me")
//                        .logoutUrl("/my/logout")
//                        .logoutSuccessHandler(logoutSuccessHandler)
//                        .addLogoutHandler(logoutHandler)
                );
//                .and()
        http.rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(7 * 24 * 60 * 60) // expiration time: 7 days
                .key("mySecret!")
                .rememberMeParameter("checkRememberMe")
                .userDetailsService(userPrincipalDetailsService);  // cookies will survive if restarted

        /*
         *   After the session timeout, we can redirect use to specific page if they submit a request with invalid session ID.
         *   To configure the redirect URL, we can use the configure method by overriding the WebSecurityConfigurerAdapter.
         * */
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**"); // #3
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        daoAuthenticationProvider.setUserDetailsPasswordService(this.userDetailPasswordService);

        return daoAuthenticationProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
        tokenRepo.setDataSource(dataSource);
        return tokenRepo;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
