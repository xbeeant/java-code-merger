package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 类型参数合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class TypeParameterMerger extends AbstractNodeMerger<TypeParameter> {
    @Override
    public boolean isEqual(TypeParameter first, TypeParameter second) {
        AbstractNodeMerger<SimpleName> simpleNameMerger = AbstractNodeMerger.getMerger(SimpleName.class, isKeepFirstWhenConflict());
        return simpleNameMerger.isEqual(first.getName(), second.getName());
    }

    @Override
    public TypeParameter doMerge(TypeParameter first, TypeParameter second) {
        TypeParameter tp = new TypeParameter();
        tp.setName(isKeepFirstWhenConflict() ? first.getName() : second.getName());

        AbstractNodeMerger<ClassOrInterfaceType> classOrInterfaceTypeMerger = AbstractNodeMerger.getMerger(ClassOrInterfaceType.class, isKeepFirstWhenConflict());
        first.setTypeBound(classOrInterfaceTypeMerger.mergeCollection(first.getTypeBound(), second.getTypeBound()));

        AbstractNodeMerger<AnnotationExpr> annotationExprMerger = AbstractNodeMerger.getMerger(AnnotationExpr.class, isKeepFirstWhenConflict());
        tp.setAnnotations(annotationExprMerger.mergeCollection(first.getAnnotations(), second.getAnnotations()));
        return super.doMerge(first, second);
    }
}
