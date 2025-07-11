package com.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {
    private List<T> records;
    private Long total;

   public PageData(List<T> records, Long total) {
        this.records = records;
        this.total = total;
    }
}
