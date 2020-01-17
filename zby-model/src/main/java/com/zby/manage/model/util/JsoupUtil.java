package com.zby.manage.model.util;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author zby
 * @time 2019/12/5 9:24
 */
public class JsoupUtil {
    public static void main(String[] args)throws Exception{
//        a();
//        b();

        for (int j = 1; j <=32;j++) {
            Document doc = Jsoup.connect("https://jh.esf.fang.com/agenthome/-i3"+j+"-j310/").get();
            Elements sortC = doc.select(".agent_list li");
            for (Element e : sortC) {
                if (!StringUtils.isBlank(e.attr("link"))){
                    Elements fff = e.select("div.ttop b");
                    System.out.print(fff.text()+",");

                    Elements fggg = e.select("p.gray3");
                    System.out.print(fggg.text()+",");


                    Elements ee = e.select("span.gray3");
                    System.out.print(ee.text()+",");

                    Elements ccc = e.select(".map_dw span");
                    System.out.print(ccc.text()+",");


                    System.out.println("https://jh.esf.fang.com"+e.attr("link"));



                }
            }

            Thread.sleep(2000l);
        }
    }

    private static void b() throws IOException, InterruptedException {
        for (int j = 1; j <=65;j++) {
            Document doc = Jsoup.connect("http://f2.0579.cn/jjrlist.aspx?page="+j+"#main-left").get();
            Elements sortC = doc.select(".jjr-list dl dd ");

            for (Element e : sortC) {
                Elements ccc = e.select("a.c-red");

//            System.out.println(e.text());
                String[] ss = e.text().split(" ");
                System.out.print(ss[0] + ",");
                System.out.print(ss[1].substring(3) + ",");
                System.out.print(ss[2].substring(5) + ",");
                System.out.print(ss[3].substring(3) + ",");
                System.out.println("http://f2.0579.cn" + ccc.attr("href"));
            }

            Thread.sleep(1000l);
        }
    }

    private static void a() {
        int i = 0;
        Document doc = null;
        try {
            doc = Jsoup.connect("https://poi.mapbar.com/jinhua/FA0/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();
        Elements sortC = doc.select("dl dd a[href]");
//            System.out.println(sortC.size());
        for (Element a:sortC){
            String eleUrl = a.attr("href");
            Document newDoc = null;
            if (eleUrl.equals("http://poi.mapbar.com/jinhua/MAPITEJEHOTESPFNSRSPC")){
                i = 1;
            }
            if (i == 0){
                continue;
            }
            try {
                newDoc = Jsoup.connect(eleUrl).get();
            } catch (IOException e) {
                System.out.println(eleUrl);
                e.printStackTrace();
                continue;
            }
            Elements houses = newDoc.select("h1");
            String name = houses.html();
            System.out.print(name+",");

            Elements phone = newDoc.select("li.telCls");
            String phonee = phone.text();
            String []ph = phonee.split(" ");
            if (ph.length == 2 ){
                System.out.print("无,");
            }else {
                System.out.print(ph[1]+",");
            }




            Elements addresss = newDoc.select(".POI_ulA");
            String add = addresss.text();
//                System.out.print(add);
            int index = add.indexOf("电话");
            add = add.substring(0,index);
//                System.out.println(add);
            String[] adds = add.split(" ");
//                System.out.println(adds.length);
            if (adds.length == 5){
                System.out.println(adds[2]+" "+adds[3]+" "+adds[4]);
            }else {
                System.out.println(adds[2]+" "+adds[3]);
            }

        }
    }
}
