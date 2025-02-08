package ma.showmaker.gymTests.Mappers;

import ma.showmaker.gymTests.Response.QuestionResponse;
import ma.showmaker.gymTests.models.Question;

public abstract class QuestionMapper {

    public static QuestionResponse toQuestionResponse(Question question){
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setContent(question.getContent());
        questionResponse.setCategoryId(question.getCategory().getId());
        return questionResponse;
    }
}
