package geek.djenika.collabo.Service;

import geek.djenika.collabo.Model.Profil;
import geek.djenika.collabo.Model.Utilisateur;
import geek.djenika.collabo.Repository.ProfilRepository;
import geek.djenika.collabo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ProfilRepository profilRepository;


    @Override
    public Utilisateur addNewUser(Utilisateur utilisateur) {
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
