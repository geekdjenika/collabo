package geek.djenika.collabo.Controller;

import geek.djenika.collabo.Model.Collaborateur;
import geek.djenika.collabo.Service.CollaborateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollaborateurController {

    @Autowired
    CollaborateurService collaborateurService;

    @GetMapping("/collabos")
    @PostAuthorize("hasAuthority('USER')")
    public List<Collaborateur> collaborateurs() {
        return collaborateurService.listCollabos();
    }

    @PostMapping("/collabos")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Collaborateur saveCollabo(@RequestBody Collaborateur collaborateur) {
        return collaborateurService.addNewCollabo(collaborateur);
    }

    @PostMapping("/collabo/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Collaborateur updateCollabo(@PathVariable long id,@RequestBody Collaborateur collaborateur) {
        return collaborateurService.updateCollabo(id,collaborateur);
    }

    @DeleteMapping("/collabo/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Collaborateur deleteCollabo(@PathVariable long id) {
        return collaborateurService.deleteCollabo(id);
    }


}
