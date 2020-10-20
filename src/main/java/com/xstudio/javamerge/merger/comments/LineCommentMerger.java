package com.xstudio.javamerge.merger.comments;

import com.github.javaparser.ast.comments.LineComment;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 行评论合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class LineCommentMerger extends AbstractNodeMerger<LineComment> {
    @Override
    public boolean isEqual(LineComment first, LineComment second) {
        return first.getContent().equals(second.getContent());
    }

    @Override
    public LineComment doMerge(LineComment first, LineComment second) {
        return isKeepFirstWhenConflict() ? first : second;
    }
}
