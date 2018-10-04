package com.capgemini.productapp;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
	
	@InjectMocks
	private ProductController productController;
	
	@Mock
	private ProductService productService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testaddProduct() throws Exception {
		
		String content = "{\"productId\":1,\"productName\":\"kitkat\",\"productCategory\":\"chocos\",\"productPrice\":5000.0}";

		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(1, "kitkat", "chocos", 5000.0));
			mockMvc.perform(post("/product")

			.contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
			.andExpect(jsonPath("$.productId").value(1));
		
	}
	
	/*@Test
	public void testupdateProduct() throws Exception {
		
		String content = "{\"productId\":1,\"productName\":\"kitkaty\",\"productCategory\":\"chocos\",\"productPrice\":1.0}";

		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(new Product(1, "kitkat", "chocos", 1.0));
			mockMvc.perform(post("/product")

			.contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
			.andExpect(jsonPath("$.productId").value(1));
		
	}
	*/


}
