package ma.showmaker.gymTests.initializers;

import ma.showmaker.gymTests.models.Category;
import ma.showmaker.gymTests.models.Question;
import ma.showmaker.gymTests.models.User;
import ma.showmaker.gymTests.repositories.AnswerRepository;
import ma.showmaker.gymTests.repositories.CategoryRepository;
import ma.showmaker.gymTests.repositories.QuestionRepository;
import ma.showmaker.gymTests.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public QuestionRepository questionRepository;
    @Autowired
    public AnswerRepository answerRepository;
    @Autowired
    public CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.save(new User("Idriss", "$2a$12$ZEn5obhnpisp8sJbW6ixO.P9TftrpXnm.H1Vd0JPDI067Qr9w5oVe"));
        Category category = categoryRepository.save(new Category("personalInfos"));
        questionRepository.save(new Question("whats ur age?", category));
        questionRepository.save(new Question("whats ur sexe?", category));
        questionRepository.save(new Question("whats ur name?", category));
        questionRepository.save(new Question("whats ur last name?", category));

    }
}
