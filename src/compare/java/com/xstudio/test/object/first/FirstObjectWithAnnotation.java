package com.xstudio.test.object.first;

// import package comment override
import com.xstudio.test.object.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * class comment for FirstObjectWithAnnotation override
 *
 * @author xiaobiao
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class FirstObjectWithAnnotation extends AbstractObject<Long> {
    /**
     * first exist string
     */
    private String firstStr;

    /**
     * first missing string
     */
    private String firstMissingStr;

    /**
     * 一个方法
     */
    public void firstMethod() {
        // first method line comment
        List<String> list = new ArrayList<String>();
        list.isEmpty();
    }

    /**
     * get field first string
     *
     * @return firstStr first string
     */
    public String getFirstStr() {
        return     firstStr;
    }

    /**
     * set field first string
     *
     * @param firstStr first string
     */
    public void setFirstStr(String firstStr) {
        this.firstStr = firstStr;
    }


}
