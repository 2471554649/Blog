package com.seven.Blog.service.impl;

import com.seven.Blog.dao.ArticleMapper;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.vo.ServerResponse;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务实现
 * Created At 2018/08/08
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAllArticles(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return articleMapper.getAllArticles(offset, size);
    }

    @Override
    public List<Article> getArticlesByStatus(Integer status, Integer page, Integer size) {
        int offset = (page - 1) * size;
        return articleMapper.getArticlesByStatus(status, offset, size);
    }

    @Override
    public Article getArticleByPrimaryKey(Integer id) {
        return articleMapper.selectedArticleByPrimaryKey(id);
    }

    @Override
    public Article getPublishedArticleByPrimaryKey(Integer id) {
        return articleMapper.selectedPublishedArticleByPrimaryKey(id);
    }

    @Override
    public ServerResponse changeArticleStatus(Integer id) {
        Article article = getArticleByPrimaryKey(id);
        int status;
        if(article.getStatus() == ConstUtil.ArticleStatus.PUBLISHED.getCode())
            status = ConstUtil.ArticleStatus.UNPUBLISHED.getCode();
        else
            status = ConstUtil.ArticleStatus.PUBLISHED.getCode();
        int result = articleMapper.updateArticleStatusByPrimaryKey(id, status);
        if(result == 1)
            return ServerResponse.success("更改状态成功");
        return ServerResponse.success("更改状态失败");
    }


    @Override
    public ServerResponse addArticle(String title, String img, String summary, String content, String categoryId, String status) {
        int result = articleMapper.addArticle(title, img, summary, content, categoryId, status);
        if(result == 1)
            return ServerResponse.success("添加文章成功");
        return ServerResponse.success("添加文章失败");
    }

    @Override
    public int getArticleCount() {
        return articleMapper.getArticleCount();
    }

    @Override
    public int getArticleCountByStatus(Integer status) {
        return articleMapper.getArticleCountByStatus(status);
    }

    @Override
    public int getArticleCountByCategoryId(Integer categoryId) {
        return articleMapper.getPublishedArticleCountByCategoryId(categoryId);
    }

    @Override
    public ServerResponse deleteArticleByPrimaryKey(Integer id) {
        int result = articleMapper.deleteArticleByPrimaryKey(id);
        if(result == 1)
            return ServerResponse.success("删除文章成功");
        return ServerResponse.success("删除文章失败");
    }

    @Override
    public ServerResponse updateArticle(Article article) {
        int result = articleMapper.updateArticle(article);
        if(result == 1)
            return ServerResponse.success("更新文章成功");
        return ServerResponse.success("更新文章失败");
    }

    @Override
    public List<Article> getAllPublishedArticle(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return articleMapper.getAllPublishedArticles(offset, size);
    }

    @Override
    public List<Article> getPublishedArticleByCategoryIds(String sql, Integer page, Integer size) {
        String[] categoryIds = sql.split(",");
        int offset = (page - 1) * size;
        return articleMapper.getPublishedArticlesByCategoryIds(categoryIds, offset, size);
    }

    @Override
    public List<Article> getPublishedArticleByCategoryId(Integer categoryId, Integer page, Integer size) {
        int offset = (page - 1) * size;
        return articleMapper.getPublishedArticlesByCategoryId(categoryId, offset, size);
    }

    @Override
    public List<Article> getPublishedArticleByKeywords(String keywords, Integer page, Integer size) {
        int offset = (page - 1) * size;
        return articleMapper.getPublishedArticleByKeywords(keywords, offset, size);
    }

    @Override
    public int getArticleCountByKeywords(String keywords) {
        return articleMapper.getPublishedArticleCountByKeywords(keywords);
    }


}
