package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
    public Product getProductById(Long id) throws UserNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any product with id" + id);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategoryId(id);
    }

    public List<Product> getAllProductsByName(String name){
        List<Product> result = productRepository.findAllByNameContains(name);
        if(result != null){
            return result;
        }else{
            System.out.println("Can not find product by name");
            return null;
        }
    }
    public List<Product> sortProductByPriceAsc(){
        List<Product> result = productRepository.findAll(Sort.by("price").ascending());
            return result;
    }
    public List<Product> sortProductByPriceDesc(){
        List<Product> result = productRepository.findAll(Sort.by("price").descending());
            return result;
    }
    public List<Product> sortProductByNameAsc(){
        List<Product> result = productRepository.findAll(Sort.by("name").ascending());
        return result;
    }
    public List<Product> sortProductByNameDesc(){
        List<Product> result = productRepository.findAll(Sort.by("name").descending());
        return result;
    }
    // Pagination
    public Page<Product> findPaginated(int pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<Product> productList = productRepository.findAll(pageable);
        return productList;
    }

    public Page<Product> findAllWithSort(String sortField, String sortDir, int pageNo) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, 5, sort);
        return productRepository.findAll(pageable);
    }
    public Page<Product> findAllByName(String name, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5 );
        return productRepository.findAllByNameContains(name, pageable);
    }

}
