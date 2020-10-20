package com.xstudio.javamerge.merger.body;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.xstudio.javamerge.AbstractNodeMerger;

import java.util.Optional;

/**
 * 字段声明合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class FieldDeclarationMerger extends AbstractNodeMerger<FieldDeclaration> {
    @Override
    public boolean isEqual(FieldDeclaration first, FieldDeclaration second) {
        if (first.getVariables().size() != second.getVariables().size()) {
            return false;
        }

        return first.getVariable(0).getName().equals(second.getVariable(0).getName());
    }

    @Override
    public FieldDeclaration doMerge(FieldDeclaration first, FieldDeclaration second) {
        FieldDeclaration fieldDeclaration = new FieldDeclaration();
        fieldDeclaration.setVariables(first.getVariables());

        AbstractNodeMerger<Modifier> modifierMerger = AbstractNodeMerger.getMerger(Modifier.class, isKeepFirstWhenConflict());
        fieldDeclaration.setModifiers(modifierMerger.mergeCollection(first.getModifiers(), second.getModifiers()));

        // field comment
        AbstractNodeMerger<Comment> commentMerger = AbstractNodeMerger.getMerger(Comment.class, isKeepFirstWhenConflict());
        Optional<Comment> commentOptional = commentMerger.merge(first.getComment(), second.getComment());
        if (commentOptional.isPresent()) {
            fieldDeclaration.setComment(commentOptional.get());
        }

        return fieldDeclaration;
    }
}
