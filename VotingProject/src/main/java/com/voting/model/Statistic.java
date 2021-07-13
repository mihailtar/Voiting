package com.voting.model;

public class Statistic {
    private String option;
    private int count;

    public Statistic() {
    }

    public Statistic(String option, int count) {
        this.option = option;
        this.count = count;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
