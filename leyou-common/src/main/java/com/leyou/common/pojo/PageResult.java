package com.leyou.common.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long total;// 总条数
    private Integer totalPage;// 总页数
    private List<T> items;// 当前页数据
}
