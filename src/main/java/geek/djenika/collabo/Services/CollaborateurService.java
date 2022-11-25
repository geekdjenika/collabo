package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Collaborateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CollaborateurService {

    public List<Collaborateur> findAllCollabo() ;

    public Optional<Collaborateur> findCollaboById(int id);

    public Collaborateur findByCollaboName(String collaboName) ;

    Collaborateur saveCollabo(Collaborateur newCollabo);

    Collaborateur updateCollabo(int id, Collaborateur collaborateur);

    Collaborateur deleteCollabo(int collaboId);
    /*Object ajouter(Collaborateur collaborateur);

    Object modifier(Collaborateur collaborateur,long id);

    Object supprimer(long id);*/
}
