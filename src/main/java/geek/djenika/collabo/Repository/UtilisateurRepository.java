package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query(value = "SELECT utilisateur.id, utilisateur.password, utilisateur.username FROM utilisateur WHERE utilisateur.username = :username",nativeQuery = true)
    Utilisateur findByUserName(String username);
}
