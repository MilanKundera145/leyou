package com.leyou.item.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系
 */
@Data
@TableName("tb_brand")
public class Brand {

    @TableId(value = "id")
    private Long id;
    private String name;// 品牌名称
    private String image;// 品牌图片
    private Character letter;
}
