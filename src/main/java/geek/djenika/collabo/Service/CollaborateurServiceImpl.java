package geek.djenika.collabo.Service;

import geek.djenika.collabo.Model.Collaborateur;
import geek.djenika.collabo.Repository.CollaborateurRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@ToString
public class CollaborateurServiceImpl implements CollaborateurService{

    @Autowired
    CollaborateurRepository collaborateurRepository;

    @Override
    public Collaborateur addNewCollabo(Collaborateur collaborateur) {
        return collaborateurRepository.save(collaborateur);
    }

    @Override
    public List<Collaborateur> listCollabos() {
        return collaborateurRepository.findAll();
    }

    @Override
    public Collaborateur updateCollabo(long id, Collaborateur collaborateur) {
        Collaborateur retrievedCollabo=collaborateurRepository.findById(id).orElse(null);

        if(retrievedCollabo==null) {
            try {
                throw new Exception("Collaborateur non trouvé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return collaborateur;
        } else  {
            retrievedCollabo.setNom(collaborateur.getNom());
            retrievedCollabo.setPrenom(collaborateur.getPrenom());
            retrievedCollabo.setCreateur(collaborateur.getCreateur());
            collaborateurRepository.save(retrievedCollabo);
            return collaborateurRepository.findById(id).get();
        }


    }

    @Override
    public Collaborateur deleteCollabo(long id) {
        Optional<Collaborateur> retrievedCollabo=collaborateurRepository.findById(id);
        if(retrievedCollabo==null)
            try {
                throw new Exception("Collaborateur non trouvé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        collaborateurRepository.deleteById(id);
        return retrievedCollabo.get();
    }
}
