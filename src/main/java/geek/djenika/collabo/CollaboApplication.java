package geek.djenika.collabo;

import geek.djenika.collabo.Repository.ProfilRepository;
import geek.djenika.collabo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollaboApplication implements CommandLineRunner {

	@Autowired
	ProfilRepository profilRepository;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	public static void main(String[] args) {
		SpringApplication.run(CollaboApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (profilRepository.findAll().size() == 0) {
			profilRepository.ajouterProfils();
		}
		if (utilisateurRepository.findAll().size() == 0) {
			utilisateurRepository.ajouterAdmin();
		}
	}
}
