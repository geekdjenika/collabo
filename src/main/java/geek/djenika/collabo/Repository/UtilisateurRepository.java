package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO utilisateur(username,password,profil) values ('geekdjenika','__inconnu__',1)",nativeQuery = true)
    void ajouterAdmin();

    @Query(value = "SELECT utilisateur.id, utilisateur.username, utilisateur.password FROM utilisateur WHERE utilisateur.username = :username;", nativeQuery = true)
    Utilisateur findByUsername(String username);
}
