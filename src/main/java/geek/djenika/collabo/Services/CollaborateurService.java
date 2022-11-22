package geek.djenika.collabo.Services;

import geek.djenika.collabo.Modeles.Collaborateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollaborateurService {
    List<Collaborateur> getAll();
}
