package com.xstudio.javamerge.merger.ast;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.Name;
import com.xstudio.javamerge.AbstractNodeMerger;

import java.util.Optional;

/**
 * 导入声明合并
 *
 * @author huangxiaobiao
 * @date 2020/10/15
 */
public class ImportDeclarationMerger extends AbstractNodeMerger<ImportDeclaration> {
    @Override
    public boolean isEqual(ImportDeclaration first, ImportDeclaration second) {
        AbstractNodeMerger<Name> nameMerger = AbstractNodeMerger.getMerger(Name.class, isKeepFirstWhenConflict());
        return nameMerger.isEqual(first.getName(), second.getName());
    }

    @Override
    public ImportDeclaration doMerge(ImportDeclaration first, ImportDeclaration second) {
        AbstractNodeMerger<Name> nameMerger = AbstractNodeMerger.getMerger(Name.class, isKeepFirstWhenConflict());
        Optional<Name> name = nameMerger.merge(first.getName(), second.getName());

        // merge comment
        AbstractNodeMerger<Comment> commentMerger = AbstractNodeMerger.getMerger(Comment.class, isKeepFirstWhenConflict());
        ImportDeclaration importDeclaration = new ImportDeclaration(name.get(), first.isStatic(), first.isAsterisk());
        Optional<Comment> comment = commentMerger.merge(first.getComment(), second.getComment());
        if (comment.isPresent()){
            importDeclaration.setComment(comment.get());
        }
        return importDeclaration;
    }
}
