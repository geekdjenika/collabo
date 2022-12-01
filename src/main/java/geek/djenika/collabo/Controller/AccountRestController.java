package geek.djenika.collabo.Controller;

import geek.djenika.collabo.Model.Profil;
import geek.djenika.collabo.Model.RoleUserModel;
import geek.djenika.collabo.Model.Utilisateur;
import geek.djenika.collabo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    //Pour retrouver tous les utilisateurs
    @GetMapping("/users")
    public List<Utilisateur> utilisateurs() {
        return accountService.listUsers();
    }

    @PostMapping("/users")
    Utilisateur saveUser(@RequestBody Utilisateur utilisateur) {
        return accountService.addNewUser(utilisateur);
    }

    @PostMapping("/roles")
    Profil saveProfile(@RequestBody Profil profil) {
        return accountService.addNewProfile(profil);
    }

    @PostMapping("/addprofiletouser")
    void addProfileToUser(@RequestBody RoleUserModel roleUserModel) {
        accountService.addProfileToUser(roleUserModel.getUsername(), roleUserModel.getProfilename());
    }

}
