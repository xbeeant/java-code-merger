package com.xstudio.javamerge.merger.ast.type;

import com.github.javaparser.ast.type.Type;
import com.xstudio.javamerge.AbstractNodeMerger;

/**
 * @author huangxiaobiao
 */
public class TypeMerger extends AbstractNodeMerger<Type> {
    @Override
    public boolean isEqual(Type first, Type second) {
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getClass(), isKeepFirstWhenConflict());
        return merger.isEqual(first, second);
    }

    @Override
    public Type doMerge(Type first, Type second) {
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getClass(), isKeepFirstWhenConflict());
        return (Type) merger.doMerge(first, second);
    }
}
