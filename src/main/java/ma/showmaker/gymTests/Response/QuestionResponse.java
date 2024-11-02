package ma.showmaker.gymTests.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponse {
    private Long id;
    private String content;
    private Long categoryId;
}
