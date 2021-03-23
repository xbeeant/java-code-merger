package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.BinaryExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author xiaobiao
 * @date 2021/3/23
 */
public class BinaryExprMerger extends AbstractNodeMerger<BinaryExpr> {
    @Override
    public boolean isEqual(BinaryExpr first, BinaryExpr second) {
        boolean isLeftEqual = first.getLeft().equals(second.getLeft());
        boolean isRightEqual = first.getRight().equals(second.getRight());
        boolean isOperatorEqual = first.getOperator().equals(second.getOperator());
        int weight = 0;
        if (isLeftEqual) {
            weight += 1;
        }

        if (isRightEqual) {
            weight += 1;
        }

        if (isOperatorEqual) {
            weight += 1;
        }

        return weight >= 2;
    }
}
