package geek.djenika.collabo.Modeles;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Collaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String nom;
    String prenom;
}
