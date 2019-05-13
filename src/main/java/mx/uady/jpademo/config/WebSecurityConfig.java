package mx.uady.jpademo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import mx.uady.jpademo.repository.UsuarioRepository;
import mx.uady.jpademo.filtro.TokenFiltro;;

/**
 * WebSecurityConfig
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // @Override
    // public void configure(AuthenticationManagerBuilder
    // authenticationManagerBuilder) throws Exception {
    // authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.inMemoryAuthentication().withUser("superUser")
    // .password("$2a$10$fcfPVW4LTSx3uo/Ot5abTeJ1L13l5qMaw6sibBJRDjS9ccDOA4QD.").authorities("ADMIN");
    // // .and().withUser("admin").password("adminPass")
    // // .authorities("ADMIN");
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.csrf().disable().httpBasic().disable()

                .authorizeRequests()
                .antMatchers("/recetas", "/ingredientes", "/recetas/*", "/ingredientes/*", "/recetas/receta/*",
                        "/categorias", "/categorias/*")
                .fullyAuthenticated().antMatchers(HttpMethod.POST, "/categorias").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/recetas/{id}").hasAuthority("ADMIN").antMatchers("/signup", "/login")
                .permitAll().and().addFilterBefore(new TokenFiltro(usuarioRepository), BasicAuthenticationFilter.class);
    }

}