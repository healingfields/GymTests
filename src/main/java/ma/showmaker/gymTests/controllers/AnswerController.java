package ma.showmaker.gymTests.controllers;

import ma.showmaker.gymTests.Dtos.AnswerDto;
import ma.showmaker.gymTests.models.Answer;
import ma.showmaker.gymTests.models.Question;
import ma.showmaker.gymTests.models.User;
import ma.showmaker.gymTests.repositories.AnswerRepository;
import ma.showmaker.gymTests.repositories.QuestionRepository;
import ma.showmaker.gymTests.repositories.UserRepository;
import ma.showmaker.gymTests.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AnswerController {

    @Autowired
    public AnswerRepository answerRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public QuestionRepository questionRepository;

    @Autowired
    public JwtUtil jwtService;

    @PostMapping("/saveAnswer")
    public ResponseEntity<Answer> saveAnswer(@RequestBody AnswerDto answerDto, @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractNameFromToken(token);
        User user = userRepository.findByUserName(username);
        Optional<Question> question = questionRepository.findById(answerDto.getQuestionId());
        Answer answer = new Answer(answerDto.getContent(), user, question.get());

        Answer savedAnswer = answerRepository.save(answer);
        return ResponseEntity.ok(savedAnswer);
    }
 }
