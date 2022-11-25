package geek.djenika.collabo.Modeles;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
public class Utilisateur {
    @Id
    @Column(name="user_id")
    private int userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;


    @Override
    public int hashCode() {

        return this.userId;
    }


    @Override
    public boolean equals(Object obj) {


        if(obj==null || !(obj instanceof Utilisateur) )
            return false;
        return this.userId==((Utilisateur)obj).getUserId();
    }

}
