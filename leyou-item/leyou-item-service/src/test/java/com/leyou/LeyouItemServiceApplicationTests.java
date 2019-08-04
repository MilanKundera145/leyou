package com.leyou;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Sku;
import com.leyou.item.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LeyouItemServiceApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private BrandService brandService;

    @Test
    public void test(){
        categoryMapper.categoryList();
    }

    @Test
    public void test2(){
        IPage<Brand> iPage = this.brandService.queryBrandsByPage("L", 1L, 10L);
        System.out.println(iPage.getRecords());
    }


    @Test
    public void test3(){
        Brand brand = new Brand();
        brand.setName("我就是测试一下");
        brand.setImage("我就是测试一下");
        brand.setLetter('L');
        List<Long> cids = new ArrayList<>();
        cids.add(123l);
        cids.add(234l);
        cids.add(345l);
        this.brandService.saveBrand(brand,cids);
    }

    @Autowired
    private SkuMapper skuMapper;
    @Test
    public void test4(){
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Sku::getSpuId,3);
        List<Sku> skus = this.skuMapper.selectList(wrapper);
        List<Long> ids = skus.stream().map(sku->sku.getId()).
                collect(Collectors.toList());
        System.out.println(ids);
    }

}
