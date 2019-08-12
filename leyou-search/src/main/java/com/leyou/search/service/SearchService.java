package com.leyou.search.service;

import com.leyou.item.pojo.Spu;
import com.leyou.search.pojo.Goods;

import java.io.IOException;

public interface SearchService {

    Goods buildGoods(Spu spu) throws IOException;
}
