package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
@NamedQuery(name = "model.User.getAll", query = "SELECT u from User u")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login", length = 32,unique = true)
    private String login;

    @Column(name = "role", length = 32)
    private String role="user";

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 32)
    private String phone;

    @Column(name = "enabled")
    private boolean enabled=false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messageSet;

    @ManyToMany
    @JoinTable(name = "friend",
            joinColumns = @JoinColumn(name = "requester_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id", nullable = false))
    @JsonIgnore
    private List<User> friends;
}
