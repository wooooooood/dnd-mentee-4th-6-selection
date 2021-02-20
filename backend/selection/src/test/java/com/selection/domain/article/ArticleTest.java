package com.selection.domain.article;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleTest {

    @Test
    @DisplayName("게시글 도메인 테스트")
    public void creatArticle() {
        // given
        final String title = "제목 1";
        final String content = "내용 1";
        final String author = "애플";
        final String anotherAuthor = "히히";
        final String choiceContent1 = "선택지 1";
        final String choiceContent2 = "선택지 2";
        final Long firstChoiceId = 1L;
        final Long secondChoiceId = 2L;

        Article article = new Article(title, content, author);

        final List<Choice> choices = Arrays.asList(
            new Choice(firstChoiceId,choiceContent1, article),
            new Choice(secondChoiceId,choiceContent2, article)
        );

        final List<Goguma> gogumas = Arrays.asList(
            new Goguma(GogumaType.ANGRY, anotherAuthor, article),
            new Goguma(GogumaType.CONFUSED, anotherAuthor, article)
        );

        article.addChoices(choices);
        gogumas.forEach(article::addGoguma);

        article.voteOnChoice(firstChoiceId, anotherAuthor);
        article.voteOnChoice(secondChoiceId, anotherAuthor);

        // when
        Optional<Choice> choice = article.getChoices().findVotedByAuthor(anotherAuthor);

        // then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
        assertThat(article.getAuthor()).isEqualTo(author);
        assertThat(article.getChoices().size()).isEqualTo(2);
        assertThat(article.getGogumas().size()).isEqualTo(2);
        assertThat(choice.isPresent()).isTrue();
        assertThat(choices.get(0).existVoteByAuthor(anotherAuthor)).isFalse();
        assertThat(choice.get().getId()).isEqualTo(2L);

    }
}