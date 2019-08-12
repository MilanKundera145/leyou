package com.leyou.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.item.pojo.Brand;

import java.util.List;

public interface BrandService extends IService<Brand> {

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param pageNo
     * @param size
     * @return
     */
    IPage<Brand> queryBrandsByPage(String key, Long pageNo, Long size);

    /**
     * 新增品牌
     * @param brand
     * @param cids
     */
    void saveBrand(Brand brand, List<Long> cids);

    /**
     * 品牌集合
     * @param cid
     * @return
     */
    List<Brand> queryBrandsByCid(Long cid);

    /**
     * 查询品牌
     * @param id
     * @return
     */
    Brand queryBrandById(Long id);
}
