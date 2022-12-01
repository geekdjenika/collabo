package geek.djenika.collabo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //LAZY pour que les roles ne soit pas chargés automatiquement lors du chargement de l'utilisateur
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Profil> profils = new ArrayList<>();

    public <E> Utilisateur(String username, String password, Collection<Profil> profile) {
    }
}
