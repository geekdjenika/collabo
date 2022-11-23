package geek.djenika.collabo.Controlleurs;

import geek.djenika.collabo.Configuration.ResponseHandler;
import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Services.CollaborateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collaborateur")
public class AdminController {
    CollaborateurService collaborateurService;
    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterCollabo(@RequestBody Collaborateur collaborateur) {
        if (collaborateurService.ajouter(collaborateur) != null) {
            return ResponseHandler.generateResponse(
                    "Collaborateur ajouter avec succès !",
                    HttpStatus.OK,
                    collaborateurService.ajouter(collaborateur)
            );
        }
        else {
            return ResponseHandler.generateResponse(
                    "Cet collaborateur existe déjà !",
                    HttpStatus.OK,
                    collaborateurService.ajouter(collaborateur)
            );
        }

    }
    @PostMapping("/modifier/{id}")
    ResponseEntity<Object> modifierCollabo(@RequestBody Collaborateur collaborateur,@PathVariable long id) {
        if (collaborateurService.modifier(collaborateur,id) != null) {
            return ResponseHandler.generateResponse(
                    "Collaborateur modifier avec succès !",
                    HttpStatus.OK,
                    collaborateurService.modifier(collaborateur, id)
            );
        } else {
            return ResponseHandler.generateResponse(
                    "Cet collaborateur n'existe pas !",
                    HttpStatus.OK,
                    collaborateurService.modifier(collaborateur,id)
            );
        }

    }
    @DeleteMapping("/supprimer/{id}")
    ResponseEntity<Object> supprimerCollabo(@PathVariable long id) {
        if (collaborateurService.supprimer(id) != null) {
            return ResponseHandler.generateResponse(
                    "Collaborateur supprimer avec succès !",
                    HttpStatus.OK,
                    collaborateurService.supprimer(id)
            );
        } else {
            return ResponseHandler.generateResponse(
                    "Ce collaborateur n'existe plus !",
                    HttpStatus.OK,
                    collaborateurService.supprimer(id)
            );
        }
    }
}
