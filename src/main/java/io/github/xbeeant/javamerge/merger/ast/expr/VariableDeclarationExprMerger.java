package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 变量声明expr合并
 *
 * @author huangxiaobiao
 * @date 2020/10/20
 */
public class VariableDeclarationExprMerger extends AbstractNodeMerger<VariableDeclarationExpr> {
    @Override
    public boolean isEqual(VariableDeclarationExpr first, VariableDeclarationExpr second) {
        return first.toString().equals(second.toString());
    }

    @Override
    public VariableDeclarationExpr doMerge(VariableDeclarationExpr first, VariableDeclarationExpr second) {
        return super.doMerge(first, second);
    }
}
