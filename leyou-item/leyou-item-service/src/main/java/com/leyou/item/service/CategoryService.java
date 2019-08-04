package com.leyou.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * 根据父id查询子类目
     * @param pid
     * @return
     */
    List<Category> categoryListByPid(Long pid);

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
    List<Category> queryByBid(Long bid);

    /**
     * 查询分类名称
     * @param ids
     * @return
     */
    List<String> queryNamesByIds(List<Long> ids);
}
