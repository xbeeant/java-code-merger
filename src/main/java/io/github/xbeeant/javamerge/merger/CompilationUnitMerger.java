package io.github.xbeeant.javamerge.merger;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import io.github.xbeeant.javamerge.AbstractNodeMerger;
import io.github.xbeeant.javamerge.AbstractTypeMerger;

import java.util.Optional;

/**
 * 编译单元合并
 *
 * @author huangrongbiao
 * @date 2020/10/15
 */
public class CompilationUnitMerger {
    private boolean keepFirstWhenConflict;

    public CompilationUnit merge(CompilationUnit first, CompilationUnit second, boolean keepFirstWhenConflict) {
        this.keepFirstWhenConflict = keepFirstWhenConflict;
        if (null == first) {
            return second;
        }

        if (null == second) {
            return first;
        }

        CompilationUnit cu = new CompilationUnit();

        AbstractNodeMerger<PackageDeclaration> packageDeclarationMerger = AbstractNodeMerger.getMerger(PackageDeclaration.class, keepFirstWhenConflict);
        cu.setPackageDeclaration(packageDeclarationMerger.merge(first.getPackageDeclaration(), second.getPackageDeclaration()).get());

        AbstractNodeMerger<ImportDeclaration> importDeclarationMerger = AbstractNodeMerger.getMerger(ImportDeclaration.class, keepFirstWhenConflict);
        cu.setImports(importDeclarationMerger.mergeCollection(first.getImports(), second.getImports()));


        cu.setTypes(mergeTypes(first.getTypes(), second.getTypes()));

        return cu;
    }


    public NodeList<TypeDeclaration<?>> mergeTypes(NodeList<TypeDeclaration<?>> first, NodeList<TypeDeclaration<?>> second) {
        NodeList<TypeDeclaration<?>> nodeList = new NodeList<>();
        if (first.isEmpty()) {
            return second;
        }

        if (second.isEmpty()) {
            return first;
        }

        NodeList<TypeDeclaration<?>> secondCopies = new NodeList<>(second);
        TypeDeclaration<?> found = null;
        for (TypeDeclaration<?> firstNode : first) {
            AbstractTypeMerger merger = AbstractTypeMerger.getMerger(firstNode.getClass(), keepFirstWhenConflict);
            for (TypeDeclaration<?> secondNode : secondCopies) {
                if (merger.isEqual(firstNode, secondNode)) {
                    found = secondNode;
                    break;
                }
            }

            if (null != found) {
                Optional<TypeDeclaration<?>> mergeResult = Optional.of(merger.doMerge(firstNode, found));
                nodeList.add(mergeResult.get());
                secondCopies.remove(found);
            } else {
                nodeList.add(firstNode);
            }
        }


        if (secondCopies.isNonEmpty()) {
            nodeList.addAll(secondCopies);
        }

        return nodeList;
    }
}
