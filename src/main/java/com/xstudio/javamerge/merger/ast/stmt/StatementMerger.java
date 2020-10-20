package com.xstudio.javamerge.merger.ast.stmt;

import com.github.javaparser.ast.stmt.Statement;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 语句合并
 *
 * @author huangxiaobiao
 * @date 2020/10/20
 */
public class StatementMerger extends AbstractNodeMerger<Statement> {
    @Override
    public boolean isEqual(Statement first, Statement second) {
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getClass(), isKeepFirstWhenConflict());
        return merger.isEqual(first, second);
    }

    @Override
    public Statement doMerge(Statement first, Statement second) {
        return super.doMerge(first, second);
    }
}
