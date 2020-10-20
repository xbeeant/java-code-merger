package com.xstudio.javamerge.merger.comments;

import com.github.javaparser.ast.comments.JavadocComment;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * javadoc注释合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class JavadocCommentMerger extends AbstractNodeMerger<JavadocComment> {
    @Override
    public boolean isEqual(JavadocComment first, JavadocComment second) {
        return first.getContent().equals(second.getContent());
    }

    @Override
    public JavadocComment doMerge(JavadocComment first, JavadocComment second) {
        JavadocComment javadocComment = new JavadocComment();
        javadocComment.setContent(isKeepFirstWhenConflict() ? first.getContent() : second.getContent());
        return javadocComment;
    }
}
