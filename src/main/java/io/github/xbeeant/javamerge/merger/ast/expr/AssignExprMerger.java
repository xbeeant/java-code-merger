package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.AssignExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author xiaobiao
 * @date 2021/3/23
 */
public class AssignExprMerger extends AbstractNodeMerger<AssignExpr> {
    @Override
    public boolean isEqual(AssignExpr first, AssignExpr second) {
        return first.toString().equals(second.toString());
    }
}
