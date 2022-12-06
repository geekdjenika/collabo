package geek.djenika.collabo.Service;

import geek.djenika.collabo.Model.Profil;
import geek.djenika.collabo.Model.Utilisateur;
import geek.djenika.collabo.Repository.ProfilRepository;
import geek.djenika.collabo.Repository.UtilisateurRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@ToString
public class AccountServiceImpl implements AccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ProfilRepository profilRepository;


    @Override
    public Utilisateur addNewUser(Utilisateur utilisateur) {
        String pw = utilisateur.getPassword();
        utilisateur.setPassword(passwordEncoder.encode(pw));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Profil addNewProfile(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public void addProfileToUser(String username, String profileName) {

        Utilisateur utilisateur = utilisateurRepository.findByUserName(username);
        Profil profil = profilRepository.findByProfileName(profileName);

        System.out.println("Voici l'utilisateur voulu : "+utilisateur+" son username est : "+username);
        System.out.println("Voici le profil voulu : "+profil+" sa clé de recherche est : "+username);

        utilisateur.getProfils().add(profil);

    }

    @Override
    public Utilisateur loadUserByUsername(String username) {
        return utilisateurRepository.findByUserName(username);
    }

    @Override
    public List<Utilisateur> listUsers() {
        return utilisateurRepository.findAll();
    }
}
