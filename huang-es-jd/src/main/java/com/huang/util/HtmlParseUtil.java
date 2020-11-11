package com.huang.util;

import com.alibaba.fastjson.JSON;
import com.huang.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {

    public List<Content> parseJD(String keywords) throws IOException {
        //获取请求:     https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=f807c58b66dc4baab4c7ed71834c36be
        //前提,联网,ajax不能获得

        String url = "https://search.jd.com/Search?keyword="+keywords+"&enc=utf-8";
        //解析网页。(JIsoup返 回Document就是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有你在js中可以使用的方法，这里都能用!
        Element element = document.getElementById("J_goodsList");
        //System.out.println(element.html());
        //获取所有的Li元素
        Elements elements = element.getElementsByTag("li");
        //
        List<Content> contents = new ArrayList<>();

        //获取元素中的内容,这野l就是每一 个Li标签了!
        for (Element el : elements) {
            //由于图片是延迟加载
            //data-lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            /*System.out.println("===============================");
            System.out.println(img);
            System.out.println(price);
            System.out.println(title);*/
            contents.add(new Content(img,price,title));
        }

        return contents;
    }

    public static void main(String[] args) throws IOException {
        new HtmlParseUtil().parseJD("耐克").forEach(System.out::println);
    }
}
