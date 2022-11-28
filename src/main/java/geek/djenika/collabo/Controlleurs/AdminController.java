package geek.djenika.collabo.Controlleurs;

import geek.djenika.collabo.Configuration.ResponseHandler;
import geek.djenika.collabo.Modeles.Collaborateur;
import geek.djenika.collabo.Services.CollaborateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Map;

@RestController
public class AdminController {

    private final OAuth2AuthorizedClientService authorizedClientService;



    CollaborateurService collaborateurService;

    public AdminController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    private OidcIdToken getIdToken(OAuth2User principal){
        if(principal instanceof DefaultOidcUser) {
            DefaultOidcUser oidcUser = (DefaultOidcUser)principal;
            return oidcUser.getIdToken();
        }
        return null;
    }

    @RolesAllowed("USER")
    @RequestMapping("/**")
    ResponseEntity<Object> getCollaborateurs() {
        return ResponseHandler.generateResponse(
                "Bienvenu USER",
                HttpStatus.OK,
                "ça marche"
                //collaborateurService.getAll()
        );
    }

    @RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/admin")
    ResponseEntity<Object> getADMIN() {
        return ResponseHandler.generateResponse(
                "Bienvenu ADMIN",
                HttpStatus.OK,
                "ça marche"
                //collaborateurService.getAll()
        );
    }

    /*@RequestMapping("/*")
    public String getGithub(Principal user)
    {
        return "Bienvenu, "+user.toString()+" !";
    }*/
    //@RequestMapping("/*")
    public String getUserInfo(Principal user) {
        StringBuffer userInfo= new StringBuffer();

        if(user instanceof UsernamePasswordAuthenticationToken){
            userInfo.append(getUsernamePasswordLoginInfo(user));
        }
        else if(user instanceof OAuth2AuthenticationToken){
            userInfo.append(getOauth2LoginInfo(user));
        }

        return userInfo.toString();
    }

    private StringBuffer getUsernamePasswordLoginInfo(Principal user)
    {
        StringBuffer usernameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()){
            User u = (User) token.getPrincipal();
            usernameInfo.append("Bienvenu, " + u.getUsername());
        }
        else{
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

    private StringBuffer getOauth2LoginInfo(Principal user){
        StringBuffer protectedInfo = new StringBuffer();
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(),
                authToken.getName());

        OAuth2User principal = ((OAuth2AuthenticationToken) user).getPrincipal();

        Map<String,Object> userDetails = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();

        String userToken = authClient.getAccessToken().getTokenValue();
        protectedInfo.append("Bienvenu, " + userDetails.get("name")+"<br><br>");
        protectedInfo.append("e-mail: " + userDetails.get("email")+"<br><br>");
        protectedInfo.append("Votre Token: " + userToken+"<br><br>");

        //protectedInfo.append("Access Token: " + userToken+"<br><br>");

        OidcIdToken idToken = getIdToken(principal);

        if(idToken != null) {

            protectedInfo.append("La valeur de l'idToken: " + idToken.getTokenValue()+"<br><br>");
            protectedInfo.append("Les valeurs associées au token : <br><br>");

            Map<String, Object> claims = idToken.getClaims();

            for (String key : claims.keySet()) {
                protectedInfo.append("  " + key + ": " + claims.get(key)+"<br>");
            }
        }

        return protectedInfo;
    }
/*
    //@PostMapping("/ajouter")
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

    //@PostMapping("/modifier/{id}")
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

    //@DeleteMapping("/supprimer/{id}")
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
    }*/
}
