package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "conversation")
@NamedQuery(name = "model.Conversation.getAll", query = "SELECT c from Conversation c")
@Data
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "date")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime date = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conversation",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Message> messageSet;

    @ManyToMany
    @JoinTable(name = "user_conversation",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    public Set<User> users;

}
