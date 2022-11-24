package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Profil;
import geek.djenika.collabo.Modeles.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    UtilisateurService utilisateurService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.trim().isEmpty()) {
            throw new UsernameNotFoundException("Nom d'utilisateur vide !");
        }

        Utilisateur utilisateur = utilisateurService.findByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur " + username + " non trouvé !");
        }

        return new User(utilisateur.getUsername(), utilisateur.getPassword(),
                getGrantedAuthorities(utilisateur));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Utilisateur utilisateur) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Profil profil = utilisateur.getProfil();
        authorities.add(new SimpleGrantedAuthority(profil.getRole()));
        return authorities;
    }

}
