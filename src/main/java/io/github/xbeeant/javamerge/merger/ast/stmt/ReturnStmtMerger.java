package io.github.xbeeant.javamerge.merger.ast.stmt;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ReturnStmt;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 合并返回支撑
 *
 * @author huangxiaobiao
 * @date 2020/10/20
 */
public class ReturnStmtMerger extends AbstractNodeMerger<ReturnStmt> {
    @Override
    public boolean isEqual(ReturnStmt first, ReturnStmt second) {
        return first.getExpression().equals(second.getExpression());
    }

    @Override
    public ReturnStmt doMerge(ReturnStmt first, ReturnStmt second) {
        ReturnStmt returnStmt = new ReturnStmt();
        AbstractNodeMerger<Expression> expressionMerger = AbstractNodeMerger.getMerger(Expression.class, isKeepFirstWhenConflict());
        Expression expression = expressionMerger.doMerge(first.getExpression(), second.getExpression());
        returnStmt.setExpression(expression);
        return returnStmt;
    }
}
