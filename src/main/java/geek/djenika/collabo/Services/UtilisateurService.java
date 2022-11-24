package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface UtilisateurService {
    Utilisateur findByUsername(String username);
}
