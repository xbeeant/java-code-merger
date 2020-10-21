package com.xstudio.javamerge.merger.body;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.xstudio.javamerge.AbstractNodeMerger;
import com.xstudio.javamerge.AbstractTypeMerger;

import java.util.Optional;

/**
 * 类或接口声明合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class ClassOrInterfaceDeclarationMerger extends AbstractTypeMerger<ClassOrInterfaceDeclaration> {
    @Override
    public boolean isEqual(ClassOrInterfaceDeclaration first, ClassOrInterfaceDeclaration second) {
        AbstractNodeMerger<SimpleName> nameMerger = AbstractNodeMerger.getMerger(SimpleName.class, isKeepFirstWhenConflict());
        return nameMerger.isEqual(first.getName(), second.getName());
    }

    @Override
    public ClassOrInterfaceDeclaration doMerge(ClassOrInterfaceDeclaration first, ClassOrInterfaceDeclaration second) {
        ClassOrInterfaceDeclaration coid = new ClassOrInterfaceDeclaration();
        // set name
        coid.setName(first.getName());
        // set interface
        coid.setInterface(isKeepFirstWhenConflict() ? first.isInterface() : second.isInterface());
        // set modifiers
        NodeList<Modifier> modifiers = isKeepFirstWhenConflict() ? first.getModifiers() : second.getModifiers();
        for (Modifier modifier : modifiers) {
            coid.addModifier(modifier.getKeyword());
        }

        // set comments
        AbstractNodeMerger<Comment> commentMerger = AbstractNodeMerger.getMerger(Comment.class, isKeepFirstWhenConflict());
        Optional<Comment> commentOpt = commentMerger.merge(first.getComment(), second.getComment());
        if (commentOpt.isPresent()) {
            coid.setComment(commentOpt.get());
        }

        // set annotation
        AbstractNodeMerger<AnnotationExpr> annotationDeclarationMerger = AbstractNodeMerger.getMerger(AnnotationExpr.class, isKeepFirstWhenConflict());
        coid.setAnnotations(annotationDeclarationMerger.mergeCollection(first.getAnnotations(), second.getAnnotations()));

        // set extendedTypes
        AbstractNodeMerger<ClassOrInterfaceType> classOrInterfaceTypeMerger = AbstractNodeMerger.getMerger(ClassOrInterfaceType.class, isKeepFirstWhenConflict());
        coid.setExtendedTypes(classOrInterfaceTypeMerger.mergeCollection(first.getExtendedTypes(), second.getExtendedTypes()));

        // set type parameters
        AbstractNodeMerger<TypeParameter> typeParameterMerger = AbstractNodeMerger.getMerger(TypeParameter.class, isKeepFirstWhenConflict());
        coid.setTypeParameters(typeParameterMerger.mergeCollection(first.getTypeParameters(), second.getTypeParameters()));

        // set member
        coid.setMembers(mergeMembers(first.getMembers(), second.getMembers()));

        return coid;
    }

    public NodeList<BodyDeclaration<?>> mergeMembers(NodeList<BodyDeclaration<?>> first, NodeList<BodyDeclaration<?>> second) {
        NodeList<BodyDeclaration<?>> nodeList = new NodeList<>();
        if (first.isEmpty()) {
            return second;
        }

        if (second.isEmpty()) {
            return first;
        }

        NodeList<BodyDeclaration<?>> secondCopies = new NodeList<>(second);
        BodyDeclaration<?> found = null;
        for (BodyDeclaration<?> firstNode : first) {
            AbstractNodeMerger merger = AbstractNodeMerger.getMerger(firstNode.getClass(), isKeepFirstWhenConflict());
            for (BodyDeclaration<?> secondNode : secondCopies) {
                if (firstNode.getClass().getName().equals(secondNode.getClass().getName())) {
                    if (merger.isEqual(firstNode, secondNode)) {
                        found = secondNode;
                        break;
                    }
                }
            }

            if (null != found) {
                Optional<Node> mergeResult = Optional.of(merger.doMerge(firstNode, found));
                nodeList.add((BodyDeclaration<?>) mergeResult.get());
                secondCopies.remove(found);
            } else {
                nodeList.add(firstNode);
            }
            found = null;
        }


        if (secondCopies.isNonEmpty()) {
            nodeList.addAll(secondCopies);
        }

        return nodeList;
    }
}
