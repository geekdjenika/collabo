package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.UserDetailsImpl;
import geek.djenika.collabo.Modeles.Utilisateur;
import geek.djenika.collabo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur user=userRepo.findByUserName(username);

//		System.out.println("inside impl "+user);

        if(user==null) {
            System.out.println("exception thrown");
            throw new UsernameNotFoundException(username + "not found");
        }
        return new UserDetailsImpl(user);
    }
}
