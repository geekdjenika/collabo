package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Repository.CollaborateurRepository;

import java.util.List;

public class CollaborateurServiceImpl implements CollaborateurService{

    CollaborateurRepository collaborateurRepository;
    @Override
    public List<Collaborateur> getAll() {
        return collaborateurRepository.findAll();
    }

    @Override
    public Object ajouter(Collaborateur collaborateur) {
        Collaborateur collaborateurcourant = collaborateurRepository.findById(collaborateur.getId()).orElse(null);
        if (collaborateurcourant == null) {
            collaborateurRepository.save(collaborateur);
            return collaborateurcourant;
        } else return null;
    }

    @Override
    public Object modifier(Collaborateur collaborateur, long id) {
        Collaborateur collaborateurcourant = collaborateurRepository.findById(id).orElse(null);
        if (collaborateurcourant != null) {
            return collaborateurRepository.save(collaborateur);
        } else return null;
    }

    @Override
    public Object supprimer(long id) {
        Collaborateur collaborateurcourant = collaborateurRepository.findById(id).orElse(null);
        if (collaborateurcourant != null) {
            collaborateurRepository.deleteById(id);
            return collaborateurcourant;
        } else return null;
    }
}
