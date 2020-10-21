package com.xstudio;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
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
        CompilationUnit mergeResult = JavaMerger.merge(aResult.get(), bResult.get(), false);
        String s = mergeResult.toString();
        logger.info("merge result: \n{}", s);
    }

    @Test
    public void parseInterface() throws FileNotFoundException {
        URL resource = ParseTest.class.getResource("");
        String path = resource.getPath();
        String sourcePathParent = path.substring(0, path.indexOf("/build"));
        File afile = new File(sourcePathParent + "/src/test/java/com/xstudio/test/object/first/FirstInterface.java");
        File bfile = new File(sourcePathParent + "/src/compare/java/com/xstudio/test/object/first/FirstInterface.java");
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> a = javaParser.parse(afile);
        ParseResult<CompilationUnit> b = javaParser.parse(bfile);
        Optional<CompilationUnit> aResult = a.getResult();
        Optional<CompilationUnit> bResult = b.getResult();
        CompilationUnit mergeResult = JavaMerger.merge(aResult.get(), bResult.get(), false);
        String s = mergeResult.toString();
        logger.info("merge result: \n{}", s);
    }

    @Test
    public void testAst() throws ParseException {
        String source = "/**                                                                         \n" +
                " * Copyright 2015-2015 the original author or authors.                         \n" +
                " *                                                                           \n" +
                " *       HaHa,I have the right to do anything!                               \n" +
                " */\n" +
                "package com.freetmp.xmbg.postgresql.mapper;\n" +
                "\n" +
                "import com.freetmp.xmbg.postgresql.entity.Admin;\n" +
                "import com.freetmp.xmbg.postgresql.entity.AdminExample;\n" +
                "import java.util.List;\n" +
                "import org.apache.ibatis.annotations.Param;\n" +
                "\n" +
                "public interface AdminMapper {\n" +
                "    int countByExample(AdminExample example);\n" +
                "\n" +
                "    int deleteByExample(AdminExample example);\n" +
                "\n" +
                "    int deleteByPrimaryKey(Long id);\n" +
                "\n" +
                "    int insert(Admin record);\n" +
                "\n" +
                "    int insertSelective(Admin record);\n" +
                "\n" +
                "    List<Admin> selectByExample(AdminExample example);\n" +
                "\n" +
                "    Admin selectByPrimaryKey(Long id);\n" +
                "\n" +
                "    int updateByExampleSelective(@Param(\"record\") Admin record, @Param(\"example\") AdminExample example);\n" +
                "\n" +
                "    int updateByExample(@Param(\"record\") Admin record, @Param(\"example\") AdminExample example);\n" +
                "\n" +
                "    int updateByPrimaryKeySelective(Admin record);\n" +
                "\n" +
                "    int updateByPrimaryKey(Admin record);\n" +
                "\n" +
                "    int batchInsert(List<Admin> list);\n" +
                "\n" +
                "    int batchUpdate(List<Admin> list);\n" +
                "\n" +
                "    int upsert(@Param(\"record\") Admin record, @Param(\"array\") String[] array);\n" +
                "\n" +
                "    int batchUpsert(@Param(\"records\") List<Admin> list, @Param(\"array\") String[] array);\n" +
                "}";

        String source2 =
                "package com.freetmp.xmbg.postgresql.mapper;\n" +
                        "\n" +
                        "import com.freetmp.xmbg.postgresql.entity.Admin;\n" +
                        "import com.freetmp.xmbg.postgresql.entity.AdminExample;\n" +
                        "import java.util.List;\n" +
                        "import org.apache.ibatis.annotations.Param;\n" +
                        "\n" +
                        "public interface AdminMapper {\n" +
                        "    int countByExample(AdminExample example);\n" +
                        "\n" +
                        "    int deleteByExample(AdminExample example);\n" +
                        "\n" +
                        "    int deleteByPrimaryKey(Long id);\n" +
                        "\n" +
                        "    int insert(Admin record);\n" +
                        "\n" +
                        "    int insertSelective(Admin record);\n" +
                        "\n" +
                        "    List<Admin> selectByExample(AdminExample example);\n" +
                        "\n" +
                        "    Admin selectByPrimaryKey(Long id);\n" +
                        "\n" +
                        "    int updateByExampleSelective(@Param(\"record\") Admin record, @Param(\"example\") AdminExample example);\n" +
                        "\n" +
                        "    int updateByExample(@Param(\"record\") Admin record, @Param(\"example\") AdminExample example);\n" +
                        "\n" +
                        "    int updateByPrimaryKeySelective(Admin record);\n" +
                        "\n" +
                        "    int updateByPrimaryKey(Admin record);\n" +
                        "\n" +
                        "    int batchInsert(List<Admin> list);\n" +
                        "\n" +
                        "    int batchUpdate(List<Admin> list);\n" +
                        "\n" +
                        "    int upsert(@Param(\"record\") Admin record, @Param(\"array\") String[] array);\n" +
                        "\n" +
                        "    int batchUpsert(@Param(\"records\") List<Admin> list, @Param(\"array\") String[] array);\n" +
                        "}" +
                        "\n" +
                        "interface Serializable {\n" +
                        "}\n";

        JavaParser javaParser = new JavaParser();

        ParseResult<CompilationUnit> unit1 = javaParser.parse(source);
        ParseResult<CompilationUnit> unit2 = javaParser.parse(source2);

        CompilationUnit merge = JavaMerger.merge(unit1.getResult().get(), unit2.getResult().get(), true);
        System.out.printf(merge.toString());
    }
}
