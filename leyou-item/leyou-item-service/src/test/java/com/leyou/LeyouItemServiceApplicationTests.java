package com.leyou;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LeyouItemServiceApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;
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

}
