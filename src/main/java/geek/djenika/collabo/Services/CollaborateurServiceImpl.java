package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Repository.CollaborateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollaborateurServiceImpl implements CollaborateurService{

    CollaborateurRepository collaborateurRepository;
    @Override
    public List<Collaborateur> getAll() {
        return collaborateurRepository.findAll();
    }
}
