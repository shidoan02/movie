package com.mooovi.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mooovi.business.entity.Product;
import com.mooovi.business.service.ProductService;

@Component
public class Scraping {

	@Autowired
	private ProductService productService;

	private static final String SITE_URL = "http://review-movie.herokuapp.com/";

	// collectPageLinkメソッドを呼び出し、集めた個別ページのリンク一つ一つをsaveProductメソッドの引数に渡す
	public void execute() throws IOException {
		System.out.println("execute scraping!");
		List<String> links = collectPageLink(); // ①collectPageLinkを呼び出しリストを取得する
		for (String link : links) { // ②リストから1つ1つリンクを取り出し、saveProductの引数に渡す
			saveProduct(SITE_URL + link); // ③
		}
	}

	// 表示されている全ての映画の個別ページのリンクURLを取得し、そのリンクをリストに追加
	private List<String> collectPageLink() throws IOException {

		List<String> links = new ArrayList<>();// ①リストを用意する
		String nextUrl = ""; // 最初はtopページなので空白
		while (true) {
			// スクレイピング対象のページのURL
			Document document = Jsoup.connect(SITE_URL + nextUrl).get();
			Elements elements = document.select(".entry-title a");
			for (Element element : elements) {
				links.add(element.attr("href"));
			}
			Element nextLink = document.select(".pagination .next a").first(); // 個別ページのURLのタグからhref要素を取り出し、リストに追加する
			if (nextLink != null) { // nullがなければ
				nextUrl = nextLink.attr("href"); // nextLinkからhref属性の値を取得nextUrlに代入
			} else {
				break; // nullならbreak
			}
		}
		return links;
	}

	// 引数として渡された個別ページのリンクURLから「作品名」＋「作品画像のURL」をスクレイピングし、productsテーブルに保存
	private void saveProduct(String link) throws IOException {

		Document document = Jsoup.connect(link).get(); // 
		String title = document.select(".entry-title").first().text(); // 
		String imageUrl = document.select(".entry-content img").first().attr("src"); // 
		String director = document.select(".director span").first().text(); // 
		String detail = document.select(".entry-content p").first().text(); // 
		String openDate = document.select(".date span").first().text(); // 

		//同じタイトルの映画はDBに保存しない
		Product product = productService.findOneOrNew(title);

		// Product product = new Product(); // ⑦Productクラスのインスタンス
		product.setTitle(title);
		product.setImageUrl(imageUrl);
		product.setDirector(director); // 
		product.setDetail(detail); // 
		product.setOpenDate(openDate); // 
		productService.save(product);
	}
}
// public static void main(String[] args) {
// SpringApplication.run(ScrapingApplication.class, args);
// System.exit(0);
// }
