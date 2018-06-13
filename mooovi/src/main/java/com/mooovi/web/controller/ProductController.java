package com.mooovi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mooovi.business.entity.Product;
import com.mooovi.business.service.ProductService;

//作品の個別ページを表示
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    //topページから画像クリックし、show.htmlへ遷移
    @GetMapping("/products/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "products/show";
    }
    
    //検索
    @GetMapping("/products/search")
    public String search(@RequestParam(defaultValue = "") String keyword, Model model) {
        List<Product> products = productService.findAllByTitleLike(keyword);
        model.addAttribute("products", products);
        return "products/search";
    }

}
