package com.seven.Blog.service;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.pojo.Tag;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: Tag Service
 * Created At 2018/12/24
 */
public interface TagService {

    PageInfo queryTags(Integer status, int pageNum, int pageSize);

    List<Tag> queryTagOptions();

    Tag queryTagById(Integer id);

    boolean insertTag(Tag tag);

    boolean updateTag(Tag tag);

    boolean updateStatus(Integer id);

    boolean deleteTag(Integer id);
}