package com.leyou.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.item.pojo.SpuDetail;

public interface SpuDetailService extends IService<SpuDetail> {

    /**
     * 新增spuDetail
     * @param spuDetail
     */
    void SpuDetail(SpuDetail spuDetail);
}
