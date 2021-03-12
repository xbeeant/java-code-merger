package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.Name;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 名字合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class NameMerger extends AbstractNodeMerger<Name> {
    @Override
    public boolean isEqual(Name first, Name second) {
        return first.toString().equals(second.toString());
    }

    @Override
    public Name doMerge(Name first, Name second) {
        // todo
        return first;
    }
}
