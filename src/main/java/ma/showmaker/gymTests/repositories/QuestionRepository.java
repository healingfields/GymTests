package ma.showmaker.gymTests.repositories;

import ma.showmaker.gymTests.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}