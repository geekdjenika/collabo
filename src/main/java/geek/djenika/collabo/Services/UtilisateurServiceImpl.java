package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Utilisateur;
import geek.djenika.collabo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UtilisateurServiceImpl implements UtilisateurService{

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> findAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Optional<Utilisateur> findUserById(int id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public Utilisateur findByUserName(String userName) {
        Utilisateur utilisateur=utilisateurRepository.findByUserName(userName);
        return utilisateur;
    }

    @Override
    public Utilisateur saveUser(Utilisateur newUser) {

        Utilisateur utilisateur=utilisateurRepository.save(newUser);
        return utilisateur;

    }

    @Override
    public Utilisateur updateUser(int id,Utilisateur utilisateur) {

        Optional<Utilisateur> retrievedUser=utilisateurRepository.findById(id);
        if(retrievedUser==null)
            try {
                throw new Exception("Utilisateur non trouvé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        utilisateurRepository.save(utilisateur);
        return utilisateurRepository.findById(id).get();

    }

    @Override
    public Utilisateur deleteUser(int userId) {

        Optional<Utilisateur> retrievedUser=utilisateurRepository.findById(userId);
        if(retrievedUser==null)
            try {
                throw new Exception("Utilisateur non trouvé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        utilisateurRepository.deleteById(userId);
        return retrievedUser.get();



    }

}
