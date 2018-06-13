package com.mooovi.scraping.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapingDetailImages {

    public static void main(String[] args){
        try {
            List<String> links = collectPageLink();
            for(String link : links){
                scraping(link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> collectPageLink() throws IOException {
        List<String> links = new ArrayList<>();
        // 個別ページのリンクをリストに追加する
        return links;
    }

    public static void scraping(String link) throws IOException {
        // 個別ページから作品画像のURLを取得する
    }

}
