package io.github.xbeeant.test.object.first;

// import package comment override
import io.github.xbeeant.test.object.AbstractObject;
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
     * first exist string override
     */
    private String firstStr;
}
