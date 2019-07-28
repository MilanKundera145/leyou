package com.leyou.item.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("pageBrand")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "pageNo", defaultValue = "1")Long pageNo,
            @RequestParam(value = "size", defaultValue = "5")Long size,
            @RequestParam(value = "sortBy", required = false)String sortBy
    ){
        IPage<Brand> iPage = this.brandService.queryBrandsByPage(key, pageNo, size, sortBy);

        PageResult<Brand> result = new PageResult<>();
        result.setItems(iPage.getRecords());
        result.setTotalPage(result.getTotalPage());
        result.setTotal(result.getTotal());
        if (CollectionUtils.isEmpty(iPage.getRecords())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
