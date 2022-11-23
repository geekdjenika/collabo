package geek.djenika.collabo.Modeles;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "profil")
    private Profil profil;
}
