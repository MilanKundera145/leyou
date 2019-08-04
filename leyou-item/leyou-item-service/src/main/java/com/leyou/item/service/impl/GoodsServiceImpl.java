package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Spu;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Service("goodsService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true,rollbackFor=Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {
        QueryWrapper<Spu> wrapper = new QueryWrapper<>();
        // 搜索条件
        if (StringUtils.isNotBlank(key))
            wrapper.lambda().like(Spu::getTitle,key);
        if (saleable != null)
            wrapper.lambda().eq(Spu::getSaleable,saleable);
        // 分页条件
        IPage<Spu> iPage = new Page<>(page,rows);
        IPage<Spu> spuPage = this.spuMapper.selectPage(iPage, wrapper);
        List<Spu> records = spuPage.getRecords();

        List<SpuBo> spuBos = new ArrayList<>();
        records.forEach(spu -> {
            SpuBo spuBo = new SpuBo();

            // copy共同属性的值到新的对象
            BeanUtils.copyProperties(spu,spuBo);

            // 查询分类名称
            List<String> cnames = this.categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3()));
            spuBo.setCname(StringUtils.join(cnames,"/"));

            // 查询品牌的名称
            spuBo.setBname(this.brandMapper.selectById(spu.getBrandId()).getName());
            spuBos.add(spuBo);
        });

        return new PageResult<>(spuPage.getTotal(),(int)spuPage.getPages(),spuBos);
    }
}
