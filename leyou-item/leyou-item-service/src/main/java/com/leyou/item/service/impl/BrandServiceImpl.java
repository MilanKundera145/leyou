package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("brandService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true,rollbackFor=Exception.class)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public IPage<Brand> queryBrandsByPage(String key, Long pageNo, Long size) {

        Page<Brand> page = new Page<>(pageNo,size);
        IPage<Brand> iPage = this.baseMapper.queryBrandsByPage(page,key);
        return iPage;
    }

    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        //先新增brand
        this.baseMapper.insert(brand);
        //在新增中间表
        cids.forEach(cid->{
            this.baseMapper.insertCategoryAndBrand(cid, brand.getId());
        });
    }

    @Override
    public List<Brand> queryBrandsByCid(Long cid) {
        return this.baseMapper.selectBrandByCid(cid);
    }
}
