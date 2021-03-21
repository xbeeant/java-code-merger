package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.type.Type;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * @author huangxiaobiao
 */
public class TypeMerger extends AbstractNodeMerger<Type> {
    @Override
    public boolean isEqual(Type first, Type second) {
        if (!first.getClass().equals(second.getClass())) {
            return false;
        }
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getClass(), isKeepFirstWhenConflict());
        return merger.isEqual(first, second);
    }

    @Override
    public Type doMerge(Type first, Type second) {
        if (!first.getClass().equals(second.getClass())) {
            if (isKeepFirstWhenConflict()) {
                return first;
            } else {
                return second;
            }
        }
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.getClass(), isKeepFirstWhenConflict());
        return (Type) merger.doMerge(first, second);
    }
}
