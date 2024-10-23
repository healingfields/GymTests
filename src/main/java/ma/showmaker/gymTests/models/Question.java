package ma.showmaker.gymTests.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "questions")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Question(String content,  Category category){
        this.content = content;
        this.category = category;
        category.getQuestions().add(this);
    }
}
