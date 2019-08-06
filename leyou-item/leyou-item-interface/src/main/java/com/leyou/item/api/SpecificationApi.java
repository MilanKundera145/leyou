package com.leyou.item.api;

import com.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("spec")
public interface SpecificationApi {

    @GetMapping("params")
    ResponseEntity<List<SpecParam>> queryParams(@RequestParam("gid") Long gid);
}
