package pl.pb.storageproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import pl.pb.storageproject.security.handlers.CustomAccessDeniedHandler;

@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/informations","/sharedInformation").hasAnyRole("FULL_USER","LIMITED_USER","ADMIN")
                .antMatchers("/users", "/users/**", "/newCategory", "/updateCategory/**",  "/deleteCategory/**", "/categories","/changeUserRole/**" ).hasRole("ADMIN")
                .antMatchers("/newInformation", "/updateInformation/**", "/shareInformation/**", "/deleteInformation/**","/shareInformation/**" ).hasAnyRole("FULL_USER","ADMIN")
                //.anyRequest().authenticated()
                .and().formLogin().permitAll()
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/loginUser")
                .defaultSuccessUrl("/helloUser")
                .failureUrl("/login-error")
                .and().rememberMe().key("rem-me-key")
                .rememberMeParameter("remember") // it is name of checkbox at login page
                .rememberMeCookieName("rememberlogin") // it is name of the cookie
                .tokenValiditySeconds(100*64)
                .and().logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID").permitAll()
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
                // remember for number of seconds

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(2)
                .expiredUrl("/error/accessDenied");

        http.sessionManagement()
                .invalidSessionUrl("/error/accessDenied");

        http.sessionManagement()
                .sessionFixation().migrateSession();

        //servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**","/","/register");
    }

}
