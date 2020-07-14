package be.harm.sokoban.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

class SecurityConfig {
    @Configuration
    @EnableGlobalMethodSecurity(
            prePostEnabled = true,
            securedEnabled = true,
            jsr250Enabled = true)
    public static class MethodSecurityConfig extends GlobalMethodSecurityConfiguration { }

    @Configuration
    @EnableWebSecurity
    static class MvcSecurityConfig extends WebSecurityConfigurerAdapter {
        private static final String H2CONSOLE_LOCATION = "/h2-console/**";

        private final UserDetailsService userDetailsService;

        public MvcSecurityConfig(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/**").authorizeRequests()
                    .antMatchers(H2CONSOLE_LOCATION).permitAll()
                    .antMatchers("/users/new").permitAll()
                    .antMatchers("/").permitAll()
                    .and()
                        .formLogin()
                        .loginPage("/login")
                            .defaultSuccessUrl("/games")
                            .permitAll().and()
                        .logout()
                            .permitAll();
            httpSecurity.csrf().ignoringAntMatchers(H2CONSOLE_LOCATION);
            httpSecurity.headers().frameOptions().sameOrigin();
        }
    }

    @Configuration
    @Order(1)
    static class RestApiSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/api/**").authorizeRequests()
                    .anyRequest().permitAll();
            httpSecurity.csrf().ignoringAntMatchers("/api/**");
        }
    }
}
