package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.type.VoidType;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * Void类型合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class VoidTypeMerger extends AbstractNodeMerger<VoidType> {
    @Override
    public boolean isEqual(VoidType first, VoidType second) {
        return first.isVoidType() && second.isVoidType();
    }

    @Override
    public VoidType doMerge(VoidType first, VoidType second) {
        return isKeepFirstWhenConflict() ? first : second;
    }
}
