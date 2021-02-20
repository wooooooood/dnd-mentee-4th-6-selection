package com.selection.dto.article;

import com.selection.domain.article.Article;
import com.selection.dto.choice.ChoiceResponse;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleResponse {

    @ApiModelProperty(notes = "번호", required = true, example = "1")
    private Long id;
    @ApiModelProperty(notes = "제목", required = true, example = "제목")
    private String title;
    @ApiModelProperty(notes = "본문", required = true, example = "본문")
    private String content;
    @ApiModelProperty(notes = "작성자", required = true, example = "애플")
    private String author;
    @ApiModelProperty(notes = "공유수", required = true, example = "0")
    private Long numOfShared;

    @ApiModelProperty(notes = "선택지", required = true)
    private List<ChoiceResponse> choices = new ArrayList<>();

    @ApiModelProperty(notes = "조회자가 선택한 선택지 번호", required = true, example = "-1")
    private Long votedChoiceId = -1L;
    @ApiModelProperty(notes = "작성일자", required = true)
    private LocalDateTime createdAt;

    public ArticleResponse(Article article, Long votedChoiceId) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.numOfShared = article.getNumOfShared();
        this.author = article.getAuthor();
        this.choices.addAll(article.toChoicesResponse());
        this.votedChoiceId = votedChoiceId;
        this.createdAt = article.getCreatedAt();
    }
}
