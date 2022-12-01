package geek.djenika.collabo.Configuration;

import geek.djenika.collabo.Filter.JwtAuthenticationFilter;
import geek.djenika.collabo.Model.Utilisateur;
import geek.djenika.collabo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Désactiver la vérification csrf pour utiliser les sessions
        http.csrf().disable();

        //Utilisation de l'authentification stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();

        //Activer le formulaire d'authentification
        //http.formLogin();

        //Ajout des filtres
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));

        //Demander l'authentification à chaque requette
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Utilisateur utilisateur = accountService.loadUserByUsername(username);

                //Conversion de la liste de rôle en granted authorities
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                utilisateur.getProfils().forEach(profil -> {
                    authorities.add(new SimpleGrantedAuthority(profil.getRole()));
                });

                return new User(utilisateur.getUsername(), utilisateur.getPassword(),authorities);
            }
        });

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
