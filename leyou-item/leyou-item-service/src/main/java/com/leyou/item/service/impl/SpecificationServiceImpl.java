package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.context.ThemeSource;

import java.util.List;

@Slf4j
@Service("specificationService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true,rollbackFor=Exception.class)
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        QueryWrapper<SpecGroup> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SpecGroup::getCid,cid);
        return this.specGroupMapper.selectList(wrapper);
    }

    @Override
    public List<SpecParam> queryParams(Long gid) {
        QueryWrapper<SpecParam> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SpecParam::getGroupId,gid);
        return this.specParamMapper.selectList(wrapper);
    }
}
