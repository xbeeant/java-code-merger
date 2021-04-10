package io.github.xbeeant;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import io.github.xbeeant.javamerge.merger.JavaMerger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xiaobiao
 * @date 2021/3/22
 */
class IfElseTest {
    JavaParser javaParser = new JavaParser();

    @Test
    void testIfElseIfElseAllEqual() {
        String s1 = "/**\n" +
                " * @author xiaobiao\n" +
                " * @date 2021/3/22\n" +
                " */\n" +
                "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        } else if (a == 2) {\n" +
                "            a = 22;\n" +
                "        } else {\n" +
                "            a = 33;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        ParseResult<CompilationUnit> parse1 = javaParser.parse(s1);

        ParseResult<CompilationUnit> parse2 = javaParser.parse(s1);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = "/**\n" +
                " * @author xiaobiao\n" +
                " * @date 2021/3/22\n" +
                " */\n" +
                "public class A {\n" +
                "\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        } else if (a == 2) {\n" +
                "            a = 22;\n" +
                "        } else {\n" +
                "            a = 33;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testIfConditionNotEqual() {
        String s1 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse1 = javaParser.parse(s1);

        String s2 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a != 1) {\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse2 = javaParser.parse(s2);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = "public class A {\n" +
                "\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testIfBlockNotEqual() {
        String s1 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse1 = javaParser.parse(s1);

        String s2 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse2 = javaParser.parse(s2);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = "public class A {\n" +
                "\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testElseConditionNotEqual() {
        String s1 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            \n" +
                "        } else if(a == 2) {}\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse1 = javaParser.parse(s1);

        String s2 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            \n" +
                "        } else if(a == 3) {}\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse2 = javaParser.parse(s2);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = "public class A {\n" +
                "\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "        } else if (a == 2) {\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Assertions.assertEquals(expected, result);
    }


    @Test
    void testMissingElseCondition() {
        String s1 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        } else { a = 12; }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse1 = javaParser.parse(s1);

        String s2 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse2 = javaParser.parse(s2);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = "public class A {\n" +
                "\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        } else {\n" +
                "            a = 12;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testMoreElseCondition() {
        String s1 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse1 = javaParser.parse(s1);

        String s2 = "public class A {\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            \n" +
                "        } else { a = 12; }\n" +
                "    }\n" +
                "}";
        ParseResult<CompilationUnit> parse2 = javaParser.parse(s2);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = "public class A {\n" +
                "\n" +
                "    public void a(Integer a) {\n" +
                "        if (a == 1) {\n" +
                "            a = 11;\n" +
                "        } else {\n" +
                "            a = 12;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Assertions.assertEquals(expected, result);
    }
}
