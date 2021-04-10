package io.github.xbeeant.javamerge.merger.body;

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
import io.github.xbeeant.javamerge.AbstractNodeMerger;
import io.github.xbeeant.javamerge.AbstractTypeMerger;

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
        boolean isNameEqual = nameMerger.isEqual(first.getName(), second.getName());
        AbstractNodeMerger<ClassOrInterfaceType> classOrInterfaceTypeMerger = AbstractNodeMerger.getMerger(ClassOrInterfaceType.class, isKeepFirstWhenConflict());
        boolean isExtendedTypeEqual = classOrInterfaceTypeMerger.isEqual(first.getExtendedTypes(), second.getExtendedTypes());

        boolean isImplementedTypeEqual = classOrInterfaceTypeMerger.isEqual(first.getImplementedTypes(), second.getImplementedTypes());

        AbstractNodeMerger<TypeParameter> typeParameterMerger = AbstractNodeMerger.getMerger(TypeParameter.class, isKeepFirstWhenConflict());
        boolean isTypeParameterEqual = typeParameterMerger.isEqual(first.getTypeParameters(), second.getTypeParameters());

        return isNameEqual && isExtendedTypeEqual && isImplementedTypeEqual && isTypeParameterEqual;
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
        commentOpt.ifPresent(coid::setComment);

        // set annotation
        AbstractNodeMerger<AnnotationExpr> annotationDeclarationMerger = AbstractNodeMerger.getMerger(AnnotationExpr.class, isKeepFirstWhenConflict());
        coid.setAnnotations(annotationDeclarationMerger.mergeCollection(first.getAnnotations(), second.getAnnotations()));

        AbstractNodeMerger<ClassOrInterfaceType> classOrInterfaceTypeMerger = AbstractNodeMerger.getMerger(ClassOrInterfaceType.class, isKeepFirstWhenConflict());
        // set extendedTypes
        coid.setExtendedTypes(classOrInterfaceTypeMerger.mergeCollection(first.getExtendedTypes(), second.getExtendedTypes()));

        // set implementedTypes
        coid.setImplementedTypes(classOrInterfaceTypeMerger.mergeCollection(first.getImplementedTypes(), second.getImplementedTypes()));

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
