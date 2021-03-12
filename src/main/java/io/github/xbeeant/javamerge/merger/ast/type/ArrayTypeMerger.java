package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.type.ArrayType;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 数组类型合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class ArrayTypeMerger extends AbstractNodeMerger<ArrayType> {
    @Override
    public boolean isEqual(ArrayType first, ArrayType second) {
        return first.isArrayType() && second.isArrayType();
    }

    @Override
    public ArrayType doMerge(ArrayType first, ArrayType second) {
        // todo
        return super.doMerge(first, second);
    }
}
