package io.github.xbeeant.javamerge.merger.body;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.Type;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 方法声明合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class MethodDeclarationMerger extends AbstractNodeMerger<MethodDeclaration> {
    @Override
    public boolean isEqual(MethodDeclaration first, MethodDeclaration second) {
        boolean nameEquals = first.getName().equals(second.getName());
        if (nameEquals) {
            return first.getParameters().size() == second.getParameters().size();
        }

        return nameEquals;
    }

    @Override
    public MethodDeclaration doMerge(MethodDeclaration first, MethodDeclaration second) {
        MethodDeclaration methodDeclaration = new MethodDeclaration();
        // method name
        methodDeclaration.setName(first.getName());
        // method type
        AbstractNodeMerger<Type> typeMerger = AbstractNodeMerger.getMerger(Type.class, isKeepFirstWhenConflict());
        methodDeclaration.setType(typeMerger.doMerge(first.getType(), second.getType()));

        AbstractNodeMerger<Modifier> modifierMerger = AbstractNodeMerger.getMerger(Modifier.class, isKeepFirstWhenConflict());
        methodDeclaration.setModifiers(modifierMerger.mergeCollection(first.getModifiers(), second.getModifiers()));

        methodDeclaration.setSynchronized(isKeepFirstWhenConflict() ? first.isSynchronized() : second.isSynchronized());

        // method comment
        AbstractNodeMerger<Comment> commentMerger = AbstractNodeMerger.getMerger(Comment.class, isKeepFirstWhenConflict());
        Comment comment = commentMerger.doMerge(first.getComment(), second.getComment());
        methodDeclaration.setComment(comment);

        // body
        boolean isInterface = ((ClassOrInterfaceDeclaration) first.getParentNode().get()).isInterface();
        if (isInterface) {
            methodDeclaration.setBody(null);
        } else {
            AbstractNodeMerger<BlockStmt> blockStmtMerger = AbstractNodeMerger.getMerger(BlockStmt.class, isKeepFirstWhenConflict());
            BlockStmt blockStmtOptional = blockStmtMerger.doMerge(first.getBody(), second.getBody());
            methodDeclaration.setBody(blockStmtOptional);
        }

        // set parameters
        AbstractNodeMerger<Parameter> parameterMerger = AbstractNodeMerger.getMerger(Parameter.class, isKeepFirstWhenConflict());
        methodDeclaration.setParameters(parameterMerger.mergeCollection(first.getParameters(), second.getParameters()));

        // annotation
        AbstractNodeMerger<AnnotationExpr> annotationMerger = AbstractNodeMerger.getMerger(AnnotationExpr.class, isKeepFirstWhenConflict());
        methodDeclaration.setAnnotations(annotationMerger.mergeCollection(first.getAnnotations(), second.getAnnotations()));

        return methodDeclaration;
    }
}
