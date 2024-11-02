package ma.showmaker.gymTests.Mappers;

import ma.showmaker.gymTests.Response.QuestionResponse;
import ma.showmaker.gymTests.models.Question;

public abstract class QuestionMapper {

    public static QuestionResponse toQuestionResponse(Question question){
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(questionResponse.getId());
        questionResponse.setContent(questionResponse.getContent());
        questionResponse.setCategoryId(question.getCategory().getId());
        return questionResponse;
    }
}
