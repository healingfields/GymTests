package ma.showmaker.gymTests.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "answers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "question_id"})    )
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false, unique = false)
    @JsonIgnore
    private Question question;

    public Answer(String content, User user, Question question){
        this.content = content;
        this.user = user;
        this.question = question;
    }

}
