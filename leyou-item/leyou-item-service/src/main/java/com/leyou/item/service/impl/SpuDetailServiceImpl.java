package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.SpuDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("spuDetailService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true,rollbackFor=Exception.class)
public class SpuDetailServiceImpl extends ServiceImpl<SpuDetailMapper, SpuDetail> implements SpuDetailService {
    @Override
    public void SpuDetail(SpuDetail spuDetail) {
        this.baseMapper.insert(spuDetail);
    }
}
