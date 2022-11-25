package geek.djenika.collabo.Modeles;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="collaborateur")
public class Collaborateur {
    @Id
    @Column(name="collabo_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int collaboid;

    @Column(name="collabo_nom")
    String nom;
    @Column(name="collabo_prenom")
    String prenom;
}
