package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Utilisateur;
import geek.djenika.collabo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UtilisateurServiceImpl implements UtilisateurService{
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    @Transactional(readOnly = true)
    public Utilisateur findByUsername(String username) {
        Utilisateur utilisateur = new Utilisateur();
        try {
             utilisateur = utilisateurRepository.findByUsername(username);
        } catch (Exception e) {
            System.out.println("A ma marché !");
            throw e;
        }
        return utilisateur;
    }
}
