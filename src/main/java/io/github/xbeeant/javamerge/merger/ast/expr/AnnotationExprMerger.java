package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 注释expr合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class AnnotationExprMerger extends AbstractNodeMerger<AnnotationExpr> {
    private static final String NORMAL_ANNOTATION_EXPR = "com.github.javaparser.ast.expr.NormalAnnotationExpr";
    private static final String MARKER_ANNOTATION_EXPR = "com.github.javaparser.ast.expr.MarkerAnnotationExpr";
    private static final String SINGLE_MEMBER_ANNOTATION_EXPR = "com.github.javaparser.ast.expr.SingleMemberAnnotationExpr";

    @Override
    public boolean isEqual(AnnotationExpr first, AnnotationExpr second) {
        return first.getName().equals(second.getName());
    }

    @Override
    public AnnotationExpr doMerge(AnnotationExpr first, AnnotationExpr second) {
        if (NORMAL_ANNOTATION_EXPR.equals(first.getClass().getName())
                && NORMAL_ANNOTATION_EXPR.equals(second.getClass().getName())
        ) {
            AbstractNodeMerger<NormalAnnotationExpr> normalAnnotationExprMerger = AbstractNodeMerger.getMerger(NormalAnnotationExpr.class, isKeepFirstWhenConflict());
            return normalAnnotationExprMerger.merge((NormalAnnotationExpr) first, (NormalAnnotationExpr) second).get();
        }

        if (MARKER_ANNOTATION_EXPR.equals(first.getClass().getName())
                && MARKER_ANNOTATION_EXPR.equals(second.getClass().getName())
        ) {
            AbstractNodeMerger<MarkerAnnotationExpr> markerAnnotationExprMerger = AbstractNodeMerger.getMerger(MarkerAnnotationExpr.class, isKeepFirstWhenConflict());
            return markerAnnotationExprMerger.merge((MarkerAnnotationExpr) first, (MarkerAnnotationExpr) second).get();
        }

        if (SINGLE_MEMBER_ANNOTATION_EXPR.equals(first.getClass().getName())
                && SINGLE_MEMBER_ANNOTATION_EXPR.equals(second.getClass().getName())
        ) {
            AbstractNodeMerger<SingleMemberAnnotationExpr> singleMemberAnnotationExprMerger = AbstractNodeMerger.getMerger(SingleMemberAnnotationExpr.class, isKeepFirstWhenConflict());
            return singleMemberAnnotationExprMerger.merge((SingleMemberAnnotationExpr) first, (SingleMemberAnnotationExpr) second).get();
        }

        return super.doMerge(first, second);
    }
}
