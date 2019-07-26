package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("categoryListByPid")
    public ResponseEntity<List<Category>> categoryListByPid(@RequestParam("pid") Long pid){
        if (pid == null || pid.longValue() < 0)
            return ResponseEntity.badRequest().build();

        List<Category> categories = this.categoryService.categoryListByPid(pid);

        if (CollectionUtils.isEmpty(categories))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("categoryList")
    public ResponseEntity<List<Category>> categoryList(){

        List<Category> categories = this.categoryService.categoryList();

        if (CollectionUtils.isEmpty(categories))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(categories);
    }

}
