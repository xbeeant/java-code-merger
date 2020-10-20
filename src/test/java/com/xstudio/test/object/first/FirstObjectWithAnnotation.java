package com.xstudio.test.object.first;

// import package comment
import com.xstudio.test.object.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * class comment for FirstObjectWithAnnotation
 *
 * @author xiaobiao
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FirstObjectWithAnnotation extends AbstractObject<Long> {
    /**
     * first string
     */
    private String firstStr;


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
        return this.firstStr;
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
