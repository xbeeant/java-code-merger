package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author xiaobiao
 * @version 2021/3/21
 */
public class IfStmtMerger extends AbstractNodeMerger<IfStmt> {

    @Override
    public boolean isEqual(IfStmt first, IfStmt second) {
        AbstractNodeMerger conditionMerger = AbstractNodeMerger.getMerger(first.getCondition().getClass(), isKeepFirstWhenConflict());
        boolean isConditionEqual = conditionMerger.isEqual(first.getCondition(), second.getCondition());

        AbstractNodeMerger<Statement> statementMerger  =  AbstractNodeMerger.getMerger(Statement.class, isKeepFirstWhenConflict());
        boolean isElseEqual = statementMerger.isEqual(first.getElseStmt(), second.getElseStmt());

        boolean isThenEqual = statementMerger.isEqual(first.getThenStmt(), second.getThenStmt());
        return isConditionEqual;
    }

    @Override
    public IfStmt doMerge(IfStmt first, IfStmt second) {
        IfStmt is = new IfStmt();
        AbstractNodeMerger<Expression> nodeMerger =  AbstractNodeMerger.getMerger(Expression.class, isKeepFirstWhenConflict());
        is.setCondition(nodeMerger.doMerge(first.getCondition(), second.getCondition()));

        AbstractNodeMerger<Statement> statementMerger  =  AbstractNodeMerger.getMerger(Statement.class, isKeepFirstWhenConflict());
        is.setElseStmt(statementMerger.doMerge(first.getElseStmt(), second.getElseStmt()));
        is.setThenStmt(statementMerger.doMerge(first.getThenStmt(), second.getThenStmt()));

        return is;
    }
}
