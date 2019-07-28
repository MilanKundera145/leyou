package com.leyou.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.item.pojo.Brand;

public interface BrandService extends IService<Brand> {

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param pageNo
     * @param size
     * @return
     */
    IPage<Brand> queryBrandsByPage(String key, Long pageNo, Long size);
}
