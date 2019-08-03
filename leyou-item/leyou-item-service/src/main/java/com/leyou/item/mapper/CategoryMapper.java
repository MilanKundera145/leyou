package com.leyou.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 所有类目
     * @return
     */
    List<Category> categoryList();

    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    @Select("SELECT * FROM tb_category WHERE id IN " +
            "(SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(Long bid);
}
