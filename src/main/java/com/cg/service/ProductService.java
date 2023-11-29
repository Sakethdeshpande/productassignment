package com.cg.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Product;
import com.cg.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productrepo;
	public Optional<Product> getProduct(int id){
		Optional<Product> p=productrepo.findById(id);
		return p;
	}
	public Product createProduct(Product product) {
		return productrepo.save(product);
	}
	public List<Product> listAll() {
		 
		List<Product> products= productrepo.findAll();
		System.out.println(products);
		return products;
	}

	public Map<String,Boolean> deleteProduct(Integer productId){

		productrepo.deleteById(productId);
		Map<String,Boolean> response=new HashMap();
		response.put("product has been deleted", Boolean.TRUE);
		return response;
	}
	public String updateProduct(Integer productId, Product newProduct) {
		Optional<Product> existingProduct = productrepo.findById(productId);
		if(existingProduct.isPresent()) {
			Product foundProduct = existingProduct.get();
			foundProduct.setName(newProduct.getName());
			foundProduct.setPrice(newProduct.getPrice());
			productrepo.save(foundProduct);
			return "product Updated";
		}
		return "product Not Updated";
	}
}
