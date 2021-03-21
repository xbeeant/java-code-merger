package io.github.xbeeant;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import io.github.xbeeant.javamerge.merger.JavaMerger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;

class ParseTest {
    private static final Logger logger = LoggerFactory.getLogger(ParseTest.class);

    @Test
    void parseObject() throws FileNotFoundException {
        URL resource = ParseTest.class.getResource("");
        String path = resource.getPath();
        String sourcePathParent = path.substring(0, path.indexOf("/build"));
        File afile = new File(sourcePathParent + "/src/test/java/io/github/xbeeant/test/object/first/FirstObjectWithAnnotation.java");
        File bfile = new File(sourcePathParent + "/src/compare/java/io/github/xbeeant/test/object/first/FirstObjectWithAnnotation.java");
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
    void parseInterface() throws FileNotFoundException {
        URL resource = ParseTest.class.getResource("");
        String path = resource.getPath();
        String sourcePathParent = path.substring(0, path.indexOf("/build"));
        File afile = new File(sourcePathParent + "/src/test/java/io/github/xbeeant/test/object/first/FirstInterface.java");
        File bfile = new File(sourcePathParent + "/src/compare/java/io/github/xbeeant/test/object/first/FirstInterface.java");
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
    void testAst() throws ParseException {
        String source = "\n" +
                "package io.github.xbeeant.easy.core.model;\n" +
                "\n" +
                "import io.github.xbeeant.core.BaseModelObject;\n" +
                "import java.io.Serializable;\n" +
                "\n" +
                "/**\n" +
                " * user\n" +
                " */\n" +
                "public class User extends BaseModelObject<Long> implements Serializable {\n" +
                "\n" +
                "    /**\n" +
                "     * 用户ID\n" +
                "     */\n" +
                "    private Long id;\n" +
                "\n" +
                "    /**\n" +
                "     * 登录账号名\n" +
                "     */\n" +
                "    private String account;\n" +
                "\n" +
                "    /**\n" +
                "     * 头像\n" +
                "     */\n" +
                "    private String avatar;\n" +
                "\n" +
                "    /**\n" +
                "     * 国家ID\n" +
                "     */\n" +
                "    private Long countryId;\n" +
                "\n" +
                "    /**\n" +
                "     * 城市ID\n" +
                "     */\n" +
                "    private Long cityId;\n" +
                "\n" +
                "    /**\n" +
                "     * 省份ID\n" +
                "     */\n" +
                "    private Long provinceId;\n" +
                "\n" +
                "    /**\n" +
                "     * 区县ID\n" +
                "     */\n" +
                "    private Long districtId;\n" +
                "\n" +
                "    /**\n" +
                "     * 街道\n" +
                "     */\n" +
                "    private String street;\n" +
                "\n" +
                "    /**\n" +
                "     * 邮箱\n" +
                "     */\n" +
                "    private String email;\n" +
                "\n" +
                "    /**\n" +
                "     * 区域编码\n" +
                "     */\n" +
                "    private String prefix;\n" +
                "\n" +
                "    /**\n" +
                "     * 手机号\n" +
                "     */\n" +
                "    private String mobile;\n" +
                "\n" +
                "    /**\n" +
                "     * 是否禁用， 0 正常 1 已禁用\n" +
                "     */\n" +
                "    private Boolean invalid;\n" +
                "\n" +
                "    /**\n" +
                "     * 昵称\n" +
                "     */\n" +
                "    private String nickname;\n" +
                "\n" +
                "    /**\n" +
                "     * 密码\n" +
                "     */\n" +
                "    private String password;\n" +
                "\n" +
                "    /**\n" +
                "     * 个人简介\n" +
                "     */\n" +
                "    private String profile;\n" +
                "\n" +
                "    /**\n" +
                "     * 头衔\n" +
                "     */\n" +
                "    private String title;\n" +
                "\n" +
                "    /**\n" +
                "     * This field was generated by MyBatis Generator.\n" +
                "     * This field corresponds to the database table user\n" +
                "     *\n" +
                "     * @mbg.generated Sun Mar 21 17:13:23 CST 2021\n" +
                "     */\n" +
                "    private static final long serialVersionUID = 1L;\n" +
                "\n" +
                "    /**\n" +
                "     * get field 用户ID\n" +
                "     * @return id 用户ID\n" +
                "     */\n" +
                "    public Long getId() {\n" +
                "        return id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 用户ID\n" +
                "     * @param id the value for 用户ID\n" +
                "     */\n" +
                "    public void setId(Long id) {\n" +
                "        this.id = id;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 登录账号名\n" +
                "     * @return account 登录账号名\n" +
                "     */\n" +
                "    public String getAccount() {\n" +
                "        return account;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 登录账号名\n" +
                "     * @param account the value for 登录账号名\n" +
                "     */\n" +
                "    public void setAccount(String account) {\n" +
                "        this.account = account == null ? null : account.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 头像\n" +
                "     * @return avatar 头像\n" +
                "     */\n" +
                "    public String getAvatar() {\n" +
                "        return avatar;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 头像\n" +
                "     * @param avatar the value for 头像\n" +
                "     */\n" +
                "    public void setAvatar(String avatar) {\n" +
                "        this.avatar = avatar == null ? null : avatar.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 国家ID\n" +
                "     * @return countryId 国家ID\n" +
                "     */\n" +
                "    public Long getCountryId() {\n" +
                "        return countryId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 国家ID\n" +
                "     * @param countryId the value for 国家ID\n" +
                "     */\n" +
                "    public void setCountryId(Long countryId) {\n" +
                "        this.countryId = countryId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 城市ID\n" +
                "     * @return cityId 城市ID\n" +
                "     */\n" +
                "    public Long getCityId() {\n" +
                "        return cityId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 城市ID\n" +
                "     * @param cityId the value for 城市ID\n" +
                "     */\n" +
                "    public void setCityId(Long cityId) {\n" +
                "        this.cityId = cityId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 省份ID\n" +
                "     * @return provinceId 省份ID\n" +
                "     */\n" +
                "    public Long getProvinceId() {\n" +
                "        return provinceId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 省份ID\n" +
                "     * @param provinceId the value for 省份ID\n" +
                "     */\n" +
                "    public void setProvinceId(Long provinceId) {\n" +
                "        this.provinceId = provinceId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 区县ID\n" +
                "     * @return districtId 区县ID\n" +
                "     */\n" +
                "    public Long getDistrictId() {\n" +
                "        return districtId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 区县ID\n" +
                "     * @param districtId the value for 区县ID\n" +
                "     */\n" +
                "    public void setDistrictId(Long districtId) {\n" +
                "        this.districtId = districtId;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 街道\n" +
                "     * @return street 街道\n" +
                "     */\n" +
                "    public String getStreet() {\n" +
                "        return street;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 街道\n" +
                "     * @param street the value for 街道\n" +
                "     */\n" +
                "    public void setStreet(String street) {\n" +
                "        this.street = street == null ? null : street.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 邮箱\n" +
                "     * @return email 邮箱\n" +
                "     */\n" +
                "    public String getEmail() {\n" +
                "        return email;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 邮箱\n" +
                "     * @param email the value for 邮箱\n" +
                "     */\n" +
                "    public void setEmail(String email) {\n" +
                "        this.email = email == null ? null : email.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 区域编码\n" +
                "     * @return prefix 区域编码\n" +
                "     */\n" +
                "    public String getPrefix() {\n" +
                "        return prefix;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 区域编码\n" +
                "     * @param prefix the value for 区域编码\n" +
                "     */\n" +
                "    public void setPrefix(String prefix) {\n" +
                "        this.prefix = prefix == null ? null : prefix.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 手机号\n" +
                "     * @return mobile 手机号\n" +
                "     */\n" +
                "    public String getMobile() {\n" +
                "        return mobile;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 手机号\n" +
                "     * @param mobile the value for 手机号\n" +
                "     */\n" +
                "    public void setMobile(String mobile) {\n" +
                "        this.mobile = mobile == null ? null : mobile.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 是否禁用， 0 正常 1 已禁用\n" +
                "     * @return invalid 是否禁用， 0 正常 1 已禁用\n" +
                "     */\n" +
                "    public Boolean getInvalid() {\n" +
                "        return invalid;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 是否禁用， 0 正常 1 已禁用\n" +
                "     * @param invalid the value for 是否禁用， 0 正常 1 已禁用\n" +
                "     */\n" +
                "    public void setInvalid(Boolean invalid) {\n" +
                "        this.invalid = invalid;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 昵称\n" +
                "     * @return nickname 昵称\n" +
                "     */\n" +
                "    public String getNickname() {\n" +
                "        return nickname;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 昵称\n" +
                "     * @param nickname the value for 昵称\n" +
                "     */\n" +
                "    public void setNickname(String nickname) {\n" +
                "        this.nickname = nickname == null ? null : nickname.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 密码\n" +
                "     * @return password 密码\n" +
                "     */\n" +
                "    public String getPassword() {\n" +
                "        return password;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 密码\n" +
                "     * @param password the value for 密码\n" +
                "     */\n" +
                "    public void setPassword(String password) {\n" +
                "        this.password = password == null ? null : password.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 个人简介\n" +
                "     * @return profile 个人简介\n" +
                "     */\n" +
                "    public String getProfile() {\n" +
                "        return profile;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 个人简介\n" +
                "     * @param profile the value for 个人简介\n" +
                "     */\n" +
                "    public void setProfile(String profile) {\n" +
                "        this.profile = profile == null ? null : profile.trim();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * get field 头衔\n" +
                "     * @return title 头衔\n" +
                "     */\n" +
                "    public String getTitle() {\n" +
                "        return title;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * set filed 头衔\n" +
                "     * @param title the value for 头衔\n" +
                "     */\n" +
                "    public void setTitle(String title) {\n" +
                "        this.title = title == null ? null : title.trim();\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Long valueOfKey() {\n" +
                "        return id;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void assignKeyValue(Long value) {\n" +
                "        this.id = value;\n" +
                "    }\n" +
                "}";

        String source2 =
                "package io.github.xbeeant.easy.core.model;\n" +
                        "\n" +
                        "import io.github.xbeeant.core.BaseModelObject;\n" +
                        "import java.io.Serializable;\n" +
                        "import io.github.xbeeant.core.BaseModelObject;\n" +
                        "import java.io.Serializable;\n" +
                        "\n" +
                        "/**\n" +
                        " * user\n" +
                        " */\n" +
                        "public class User extends BaseModelObject<Long> implements Serializable {\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 用户ID\n" +
                        "     */\n" +
                        "    private Long id;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 登录账号名\n" +
                        "     */\n" +
                        "    private String account;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 头像\n" +
                        "     */\n" +
                        "    private String avatar;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 国家ID\n" +
                        "     */\n" +
                        "    private Long countryId;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 城市ID\n" +
                        "     */\n" +
                        "    private Long cityId;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 省份ID\n" +
                        "     */\n" +
                        "    private Long provinceId;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 区县ID\n" +
                        "     */\n" +
                        "    private Long districtId;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 街道\n" +
                        "     */\n" +
                        "    private String street;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 邮箱\n" +
                        "     */\n" +
                        "    private String email;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 区域编码\n" +
                        "     */\n" +
                        "    private String prefix;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 手机号\n" +
                        "     */\n" +
                        "    private String mobile;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 是否禁用， 0 正常 1 已禁用\n" +
                        "     */\n" +
                        "    private byte[] invalid;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 昵称\n" +
                        "     */\n" +
                        "    private String nickname;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 密码\n" +
                        "     */\n" +
                        "    private String password;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 个人简介\n" +
                        "     */\n" +
                        "    private String profile;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * 头衔\n" +
                        "     */\n" +
                        "    private String title;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * This field was generated by MyBatis Generator.\n" +
                        "     * This field corresponds to the database table user\n" +
                        "     *\n" +
                        "     * @mbg.generated Thu Mar 18 23:05:51 CST 2021\n" +
                        "     */\n" +
                        "    private static final long serialVersionUID = 1L;\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 用户ID\n" +
                        "     * @return id 用户ID\n" +
                        "     */\n" +
                        "    public Long getId() {\n" +
                        "        return id;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 用户ID\n" +
                        "     * @param id the value for 用户ID\n" +
                        "     */\n" +
                        "    public void setId(Long id) {\n" +
                        "        this.id = id;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 登录账号名\n" +
                        "     * @return account 登录账号名\n" +
                        "     */\n" +
                        "    public String getAccount() {\n" +
                        "        return account;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 登录账号名\n" +
                        "     * @param account the value for 登录账号名\n" +
                        "     */\n" +
                        "    public void setAccount(String account) {\n" +
                        "        this.account = account == null ? null : account.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 头像\n" +
                        "     * @return avatar 头像\n" +
                        "     */\n" +
                        "    public String getAvatar() {\n" +
                        "        return avatar;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 头像\n" +
                        "     * @param avatar the value for 头像\n" +
                        "     */\n" +
                        "    public void setAvatar(String avatar) {\n" +
                        "        this.avatar = avatar == null ? null : avatar.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 国家ID\n" +
                        "     * @return countryId 国家ID\n" +
                        "     */\n" +
                        "    public Long getCountryId() {\n" +
                        "        return countryId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 国家ID\n" +
                        "     * @param countryId the value for 国家ID\n" +
                        "     */\n" +
                        "    public void setCountryId(Long countryId) {\n" +
                        "        this.countryId = countryId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 城市ID\n" +
                        "     * @return cityId 城市ID\n" +
                        "     */\n" +
                        "    public Long getCityId() {\n" +
                        "        return cityId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 城市ID\n" +
                        "     * @param cityId the value for 城市ID\n" +
                        "     */\n" +
                        "    public void setCityId(Long cityId) {\n" +
                        "        this.cityId = cityId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 省份ID\n" +
                        "     * @return provinceId 省份ID\n" +
                        "     */\n" +
                        "    public Long getProvinceId() {\n" +
                        "        return provinceId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 省份ID\n" +
                        "     * @param provinceId the value for 省份ID\n" +
                        "     */\n" +
                        "    public void setProvinceId(Long provinceId) {\n" +
                        "        this.provinceId = provinceId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 区县ID\n" +
                        "     * @return districtId 区县ID\n" +
                        "     */\n" +
                        "    public Long getDistrictId() {\n" +
                        "        return districtId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 区县ID\n" +
                        "     * @param districtId the value for 区县ID\n" +
                        "     */\n" +
                        "    public void setDistrictId(Long districtId) {\n" +
                        "        this.districtId = districtId;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 街道\n" +
                        "     * @return street 街道\n" +
                        "     */\n" +
                        "    public String getStreet() {\n" +
                        "        return street;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 街道\n" +
                        "     * @param street the value for 街道\n" +
                        "     */\n" +
                        "    public void setStreet(String street) {\n" +
                        "        this.street = street == null ? null : street.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 邮箱\n" +
                        "     * @return email 邮箱\n" +
                        "     */\n" +
                        "    public String getEmail() {\n" +
                        "        return email;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 邮箱\n" +
                        "     * @param email the value for 邮箱\n" +
                        "     */\n" +
                        "    public void setEmail(String email) {\n" +
                        "        this.email = email == null ? null : email.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 区域编码\n" +
                        "     * @return prefix 区域编码\n" +
                        "     */\n" +
                        "    public String getPrefix() {\n" +
                        "        return prefix;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 区域编码\n" +
                        "     * @param prefix the value for 区域编码\n" +
                        "     */\n" +
                        "    public void setPrefix(String prefix) {\n" +
                        "        this.prefix = prefix == null ? null : prefix.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 手机号\n" +
                        "     * @return mobile 手机号\n" +
                        "     */\n" +
                        "    public String getMobile() {\n" +
                        "        return mobile;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 手机号\n" +
                        "     * @param mobile the value for 手机号\n" +
                        "     */\n" +
                        "    public void setMobile(String mobile) {\n" +
                        "        this.mobile = mobile == null ? null : mobile.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 是否禁用， 0 正常 1 已禁用\n" +
                        "     * @return invalid 是否禁用， 0 正常 1 已禁用\n" +
                        "     */\n" +
                        "    public byte[] getInvalid() {\n" +
                        "        return invalid;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 是否禁用， 0 正常 1 已禁用\n" +
                        "     * @param invalid the value for 是否禁用， 0 正常 1 已禁用\n" +
                        "     */\n" +
                        "    public void setInvalid(byte[] invalid) {\n" +
                        "        this.invalid = invalid;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 昵称\n" +
                        "     * @return nickname 昵称\n" +
                        "     */\n" +
                        "    public String getNickname() {\n" +
                        "        return nickname;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 昵称\n" +
                        "     * @param nickname the value for 昵称\n" +
                        "     */\n" +
                        "    public void setNickname(String nickname) {\n" +
                        "        this.nickname = nickname == null ? null : nickname.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 密码\n" +
                        "     * @return password 密码\n" +
                        "     */\n" +
                        "    public String getPassword() {\n" +
                        "        return password;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 密码\n" +
                        "     * @param password the value for 密码\n" +
                        "     */\n" +
                        "    public void setPassword(String password) {\n" +
                        "        this.password = password == null ? null : password.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 个人简介\n" +
                        "     * @return profile 个人简介\n" +
                        "     */\n" +
                        "    public String getProfile() {\n" +
                        "        return profile;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 个人简介\n" +
                        "     * @param profile the value for 个人简介\n" +
                        "     */\n" +
                        "    public void setProfile(String profile) {\n" +
                        "        this.profile = profile == null ? null : profile.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * get field 头衔\n" +
                        "     * @return title 头衔\n" +
                        "     */\n" +
                        "    public String getTitle() {\n" +
                        "        return title;\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * set filed 头衔\n" +
                        "     * @param title the value for 头衔\n" +
                        "     */\n" +
                        "    public void setTitle(String title) {\n" +
                        "        this.title = title == null ? null : title.trim();\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public Long valueOfKey() {\n" +
                        "        return id;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void assignKeyValue(Long value) {\n" +
                        "        this.id = value;\n" +
                        "    }\n" +
                        "}";

        JavaParser javaParser = new JavaParser();

        ParseResult<CompilationUnit> unit1 = javaParser.parse(source);
        ParseResult<CompilationUnit> unit2 = javaParser.parse(source2);

        CompilationUnit merge = JavaMerger.merge(unit1.getResult().get(), unit2.getResult().get(), true);
        System.out.printf(merge.toString());
    }
}
