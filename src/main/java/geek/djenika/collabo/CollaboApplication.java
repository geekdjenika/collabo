package geek.djenika.collabo;


import geek.djenika.collabo.Model.Profil;
import geek.djenika.collabo.Model.Utilisateur;
import geek.djenika.collabo.Repository.ProfilRepository;
import geek.djenika.collabo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class CollaboApplication {

	@Autowired
	ProfilRepository profilRepository;

	public static void main(String[] args) {
		SpringApplication.run(CollaboApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {

			//Ajout des rôles
			if (profilRepository.findAll().size() == 0) {
				accountService.addNewProfile(new Profil(null,"USER"));
				accountService.addNewProfile(new Profil(null,"ADMIN"));
			}

			//Ajout des utilisateur initiales
			if (accountService.listUsers().size() == 0) {
				accountService.addNewUser(new Utilisateur(null,"geekdjenika","inconnu",new ArrayList<>()));
				//Attribution du rôle de geekdjenika
				accountService.addProfileToUser("geekdjenika","ADMIN");

				accountService.addNewUser(new Utilisateur(null,"mamdy017","mamdy",new ArrayList<>()));
				//Attribution du rôle de mamdy017
				accountService.addProfileToUser("mamdy017","USER");

				accountService.addNewUser(new Utilisateur(null,"aoua1414","aoua1414",new ArrayList<>()));
				//Attribution du rôle de aoua1414
				accountService.addProfileToUser("aoua1414","USER");

			}

		};
	}
}
