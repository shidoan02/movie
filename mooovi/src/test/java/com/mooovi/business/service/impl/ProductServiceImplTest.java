package com.mooovi.business.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mooovi.business.entity.Product;
import com.mooovi.business.repository.ProductRepository;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	// テスト対象のクラスであるProductServiceImplを指定
	@InjectMocks
	private ProductServiceImpl productService;

	// 依存モックオブジェクト
	@Mock
	private ProductRepository mockProductRepository;

	//簡単なテスト例
	//テストメソッドは public void
	@Test
	public void testFindOne() {
		Product product = new Product(); // mockProductRepositoryのfindOneメソッドが呼び出されたときに帰ってくるProductエンティティを用意
		product.setId(1L);
		product.setTitle("テスト映画");
		when(mockProductRepository.findOne(1L)).thenReturn(product); // ②mockProductRepositoryのfindOneメソッドを引数に1Lを渡して呼び出したときに①で用意したproductを返す

		assertThat(productService.findOne(1L).getTitle(), is("テスト映画")); // ③ではテスト対象のメソッドが期待通りの振る舞いをするかアサーション
	}
	
	@Test
	public void testFindOneOrNew(){
	    Product product = new Product(); // ①モックであるmockProductRepositoryのfindByTitleメソッドが呼び出されたときに帰ってくるProductエンティティを用意
	    product.setId(1L);
	    product.setTitle("テスト映画");
	    when(mockProductRepository.findByTitle("テスト映画")).thenReturn(product); // ②mockProductRepositoryのfindByTitleメソッドの引数に"テスト映画"を渡したときにproductを返す
	    when(mockProductRepository.findByTitle("映画")).thenReturn(null); // ③引数に"映画"を渡したときはnullを返す

	    assertThat(productService.findOneOrNew("テスト映画").getId(), is(1L)); // ④テスト対象であるfindOneOrNewメソッドを引数として"テスト映画"を渡して呼び出した場合のアサーション
	    assertThat(productService.findOneOrNew("映画").getId(), is(nullValue())); // ⑤引数として"映画"を渡して呼び出した場合のアサーション
	}

}
