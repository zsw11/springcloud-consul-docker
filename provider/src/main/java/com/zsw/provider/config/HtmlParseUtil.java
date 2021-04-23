package com.zsw.provider.config;

import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.zsw.provider.entity.model.ParseDemo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author zsw
 * @date 2021/4/22 15:56
 * @description :
 */
@Component
public class HtmlParseUtil {

    public ArrayList<ParseDemo> parse(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyword + "&enc=utf-8";
        Document document = Jsoup.parse(new URL(url), 3000);
        Element j_goodsList = document.getElementById("J_goodsList");
        Elements tag_li = j_goodsList.getElementsByTag("li");
        ArrayList<ParseDemo> parseDemos = new ArrayList<>();
        for (Element el : tag_li) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");//获取img 标签的data-lazy-img属性地址
            String name = el.getElementsByClass("p-name").eq(0).text();
            String price = el.getElementsByClass("p-price").eq(0).text();
            ParseDemo parseDemo = new ParseDemo(img, name, price);
            parseDemos.add(parseDemo);
//            System.out.println(img);
//            System.out.println(name);
//            System.out.println(price);
//            System.out.println("-----------------");
        }
        return parseDemos;

    }

}
