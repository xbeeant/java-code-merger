package com.xstudio.javamerge.merger.ast.stmt;

import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 表达式合并
 *
 * @author huangxiaobiao
 * @date 2020/10/20
 */
public class ExpressionStmtMerger extends AbstractNodeMerger<ExpressionStmt> {
    @Override
    public boolean isEqual(ExpressionStmt first, ExpressionStmt second) {
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getExpression().getClass(), isKeepFirstWhenConflict());
        return merger.isEqual(first.getExpression(), second.getExpression());
    }

    @Override
    public ExpressionStmt doMerge(ExpressionStmt first, ExpressionStmt second) {
        return super.doMerge(first, second);
    }
}
