package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Modeles.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
    @Query(value = "SELECT * FROM collaborateur WHERE collaborateur.collabo_nom = :collabo_nom;", nativeQuery = true)
    Collaborateur findByCollaboName(String collabo_nom);
}
