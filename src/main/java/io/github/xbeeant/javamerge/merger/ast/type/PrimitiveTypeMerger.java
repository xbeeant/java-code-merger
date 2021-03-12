package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.type.PrimitiveType;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 原始类型合并
 *
 * @author huangxiaobiao
 * @date 2020/10/21
 */
public class PrimitiveTypeMerger extends AbstractNodeMerger<PrimitiveType> {
    @Override
    public PrimitiveType doMerge(PrimitiveType first, PrimitiveType second) {
        return super.doMerge(first, second);
    }

    @Override
    public boolean isEqual(PrimitiveType first, PrimitiveType second) {
        return first.getType().equals(second.getType());
    }
}
