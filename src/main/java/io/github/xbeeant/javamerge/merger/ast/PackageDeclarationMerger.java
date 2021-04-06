package io.github.xbeeant.javamerge.merger.ast;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.Name;
import io.github.xbeeant.javamerge.AbstractNodeMerger;

import java.util.Optional;

/**
 * 包声明合并
 *
 * @author huangxiaobiao
 * @date 2020/10/15
 */
public class PackageDeclarationMerger extends AbstractNodeMerger<PackageDeclaration> {

    @Override
    public boolean isEqual(PackageDeclaration first, PackageDeclaration second) {
        AbstractNodeMerger<Name> nameMerger = AbstractNodeMerger.getMerger(Name.class, isKeepFirstWhenConflict());
        return nameMerger.isEqual(first.getName(), second.getName());
    }

    @Override
    public PackageDeclaration doMerge(PackageDeclaration first, PackageDeclaration second) {
        AbstractNodeMerger<Name> nameMerger = AbstractNodeMerger.getMerger(Name.class, isKeepFirstWhenConflict());

        PackageDeclaration packageDeclaration = new PackageDeclaration();
        Optional<Name> nameMergeResult = nameMerger.merge(first.getName(), second.getName());
        nameMergeResult.ifPresent(packageDeclaration::setName);

        return packageDeclaration;
    }
}
