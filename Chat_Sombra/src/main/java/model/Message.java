package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@NamedQuery(name = "model.Message.getAll", query = "SELECT m from Message m")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "text", length = 500)
    private String text;

    @Column(name = "date")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "conversation_id")
    @JsonIgnore
    private Conversation conversation;
}
