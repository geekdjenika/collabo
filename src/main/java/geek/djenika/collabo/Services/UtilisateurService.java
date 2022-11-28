package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UtilisateurService {
    public List<Utilisateur> findAllUsers() ;

    public Optional<Utilisateur> findUserById(int id);

    public Utilisateur findByUserName(String userName);

    Utilisateur saveUser(Utilisateur newUser);

    Utilisateur updateUser(int id, Utilisateur utilisateur);

    Utilisateur deleteUser(int userId);
}
