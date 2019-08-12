package com.leyou;

import com.leyou.search.client.CategoryClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void test(){
        List<String> list = categoryClient.queryNamesByIds(Arrays.asList(1l, 3l, 2l));
        System.out.println(list);
    }

    @Test
    public void test1(){
        List<Integer> integers = Arrays.asList(1, 3, 2);

        System.out.println(integers);
    }
}
