package edu.codegym.toam.config;


import edu.codegym.toam.filter.JwtRequestFilter;
import edu.codegym.toam.service.account.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountDetailService accountDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //config for jwt auth api
        http.cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/account").permitAll()
                .antMatchers("/api/role").permitAll()
                .antMatchers("/api/property").permitAll()
                .antMatchers("/api/propertiesType").permitAll()
                .antMatchers("/api/address").permitAll()
                .antMatchers(HttpMethod.GET,"/api/account").permitAll()
                .antMatchers(HttpMethod.GET,"/api/role").permitAll()
                .antMatchers(HttpMethod.GET,"/api/property/**").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //test basic auth
//        http.csrf().disable();
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//        http.authorizeRequests().antMatchers("/accountInfor").access("hasAnyRole('ROLE_HOST', 'ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().and().formLogin()//
//                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/login")
//                .defaultSuccessUrl("/userAccountInfo")
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }
}
