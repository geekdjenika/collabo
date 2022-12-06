package geek.djenika.collabo.Service;

import geek.djenika.collabo.Model.Collaborateur;

import java.util.List;

public interface CollaborateurService {

    //Service pour la gestion des collaborateurs
    Collaborateur addNewCollabo(Collaborateur collaborateur);
    List<Collaborateur> listCollabos();
    Collaborateur updateCollabo(long id, Collaborateur collaborateur);
    Collaborateur deleteCollabo(long id);
}
