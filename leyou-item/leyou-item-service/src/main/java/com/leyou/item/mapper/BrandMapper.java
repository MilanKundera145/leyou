package com.leyou.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Param;

public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 分页查询
     * @param key
     * @param page
     * @return
     */
    IPage<Brand> queryBrandsByPage(Page<Brand> page, @Param("key") String key);
}
