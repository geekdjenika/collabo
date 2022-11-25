package geek.djenika.collabo.Controlleurs;

import geek.djenika.collabo.Configuration.ResponseHandler;
import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Services.CollaborateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CollaborateurController {

    @Autowired
    CollaborateurService collaborateurService;

    @GetMapping("/collabos")
    public ResponseEntity<Object> getAllCollabo() {
        return ResponseHandler.generateResponse(
                "OK",
                HttpStatus.OK,
                collaborateurService.findAllCollabo()
                );
    }

    @GetMapping("/collabos/owned")
    @PostFilter("filterObject.owner==authentication.name")
    public List<Collaborateur> getCollaboOwnedBy() {
        return collaborateurService.findAllCollabo();

    }

    @PostMapping("/collabos")
    public ResponseEntity<Object> saveCollabos(@RequestBody Collaborateur newCollabo, Authentication auth) {
        System.out.println(newCollabo.getNom()+"  "+auth.getName());
        return ResponseHandler.generateResponse(
                "Créer avec succès !",
                HttpStatus.CREATED,
                collaborateurService.saveCollabo(newCollabo)
        );

    }

    @GetMapping("/collabos/{id}")
    public ResponseEntity<Object> getCollaboById(@PathVariable("id") int collaboId) {
        return ResponseHandler.generateResponse(
                "Voici le collaborateur : ",
                HttpStatus.OK,
                collaborateurService.findCollaboById(collaboId).get()
        );

    }

    @PutMapping("/collabos/{id}")
    public ResponseEntity<Object> updateCollabo(@PathVariable("id") int collaboId,@RequestBody Collaborateur newCollabo) {
        return ResponseHandler.generateResponse(
                "Les données du collaborateur sont mises à jour avec succès !",
                HttpStatus.OK,
                collaborateurService.updateCollabo(collaboId,newCollabo)
        );

    }

    @DeleteMapping("/collabos/{id}")
    public ResponseEntity<Object> deleteCollabo(@PathVariable("id") int collaboId) {
        collaborateurService.deleteCollabo(collaboId);
        return ResponseHandler.generateResponse(
                "Collaborateur supprimer avec succès !",
                HttpStatus.NO_CONTENT,
                collaborateurService.deleteCollabo(collaboId)
        );

    }

    @GetMapping("/collabos/search")
    public ResponseEntity<?> userDetails(Authentication auth, @RequestParam("cname") String cName) {
        System.out.println(auth.getName().toString());
        Collaborateur collaborateur=collaborateurService.findByCollaboName(cName);
        if(collaborateur==null) {
            return ResponseHandler.generateResponse(
                    "Collaborateur non trouvé !",
                    HttpStatus.NOT_FOUND,
                    "Collaborateur "+cName+" non trouvé !"
            );
        }
        return ResponseHandler.generateResponse(
                "Voici le collaborateur trouvé :",
                HttpStatus.NOT_FOUND,
                collaborateur
        );
    }
}
