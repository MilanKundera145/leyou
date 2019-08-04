package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.pojo.Stock;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import com.leyou.item.service.SpuDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service("goodsService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true,rollbackFor=Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private CategoryService categoryService;

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

    @Override
    public void saveGoods(SpuBo spuBo) {
        // 新增spu
        // 设置默认字段
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        this.spuMapper.insert(spuBo);

        // 新增spuDetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        this.spuDetailMapper.insert(spuDetail);

        saveSkuAndStock(spuBo);
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return this.spuDetailMapper.selectById(spuId);
    }

    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Sku::getSpuId,spuId);
        List<Sku> skus = this.skuMapper.selectList(wrapper);

        skus.forEach(sku -> {
            Stock stock = this.stockMapper.selectById(sku.getId());
            sku.setStock(stock.getStock());
        });
        return skus;
    }

    @Override
    public void updateGoods(SpuBo spuBo) {
        // 查询以前sku
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Sku::getSpuId,spuBo.getId());
        List<Sku> skus = this.skuMapper.selectList(wrapper);

        // 如果以前存在，则删除
        if (CollectionUtils.isEmpty(skus)) {
            List<Long> ids = skus.stream().map(sku->sku.getId()).
                    collect(Collectors.toList());

            // 删除以前库存
            this.stockMapper.deleteBatchIds(ids);

            // 删除以前的sku
            this.skuMapper.deleteById(spuBo.getId());
        }

        // 新增sku和库存
        saveSkuAndStock(spuBo);

        // 更新spu
        spuBo.setLastUpdateTime(new Date());
        this.spuMapper.updateById(spuBo);

        // 更新spu详情
        this.spuDetailMapper.updateById(spuBo.getSpuDetail());
    }

    private void saveSkuAndStock(SpuBo spuBo) {
        spuBo.getSkus().forEach(sku -> {
            // 新增sku
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insert(sku);

            // 新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insert(stock);
        });
    }
}
