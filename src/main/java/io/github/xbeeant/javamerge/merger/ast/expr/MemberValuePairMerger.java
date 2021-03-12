package io.github.xbeeant.javamerge.merger.ast.expr;

import com.github.javaparser.ast.expr.MemberValuePair;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

/**
 * 成员值对合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class MemberValuePairMerger extends AbstractNodeMerger<MemberValuePair> {
    @Override
    public MemberValuePair doMerge(MemberValuePair first, MemberValuePair second) {
        MemberValuePair mvp = new MemberValuePair();
        mvp.setName(first.getName());
        mvp.setValue(isKeepFirstWhenConflict() ? first.getValue() : second.getValue());
        return super.doMerge(first, second);
    }

    @Override
    public boolean isEqual(MemberValuePair first, MemberValuePair second) {
        return first.getName().equals(second.getName());
    }
}
