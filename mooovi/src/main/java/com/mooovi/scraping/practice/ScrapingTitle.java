package com.mooovi.scraping.practice;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapingTitle {

	public static void main(String[] args) {
		try {
			scraping();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void scraping() throws IOException {// .entry-title
		Document document = Jsoup.connect("http://review-movie.herokuapp.com/").get(); // ①サイトのHTML情報を取得
		/*タイトル取得*/
		// Elements elements = document.select(".index-entry-title a"); //
		// ②取得したDocumentクラスからHTML要素の情報を取得(クラス タグ分類)
		// for (Element element : elements) { // ③
		// System.out.println(element.text()); // ④
		// }
		/*URL取得*/
		Elements elements = document.select(".poster_link img"); // ②
		for (Element element : elements) { // ③
			System.out.println(element.attr("src")); // ④
		}

	}

}
