package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("brand")
public interface BrandApi {

    @GetMapping("cid/{cid}")
    List<Brand> queryBrandsByCid(@PathVariable("cid")Long cid);

    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable("id") Long id);
}
