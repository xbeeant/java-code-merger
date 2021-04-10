package io.github.xbeeant;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import io.github.xbeeant.javamerge.merger.JavaMerger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xiaobiao
 * @version 2021/4/10
 */
class ExpressionMergerTest {
    private String a = "package io.github.xbeeant.easy.core.service.impl;\n" +
            "\n" +
            "import io.github.xbeeant.easy.config.AbstractSecurityMybatisPageHelperServiceImpl;\n" +
            "import io.github.xbeeant.core.IdWorker;\n" +
            "import io.github.xbeeant.easy.core.mapper.SecurityKeyMapper;\n" +
            "import io.github.xbeeant.easy.core.model.SecurityKey;\n" +
            "import io.github.xbeeant.easy.core.service.ISecurityKeyService;\n" +
            "import io.github.xbeeant.spring.mybatis.pagehelper.IMybatisPageHelperDao;\n" +
            "import org.springframework.beans.factory.annotation.Autowired;\n" +
            "import org.springframework.stereotype.Service;\n" +
            "\n" +
            "/**\n" +
            " * \n" +
            " * 密钥管理\n" +
            " */\n" +
            "@Service\n" +
            "public class SecurityKeyServiceImpl extends AbstractSecurityMybatisPageHelperServiceImpl<SecurityKey, Long> implements ISecurityKeyService {\n" +
            "    @Autowired\n" +
            "    private SecurityKeyMapper securityKeyMapper;\n" +
            "\n" +
            "    @Override\n" +
            "    public IMybatisPageHelperDao<SecurityKey, Long> getRepositoryDao() {\n" +
            "        return this.securityKeyMapper;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void setDefaults(SecurityKey record) {\n" +
            "        if (record.getId() == null) {\n" +
            "            record.setId(IdWorker.getId());\n" +
            "        }\n" +
            "    }\n" +
            "}";

    JavaParser javaParser = new JavaParser();

    @Test
    void test() {
        ParseResult<CompilationUnit> parse1 = javaParser.parse(a);

        ParseResult<CompilationUnit> parse2 = javaParser.parse(a);

        CompilationUnit merge = JavaMerger.merge(parse1.getResult().get(), parse2.getResult().get(), true);
        String result = merge.toString();

        String expected = a;
        Assertions.assertEquals(expected, result);
    }

}
