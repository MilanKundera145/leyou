package com.leyou.item.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 */
@Data
@TableName("tb_category")
public class Category {

    @TableId(value = "id")
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent; // 注意isParent生成的getter和setter方法需要手动加上Is
    private Integer sort;
}
