package com.leyou.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 分页查询
     * @param key
     * @param page
     * @return
     */
    IPage<Brand> queryBrandsByPage(Page<Brand> page, @Param("key") String key);

    /**
     * 新增商品分类和品牌中间表数据
     * @param cid
     * @param bid
     */
    @Insert("INSERT INTO tb_category_brand(category_id, brand_id) VALUES (#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 根据cid查询品牌
     * @param cid
     * @return
     */
    @Select("SELECT b.* from tb_brand b INNER JOIN " +
            "tb_category_brand cb on b.id=cb.brand_id where cb.category_id=#{cid}")
    List<Brand> selectBrandByCid(Long cid);
}
