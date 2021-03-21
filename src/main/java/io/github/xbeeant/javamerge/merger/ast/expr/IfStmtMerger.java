package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.stmt.IfStmt;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author xiaobiao
 * @version 2021/3/21
 */
public class IfStmtMerger extends AbstractNodeMerger<IfStmt> {
    @Override
    public boolean isEqual(IfStmt first, IfStmt second) {
        return first.getCondition().equals(second.getCondition());
    }

    @Override
    public IfStmt doMerge(IfStmt first, IfStmt second) {
        if (isKeepFirstWhenConflict()) {
            return first;
        }

        return second;
    }
}
