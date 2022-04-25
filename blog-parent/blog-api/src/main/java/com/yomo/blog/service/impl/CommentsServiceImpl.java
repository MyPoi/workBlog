package com.yomo.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yomo.blog.dao.mapper.CommentsMapper;
import com.yomo.blog.dao.pojo.Comment;
import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.service.CommentsService;
import com.yomo.blog.service.SysUserService;
import com.yomo.blog.utils.UserThreadLocal;
import com.yomo.blog.vo.CommentVo;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.UserVo;
import com.yomo.blog.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论业务实现类
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result commentsByArticleId(Long articleId) {
        /**
         * 1、根据文章id查询评论，从comment表中查询
         * 2、根据作者id，查询作者信息，
         * 3、判断如果level = 1 是否存在子评论
         * 4、如果有，根据评论id查询 （parent_id）
         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId).eq(Comment::getLevel, 1);
        List<Comment> comments = commentsMapper.selectList(queryWrapper);
        return Result.success(copyList(comments));
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId())
                .setAuthorId(sysUser.getId())
                .setContent(commentParam.getContent())
                .setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) comment.setLevel(1);
        else comment.setLevel(2);
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentsMapper.insert(comment);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();
        comments.forEach(comment -> commentVoList.add(copy(comment)));
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtil.copyProperties(comment, commentVo);
        commentVo.setId(String.valueOf(comment.getId()));
        // 作者信息 作者id
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        // 子评论
        Integer level = comment.getLevel();
        if (1 == level) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        // 给谁评论
        if (level > 1) {
            Long toUid = comment.getToUid();
            UserVo toUserVo = this.sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id).eq(Comment::getLevel, 2);
        return copyList(commentsMapper.selectList(queryWrapper));
    }
}
