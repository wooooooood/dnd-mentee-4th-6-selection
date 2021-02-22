package com.selection.domain.article;

import com.selection.domain.user.UserService;
import com.selection.dto.PageRequest;
import com.selection.dto.article.ArticleRequest;
import com.selection.dto.article.ArticleResponse;
import com.selection.dto.article.ArticleSummaryProjection;
import com.selection.dto.article.ArticleSummaryResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final VoteRepository voteRepository;
    private final ChoiceRepository choiceRepository;
    private final GogumaRepository gogumaRepository;
    private final UserService userService;

    public void validationAccess(Article article, String userId) {
        if (!article.getUserId().equals(userId)) {
            throw new IllegalArgumentException(
                String.format("해당 유저(%s)는 게시물(%d)에 접근 권한이 없습니다.", userId, article.getId())
            );
        }
    }

    public Article findArticleById(Long articleId) {
        return articleRepository.findById(articleId)
            .orElseThrow(
                () -> new IllegalArgumentException(
                    String.format("해당 게시글(%s)는 존재하지 않습니다.", articleId)));
    }

    public Long findChoiceIdByVotedAuthor(String userId) {
        Optional<Vote> vote = voteRepository.findByUserId(userId);
        return vote.isPresent() ? vote.get().getChoice().getId() : -1L;
    }

    @Transactional
    public Long create(String userId, ArticleRequest requestDto) {
        Article article = articleRepository.save(requestDto.toEntity(userId));
        return article.getId();
    }

    @Transactional
    public void modify(Long articleId, String userId, ArticleRequest requestDto) {
        Article article = findArticleById(articleId);
        validationAccess(article, userId);
        article.modifyTitle(requestDto.getTitle());
        article.modifyContent(requestDto.getContent());
        article.modifyChoices(requestDto.getChoices());
    }

    @Transactional
    public ArticleResponse lookUp(Long articleId, String userId) {
        Article article = findArticleById(articleId);
        return new ArticleResponse(
            article,
            userService.findByUserId(userId).getNickname(),
            article.getUserId().equals(userId),
            findChoiceIdByVotedAuthor(userId)
        );
    }

    @Transactional
    public void delete(Long articleId, String userId) {
        Article article = findArticleById(articleId);
        validationAccess(article, userId);
        gogumaRepository.deleteAllByInArticleId(articleId);
        List<Long> choiceIds = choiceRepository.findChoiceIdsByArticle(articleId);
        voteRepository.deleteAllByInQuery(choiceIds);
        choiceRepository.deleteAllInByInQuery(choiceIds);
        articleRepository.deleteById(articleId);
    }

    @Transactional
    public void vote(Long articleId, Long choiceId, String userId) {
        Article article = findArticleById(articleId);
        article.voteOnChoice(choiceId, userId);
    }

    public List<ArticleSummaryResponse> lookUpMyArticles(String author, PageRequest pageRequest) {
        List<ArticleSummaryProjection> pagesWithMyArticles =
            articleRepository.findAllByAuthor(author, pageRequest.of());
        return pagesWithMyArticles.stream().map(ArticleSummaryResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<ArticleSummaryResponse> lookUpInDrafts(PageRequest pageRequest) {
        List<ArticleSummaryProjection> articles =
            articleRepository.findDraftGogumas(pageRequest.of());

        return articles.stream()
            .map(ArticleSummaryResponse::new)
            .collect(Collectors.toList());
    }

    public List<ArticleSummaryResponse> lookUpInFires(PageRequest pageRequest) {
        List<ArticleSummaryProjection> articles =
            articleRepository.findFireGogumas(pageRequest.of());

        return articles.stream()
            .map(ArticleSummaryResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<ArticleSummaryResponse> lookUpInHonors(LocalDate when, PageRequest pageRequest) {
        List<ArticleSummaryProjection> articles =
            articleRepository.findHonorGogumasAtTime(when, pageRequest.of());

        return articles.stream()
            .map(ArticleSummaryResponse::new)
            .collect(Collectors.toList());
    }
}
