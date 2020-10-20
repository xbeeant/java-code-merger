package com.xstudio.javamerge.merger.body;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.Type;
import com.xstudio.javamerge.AbstractNodeMerger;

import java.util.Optional;

/**
 * 方法声明合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class MethodDeclarationMerger extends AbstractNodeMerger<MethodDeclaration> {
    @Override
    public boolean isEqual(MethodDeclaration first, MethodDeclaration second) {
        return first.getName().equals(second.getName());
    }

    @Override
    public MethodDeclaration doMerge(MethodDeclaration first, MethodDeclaration second) {
        MethodDeclaration methodDeclaration = new MethodDeclaration();
        // method name
        methodDeclaration.setName(first.getName());
        // method type
        AbstractNodeMerger<Type> typeMerger = AbstractNodeMerger.getMerger(Type.class, isKeepFirstWhenConflict());
        methodDeclaration.setType(typeMerger.merge(first.getType(), second.getType()).get());

        AbstractNodeMerger<Modifier> modifierMerger = AbstractNodeMerger.getMerger(Modifier.class, isKeepFirstWhenConflict());
        methodDeclaration.setModifiers(modifierMerger.mergeCollection(first.getModifiers(), second.getModifiers()));

        methodDeclaration.setSynchronized(isKeepFirstWhenConflict() ? first.isSynchronized() : second.isSynchronized());

        // method comment
        AbstractNodeMerger<Comment> commentMerger = AbstractNodeMerger.getMerger(Comment.class, isKeepFirstWhenConflict());
        Optional<Comment> commentOptional = commentMerger.merge(first.getComment(), second.getComment());
        if (commentOptional.isPresent()) {
            methodDeclaration.setComment(commentOptional.get());
        }

        // body
        AbstractNodeMerger<BlockStmt> blockStmtMerger = AbstractNodeMerger.getMerger(BlockStmt.class, isKeepFirstWhenConflict());
        Optional<BlockStmt> blockStmtOptional = blockStmtMerger.merge(first.getBody(), second.getBody());
        if (blockStmtOptional.isPresent()) {
            methodDeclaration.setBody(blockStmtOptional.get());
        }

        return methodDeclaration;
    }
}
