package com.leyou;

import com.leyou.item.mapper.CategoryMapper;
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

    @Test
    public void test(){
        categoryMapper.categoryList();
    }

}
