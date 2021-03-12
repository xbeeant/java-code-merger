package io.github.xbeeant.javamerge.merger.ast.type;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

import java.util.Optional;

/**
 * 类或接口类型合并
 *
 * @author huangxiaobiao
 * @date 2020/10/18
 */
public class ClassOrInterfaceTypeMerger extends AbstractNodeMerger<ClassOrInterfaceType> {
    @Override
    public boolean isEqual(ClassOrInterfaceType first, ClassOrInterfaceType second) {
        return first.getName().equals(second.getName());
    }

    @Override
    public ClassOrInterfaceType doMerge(ClassOrInterfaceType first, ClassOrInterfaceType second) {
        ClassOrInterfaceType coit = new ClassOrInterfaceType();
        coit.setName(first.getName());
        AbstractNodeMerger<Type> typeMerger = AbstractNodeMerger.getMerger(Type.class, isKeepFirstWhenConflict());
        Optional<NodeList<Type>> typeArguments = typeMerger.mergeCollection(first.getTypeArguments(), second.getTypeArguments());
        if (typeArguments.isPresent()) {
            coit.setTypeArguments(typeArguments.get());
        }
        return coit;
    }
}
