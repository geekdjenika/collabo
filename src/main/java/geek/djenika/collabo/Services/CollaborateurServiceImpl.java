package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Repository.CollaborateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class CollaborateurServiceImpl implements CollaborateurService{

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Override
    public List<Collaborateur> findAllCollabo() {
        return collaborateurRepository.findAll();
    }

    @Override
    public Optional<Collaborateur> findCollaboById(int id) {
        return collaborateurRepository.findById(id);
    }

    @Override
    public Collaborateur findByCollaboName(String collaboName) {
        Collaborateur collaborateur=collaborateurRepository.findByCollaboName(collaboName);

        return collaborateur;
    }

    @Override
    public Collaborateur saveCollabo(Collaborateur newCollabo) {

        Collaborateur collaborateur=collaborateurRepository.save(newCollabo);
        return collaborateur;

    }

    @Override
    public Collaborateur updateCollabo(int id,Collaborateur collaborateur) {

        Optional<Collaborateur> retrievedCollabo=collaborateurRepository.findById(id);

        if(retrievedCollabo==null)
            try {
                throw new Exception("Collaborateur non trouvé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        collaborateurRepository.save(collaborateur);
        return collaborateurRepository.findById(id).get();

    }

    @Override
    public Collaborateur deleteCollabo(int collaboId) {

        Optional<Collaborateur> retrievedCollabo=collaborateurRepository.findById(collaboId);
        if(retrievedCollabo==null)
            try {
                throw new Exception("Collaborateur non trouvé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        collaborateurRepository.deleteById(collaboId);
        return retrievedCollabo.get();



    }
/*
    @Override
    public Object ajouter(Collaborateur collaborateur) {
        Collaborateur collaborateurcourant = collaborateurRepository.findById(collaborateur.getCollaboid()).orElse(null);
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
    }*/
}
