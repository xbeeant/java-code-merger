package com.xstudio.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 单个成员注释expr合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class SingleMemberAnnotationExprMerger extends AbstractNodeMerger<SingleMemberAnnotationExpr> {
    @Override
    public boolean isEqual(SingleMemberAnnotationExpr first, SingleMemberAnnotationExpr second) {
        return first.getName().equals(second.getName());
    }

    @Override
    public SingleMemberAnnotationExpr doMerge(SingleMemberAnnotationExpr first, SingleMemberAnnotationExpr second) {
        return super.doMerge(first, second);
    }
}
