package com.xstudio.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.SimpleName;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 简单的名称合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class SimpleNameMerger extends AbstractNodeMerger<SimpleName> {
    @Override
    public boolean isEqual(SimpleName first, SimpleName second) {
        return first.equals(second);
    }
}
