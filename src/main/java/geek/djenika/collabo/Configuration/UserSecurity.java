package geek.djenika.collabo.Configuration;

import geek.djenika.collabo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public boolean hasUserId(Authentication authentication, Integer userId) {

        int userID=utilisateurRepository.findByUserName(authentication.getName()).getUserId();
//		System.out.println(userId+"  "+userID);
        if(userID==userId)
            return true;

        return false;

    }

}
