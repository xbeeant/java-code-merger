package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.MethodCallExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author xiaobiao
 * @version 2021/4/10
 */
public class MethodCallExprMerger extends AbstractNodeMerger<MethodCallExpr> {
    @Override
    public boolean isEqual(MethodCallExpr first, MethodCallExpr second) {
        return first.getName().equals(second.getName());
    }


}
