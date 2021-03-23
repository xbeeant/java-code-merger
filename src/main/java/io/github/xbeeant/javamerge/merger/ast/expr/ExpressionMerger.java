package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.Expression;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 表达式合并
 *
 * @author huangxiaobiao
 * @date 2020/10/20
 */
public class ExpressionMerger extends AbstractNodeMerger<Expression> {
    @Override
    public boolean isEqual(Expression first, Expression second) {
        if (first.getClass().equals(second.getClass())) {
            AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getClass(), isKeepFirstWhenConflict());
            return merger.isEqual(first, second);
        }

        return false;
    }

    @Override
    public Expression doMerge(Expression first, Expression second) {
        return super.doMerge(first, second);
    }
}
