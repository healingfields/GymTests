package ma.showmaker.gymTests.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "answers")
@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;
}
