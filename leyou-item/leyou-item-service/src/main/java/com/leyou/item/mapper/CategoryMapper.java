package com.leyou.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 所有类目
     * @return
     */
    List<Category> categoryList();
}
