package geek.djenika.collabo.Modeles;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String role;
}
