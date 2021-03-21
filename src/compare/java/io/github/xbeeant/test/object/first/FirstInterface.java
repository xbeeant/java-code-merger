package io.github.xbeeant.test.object.first;

import io.github.xbeeant.test.object.AbstractInterface;

import java.util.List;

/**
 * description override
 *
 * @author huangxiaobiao
 * @date 2020/10/21
 */
public interface FirstInterface extends AbstractInterface<String> {
    /**
     * method1
     *
     * @param str2 param description
     * @return {@link List &lt;String&gt;}
     */
    @Override
    String method1(String str2);


    String method1(String str1, String str2);
}
