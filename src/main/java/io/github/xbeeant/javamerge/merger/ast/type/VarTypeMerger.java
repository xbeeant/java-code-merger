package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.type.VarType;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * var类型合并
 *
 * @author huangxiaobiao
 * @date 2020/10/19
 */
public class VarTypeMerger extends AbstractNodeMerger<VarType> {
    @Override
    public boolean isEqual(VarType first, VarType second) {
        return first.isVarType() && second.isVarType();
    }

    @Override
    public VarType doMerge(VarType first, VarType second) {
        // todo
        return super.doMerge(first, second);
    }
}
