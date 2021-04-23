package com.zsw.provider.entity.model;

import lombok.Data;

/**
 * @author zsw
 * @date 2021/4/22 15:57
 * @description : 爬取的数据
 */
@Data
public class ParseDemo {
    String img;
    String name;
    String price;

    public ParseDemo(String img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }
}
