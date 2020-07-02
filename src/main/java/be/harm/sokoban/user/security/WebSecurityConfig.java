package be.harm.sokoban.user.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String H2CONSOLE_LOCATION = "/h2-console/**";

    final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(H2CONSOLE_LOCATION).permitAll()
                .antMatchers("/users/new").permitAll()
                .antMatchers("/users/all").hasAuthority(ApplicationPermission.USERS_READ.getPermission())
                .antMatchers("/games/**").hasAuthority(ApplicationPermission.GAME_PLAY.getPermission())
                .anyRequest().authenticated().and()
                .formLogin();
        httpSecurity.csrf().ignoringAntMatchers(H2CONSOLE_LOCATION);
        httpSecurity.headers().frameOptions().sameOrigin();
    }

}
