package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Modeles.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
}
