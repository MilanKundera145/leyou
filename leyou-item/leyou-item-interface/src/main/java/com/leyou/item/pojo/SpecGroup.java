package com.leyou.item.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
@Data
@TableName("tb_spec_group")
public class SpecGroup {

    @TableId(value = "id")
    private Long id;
    private Long cid;
    private String name;
    private transient List<SpecParam> params;
}
