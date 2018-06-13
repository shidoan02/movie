package com.mooovi.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	@Autowired
	private WebApplicationContext context; // ①

	private MockMvc mockMvc; // ②MockMvcの変数を用意

	/* MockMvcの設定 */
	@Before // ③Junitの機能
	public void setupMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); // ④MockMvcの生成
	}

	/* showメソッド */
	@Test
	public void testShow() throws Exception {
		mockMvc.perform(get("/products/1")) // ①"/products/1"にGETでアクセスするリクエストを作成
				.andExpect(status().isOk()) // ②HttpStatusがOKであることを検証 statusメソッドを使うことでHttpStatusの結果をテスト
				.andExpect(view().name(is("products/show"))); // ③遷移先にviewの名前がProducts/showであることを検証
	}

	/* searchメソッド キーワードで検索した結果を表示 */
	@Test
	public void testSearch() throws Exception {
		mockMvc.perform(get("/products/search").param("keyword", "the")) // ①"/products/search"にGETでアクセスするリクエストを作成
				.andExpect(status().isOk()) // ②リクエストの結果のHttpStatusを検証
				.andExpect(view().name(is("products/search"))) // ③遷移先のview名を検証
				.andExpect(model().attribute("products", hasSize(4))); // ④ProductController#searchでmodelにセットしたproductsの内容に関して検証
	}

}
