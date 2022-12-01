package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfilRepository extends JpaRepository<Profil, Long> {
    @Query(value = "SELECT profil.id, profil.role FROM profil WHERE profil.role = :profil",nativeQuery = true)
    Profil findByProfileName(String profil);
}
