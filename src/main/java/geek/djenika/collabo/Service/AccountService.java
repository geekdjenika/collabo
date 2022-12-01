package geek.djenika.collabo.Service;

import geek.djenika.collabo.Model.Profil;
import geek.djenika.collabo.Model.Utilisateur;

import java.util.List;

public interface AccountService {
    Utilisateur addNewUser(Utilisateur utilisateur);
    Profil addNewProfile(Profil profil);
    void addProfileToUser(String username, String profileName);
    Utilisateur loadUserByUsername(String username);
    List<Utilisateur> listUsers();
}
