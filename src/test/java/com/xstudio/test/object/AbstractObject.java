package com.xstudio.test.object;

public abstract class AbstractObject<T> {
    /**
     * data
     */
    private T data;


    /**
     * get field data
     *
     * @return data data
     */
    public T getData() {
        return this.data;
    }

    /**
     * set field data
     *
     * @param data data
     */
    public void setData(T data) {
        this.data = data;
    }
}
