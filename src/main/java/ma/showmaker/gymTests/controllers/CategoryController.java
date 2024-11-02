package ma.showmaker.gymTests.controllers;

import ma.showmaker.gymTests.Mappers.QuestionMapper;
import ma.showmaker.gymTests.Response.QuestionResponse;
import ma.showmaker.gymTests.models.Category;
import ma.showmaker.gymTests.repositories.CategoryRepository;
import ma.showmaker.gymTests.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public QuestionRepository questionRepository;

    @GetMapping("/categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = this.categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{categoryId}/questions")
    @ResponseBody
    public ResponseEntity<Set<QuestionResponse>> getQuestionsByCategory(@PathVariable Long categoryId){
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            Set<QuestionResponse> questionResponses = category.getQuestions()
                    .stream().map(QuestionMapper::toQuestionResponse)
                    .collect(Collectors.toSet());
            return ResponseEntity.ok(questionResponses);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptySet());
    }
}
