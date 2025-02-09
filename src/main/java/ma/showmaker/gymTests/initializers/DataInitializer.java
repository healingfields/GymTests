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
        Category personalInfosCategory = categoryRepository.save(new Category("personal Infos"));
        Category healthInfosCategory = categoryRepository.save(new Category("health Infos"));
        Category trainingInfosCategory = categoryRepository.save(new Category("training Infos"));
        Category activityInfosCategory = categoryRepository.save(new Category("activity infos"));

        questionRepository.save(new Question("What’s your full name?", personalInfosCategory));
        questionRepository.save(new Question("When is your birthday?", personalInfosCategory));
        questionRepository.save(new Question("Where are you from originally?", personalInfosCategory));
        questionRepository.save(new Question("Do you have any siblings?", personalInfosCategory));
        questionRepository.save(new Question("Are you a morning person or a night owl?", personalInfosCategory));
        questionRepository.save(new Question("Do you prefer staying in or going out on weekends?", personalInfosCategory));

        questionRepository.save(new Question("Do you have any chronic illnesses", healthInfosCategory));
        questionRepository.save(new Question("Are you currently taking any medications or supplements?", healthInfosCategory));
        questionRepository.save(new Question("Have you had any recent surgeries or hospitalizations?", healthInfosCategory));
        questionRepository.save(new Question("Do you have a family history of serious illnesses?", healthInfosCategory));
        questionRepository.save(new Question("Do you have any allergies (food, medication, environmental)", healthInfosCategory));

        questionRepository.save(new Question("How often do you exercise, and what kind of activities do you do?", trainingInfosCategory));
        questionRepository.save(new Question("What does your daily diet typically look like?", trainingInfosCategory));
        questionRepository.save(new Question("How many hours of sleep do you get on average?", trainingInfosCategory));
        questionRepository.save(new Question("Do you smoke, drink alcohol, or use any recreational drugs?", trainingInfosCategory));
        questionRepository.save(new Question("How would you rate your stress levels on a daily basis?", trainingInfosCategory));

        questionRepository.save(new Question("How often do you exercise, and what kind of activities do you do?", activityInfosCategory));
        questionRepository.save(new Question("What does your daily diet typically look like?", activityInfosCategory));
        questionRepository.save(new Question("How many hours of sleep do you get on average?", activityInfosCategory));
        questionRepository.save(new Question("Do you smoke, drink alcohol, or use any recreational drugs?", activityInfosCategory));
        questionRepository.save(new Question("How would you rate your stress levels on a daily basis?", activityInfosCategory));

    }
}
