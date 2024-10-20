package ma.showmaker.gymTests.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerDto {
    public Long questionId;
    public String content;
}
