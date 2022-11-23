package geek.djenika.collabo.Repository;

import geek.djenika.collabo.Modeles.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProfilRepository extends JpaRepository<Profil, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO profil(role) values ('ADMIN'),('USER')",nativeQuery = true)
    void ajouterProfils();
}
