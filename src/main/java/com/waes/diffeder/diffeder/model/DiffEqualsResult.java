package com.waes.diffeder.diffeder.model;

public enum DiffEqualsResult {
    EQUALS("Objects are equal!"),
    DIFF_SIZES("Objects have different sizes!"),
    DIFFERENT("Objects are different!"),
    NO_LEFT("No LEFT Object!"),
    NO_RIGHT("No RIGHT Object!");

    public String getDesc() {
        return desc;
    }

    private String desc;

    DiffEqualsResult(String s) {
        desc = s;
    }
}
