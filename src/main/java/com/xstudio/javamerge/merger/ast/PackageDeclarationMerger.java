package com.xstudio.javamerge.merger.ast;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.xstudio.javamerge.AbstractNodeMerger;

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
        if (!nameMerger.isEqual(first.getName(), second.getName())) {
            return false;
        }

        return false;
    }

    @Override
    public PackageDeclaration doMerge(PackageDeclaration first, PackageDeclaration second) {
        AbstractNodeMerger<Name> nameMerger = AbstractNodeMerger.getMerger(Name.class, isKeepFirstWhenConflict());

        PackageDeclaration packageDeclaration = new PackageDeclaration();
        packageDeclaration.setName(nameMerger.merge(first.getName(), second.getName()).get());
        return packageDeclaration;
    }
}
