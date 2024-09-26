package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

public class DataModel<T> {
    protected T value;

    public DataModel(T initial) {
        value = initial;
    }

    public T getData() {
        return value;
    }

    public void setData(T data) {
        value = data;
    }
}
