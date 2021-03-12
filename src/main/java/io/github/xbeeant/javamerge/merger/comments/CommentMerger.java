package io.github.xbeeant.javamerge.merger.comments;

import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 评论合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class CommentMerger extends AbstractNodeMerger<Comment> {
    @Override
    public boolean isEqual(Comment first, Comment second) {
        return first.getContent().equals(second.getContent());
    }

    @Override
    public Comment doMerge(Comment first, Comment second) {
        if (first instanceof LineComment && second instanceof LineComment) {
            AbstractNodeMerger<LineComment> commentMerger = AbstractNodeMerger.getMerger(LineComment.class, isKeepFirstWhenConflict());
            return commentMerger.merge((LineComment) first, (LineComment) second).get();
        }

        if (first instanceof JavadocComment && second instanceof JavadocComment) {
            return AbstractNodeMerger.getMerger(JavadocComment.class, isKeepFirstWhenConflict()).merge((JavadocComment) first, (JavadocComment) second).get();
        }

        if (first instanceof BlockComment && second instanceof BlockComment) {
            return AbstractNodeMerger.getMerger(BlockComment.class, isKeepFirstWhenConflict()).merge((BlockComment) first, (BlockComment) second).get();
        }

        return isKeepFirstWhenConflict() ? first : second;
    }
}
