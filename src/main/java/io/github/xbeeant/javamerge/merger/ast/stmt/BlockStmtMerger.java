package io.github.xbeeant.javamerge.merger.ast.stmt;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 合并块支撑
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class BlockStmtMerger extends AbstractNodeMerger<BlockStmt> {
    @Override
    public boolean isEqual(BlockStmt first, BlockStmt second) {
        AbstractNodeMerger<Statement> statementMerger = AbstractNodeMerger.getMerger(Statement.class, isKeepFirstWhenConflict()) ;
        return statementMerger.isEqual(first.getStatements(), second.getStatements());
    }

    @Override
    public BlockStmt doMerge(BlockStmt first, BlockStmt second) {
        BlockStmt blockStmt = new BlockStmt();
        NodeList<Statement> firstStatements = first.getStatements();
        NodeList<Statement> secondStatements = second.getStatements();

        AbstractNodeMerger<Statement> statementMerger = AbstractNodeMerger.getMerger(Statement.class, isKeepFirstWhenConflict()) ;

        NodeList<Statement> statements = statementMerger.mergeCollection(firstStatements, secondStatements);
        blockStmt.setStatements(statements);

        return blockStmt;
    }
}
