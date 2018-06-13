package com.mooovi.scraping.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*個別作品ページからそれぞれURL取得する*/
public class ScrapingImages {

	public static void main(String[] args) {
		try {

			List<String> links = collectPageLink(); // collectPageLink メソッド
			for (String link : links)
				scraping(link); // scraping メソッド

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> collectPageLink() throws IOException {
		List<String> links = new ArrayList<>();
		// 個別ページのリンクをリストに追加する
		Document document = Jsoup.connect("http://review-movie.herokuapp.com").get(); // ②
		Elements elements = document.select(".entry-title a"); // ③
		for (Element element : elements) { // ④
			links.add(element.attr("href")); // ⑤
		}
		return links;
	}

	public static void scraping(String link) throws IOException {
		// 個別ページから作品画像のURLを取得する
		Document document = Jsoup.connect("http://review-movie.herokuapp.com" + link).get(); // ⑦
		Elements elements = document.select(".entry-content img"); // ⑧
		Element element = elements.first(); // ⑨
		System.out.println(element.attr("src")); // ⑩
	}

}
