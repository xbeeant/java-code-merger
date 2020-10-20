package com.xstudio.javamerge.merger.ast;

import com.github.javaparser.ast.Modifier;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * 修改器合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class ModifierMerger extends AbstractNodeMerger<Modifier> {
    @Override
    public boolean isEqual(Modifier first, Modifier second) {
        return first.toString().equals(second.toString());
    }

    @Override
    public Modifier doMerge(Modifier first, Modifier second) {
        Modifier modifier = new Modifier();
        modifier.setKeyword(isKeepFirstWhenConflict() ? first.getKeyword() : second.getKeyword());
        return modifier;
    }
}
