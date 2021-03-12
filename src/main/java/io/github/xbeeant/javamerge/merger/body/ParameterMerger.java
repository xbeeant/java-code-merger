package io.github.xbeeant.javamerge.merger.body;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author huangxiaobiao
 */
public class ParameterMerger extends AbstractNodeMerger<Parameter> {
    @Override
    public boolean isEqual(Parameter first, Parameter second) {
        return first.getType().equals(second.getType());
    }

    @Override
    public Parameter doMerge(Parameter first, Parameter second) {
        Parameter parameter = new Parameter();
        parameter.setName(isKeepFirstWhenConflict() ? first.getName() : second.getName());
        // annotations
        AbstractNodeMerger<AnnotationExpr> annotationMerger = AbstractNodeMerger.getMerger(AnnotationExpr.class, isKeepFirstWhenConflict());
        parameter.setAnnotations(annotationMerger.mergeCollection(first.getAnnotations(), second.getAnnotations()));
        // type
        parameter.setType(first.getType());
        return parameter;
    }
}
