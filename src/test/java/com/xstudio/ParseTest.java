package com.xstudio;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.xstudio.javamerge.merger.JavaMerger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;

public class ParseTest {
    private static final Logger logger = LoggerFactory.getLogger(ParseTest.class);

    @Test
    public void parseObject() throws FileNotFoundException {
        // /Users/ca62785/Codes/xstudio-java-merge/src/test/java/com/xstudio/test/A.java
        URL resource = ParseTest.class.getResource("");
        String path = resource.getPath();
        String sourcePathParent = path.substring(0, path.indexOf("/build"));
        File afile = new File(sourcePathParent + "/src/test/java/com/xstudio/test/object/first/FirstObject.java");
        File bfile = new File(sourcePathParent + "/src/test/java/com/xstudio/test/object/second/SecondObject.java");
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> a = javaParser.parse(afile);
        ParseResult<CompilationUnit> b = javaParser.parse(bfile);
        Optional<CompilationUnit> aResult = a.getResult();
        Optional<CompilationUnit> bResult = b.getResult();
        CompilationUnit mergeResult = JavaMerger.merge(aResult.get(), bResult.get(), true);
        String s = mergeResult.toString();
        logger.info("merge result: \n{}", s);
    }

    @Test
    public void parseAnnotation() throws FileNotFoundException {
        // /Users/ca62785/Codes/xstudio-java-merge/src/test/java/com/xstudio/test/A.java
        URL resource = ParseTest.class.getResource("");
        String path = resource.getPath();
        String sourcePathParent = path.substring(0, path.indexOf("/build"));
        File afile = new File(sourcePathParent + "/src/test/java/com/xstudio/test/object/first/FirstObjectWithAnnotation.java");
        File bfile = new File(sourcePathParent + "/src/compare/java/com/xstudio/test/object/first/FirstObjectWithAnnotation.java");
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> a = javaParser.parse(afile);
        ParseResult<CompilationUnit> b = javaParser.parse(bfile);
        Optional<CompilationUnit> aResult = a.getResult();
        Optional<CompilationUnit> bResult = b.getResult();
        CompilationUnit mergeResult = JavaMerger.merge(aResult.get(), bResult.get(), true);
        String s = mergeResult.toString();
        logger.info("merge result: \n{}", s);
    }
}
