package com.xstudio.test.object.second;

import com.xstudio.test.object.first.FirstObject;
import com.xstudio.test.object.AbstractObject;

import java.util.List;

/**
 * class comment for SecondObject
 *
 * @author xiaobiao
 * @version 1.0.0
 */
public class SecondObject extends AbstractObject<Long> {
    /**
     * firstObject
     */
    private FirstObject firstObject;
    /**
     * second string
     */
    private String secondString;

    /**
     * list
     */
    private List<FirstObject> firstObjectList;


    /**
     * get field firstObject
     *
     * @return firstObject firstObject
     */
    public FirstObject getFirstObject() {
        return this.firstObject;
    }

    /**
     * get field list
     *
     * @return firstObjectList list
     */
    public List<FirstObject> getFirstObjectList() {
        return this.firstObjectList;
    }

    /**
     * get field second string
     *
     * @return secondString second string
     */
    public String getSecondString() {
        return this.secondString;
    }

    /**
     * set field firstObject
     *
     * @param firstObject firstObject
     */
    public void setFirstObject(FirstObject firstObject) {
        this.firstObject = firstObject;
    }

    /**
     * set field list
     *
     * @param firstObjectList list
     */
    public void setFirstObjectList(List<FirstObject> firstObjectList) {
        this.firstObjectList = firstObjectList;
    }

    /**
     * set field second string
     *
     * @param secondString second string
     */
    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }


}
