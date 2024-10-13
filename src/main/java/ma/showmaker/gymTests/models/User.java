package ma.showmaker.gymTests.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    //to look out
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_name", nullable = false)
    public String userName;

    @Column(name = "password", nullable = false)
    public String password;

    public User(){}

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

}
