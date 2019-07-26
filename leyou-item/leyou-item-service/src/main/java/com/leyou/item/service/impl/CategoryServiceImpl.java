package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("categoryService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true,rollbackFor=Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> categoryListByPid(Long pid) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Category::getParentId,pid);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Category> categoryList() {
        return this.baseMapper.categoryList();
    }
}
