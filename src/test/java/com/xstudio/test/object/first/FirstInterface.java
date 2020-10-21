package com.xstudio.test.object.first;

import com.xstudio.test.object.AbstractInterface;

import java.util.List;

/**
 * description override
 *
 * @author huangxiaobiao
 * @date 2020/10/21
 */
public interface FirstInterface extends AbstractInterface<List<String>> {
    /**
     * method1
     *
     * @param str param description
     * @return {@link List&lt;String&gt;}
     */
    @Override
    List<String> method1(String str);
}
