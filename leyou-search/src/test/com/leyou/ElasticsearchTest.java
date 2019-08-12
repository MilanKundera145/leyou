package com.leyou;

import com.leyou.search.pojo.Goods;
import com.leyou.search.repository.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private GoodsRepository repository;
    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void createIndex(){
        //创建索引库和映射
        this.template.createIndex(Goods.class);
        this.template.putMapping(Goods.class);
    }
}
