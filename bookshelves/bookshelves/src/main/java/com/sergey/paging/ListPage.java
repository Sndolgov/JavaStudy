package com.sergey.paging;

import java.util.List;

public class ListPage<T> {

    private int page;

    private List<T> data;

    private int total;

    private int maxPerPage;

    public ListPage(List<T> data, int page, int total, int maxPerPage) {
        this.data = data;
        this.page = page;
        this.total = total;
        this.maxPerPage = maxPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

}