package io.github.xbeeant.test.object.first;

// import package comment
import io.github.xbeeant.test.object.AbstractObject;
import lombok.EqualsAndHashCode;

/**
 * class comment for FirstObjectWithAnnotation
 *
 * @author xiaobiao
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
public class FirstObjectWithAnnotation extends AbstractObject<Long> {
    /**
     * first string
     */
    private String firstStr;


    /**
     * get field first string
     *
     * @return firstStr first string
     */
    public String getFirstStr() {
        // body comment
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
