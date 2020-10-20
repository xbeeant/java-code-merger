package com.xstudio.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 标记注释expr合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class MarkerAnnotationExprMerger extends AbstractNodeMerger<MarkerAnnotationExpr> {
    @Override
    public boolean isEqual(MarkerAnnotationExpr first, MarkerAnnotationExpr second) {
        return first.toString().equals(second.toString());
    }

    @Override
    public MarkerAnnotationExpr doMerge(MarkerAnnotationExpr first, MarkerAnnotationExpr second) {
        return super.doMerge(first, second);
    }
}
