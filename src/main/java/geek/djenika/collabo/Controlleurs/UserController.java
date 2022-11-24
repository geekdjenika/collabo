package geek.djenika.collabo.Controlleurs;

import geek.djenika.collabo.Configuration.ResponseHandler;
import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Services.CollaborateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collaborateur")
public class UserController {

    CollaborateurService collaborateurService;


    @GetMapping("/afficher")
    ResponseEntity<Object> getCollaborateurs() {
        return ResponseHandler.generateResponse(
                "Voici nos collaborateurs",
                HttpStatus.OK,
                collaborateurService.getAll()
        );
    }
}
