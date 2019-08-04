package com.leyou.item.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_spec_param")
public class SpecParam {
    @TableId(value = "id")
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @TableField("'numeric'")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}
