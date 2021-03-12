package io.github.xbeeant.javamerge.merger;

import com.github.javaparser.ast.CompilationUnit;

/**
 * java合并
 *
 * @author huangrongbiao
 * @date 2020/10/15
 */
public class JavaMerger {
    public static CompilationUnit merge(CompilationUnit first, CompilationUnit second, boolean keepFirstWhenConflict) {
        CompilationUnitMerger merger = new CompilationUnitMerger();
        CompilationUnit result = merger.merge(first, second, keepFirstWhenConflict);
        
        return result;
    }
}