package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 正常的注释expr合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class NormalAnnotationExprMerger extends AbstractNodeMerger<NormalAnnotationExpr> {
    @Override
    public boolean isEqual(NormalAnnotationExpr first, NormalAnnotationExpr second) {
        return first.toString().equals(second.toString());
    }

    @Override
    public NormalAnnotationExpr doMerge(NormalAnnotationExpr first, NormalAnnotationExpr second) {
        NormalAnnotationExpr nae = new NormalAnnotationExpr();
        nae.setName(first.getName());
        AbstractNodeMerger<MemberValuePair> merger = AbstractNodeMerger.getMerger(MemberValuePair.class, isKeepFirstWhenConflict());
        NodeList<MemberValuePair> memberValuePairs = merger.mergeCollection(first.getPairs(), second.getPairs());
        nae.setPairs(memberValuePairs);
        return nae;
    }
}
