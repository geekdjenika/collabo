package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Modeles.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
    Collaborateur findByCollaboName(String collaboName);
}
