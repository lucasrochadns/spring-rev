package br.com.teste.capitulo.service;

import br.com.teste.capitulo.domain.Product;
import br.com.teste.capitulo.repository.ProductRepository;
import br.com.teste.capitulo.resource.product.dto.ProductIO;
import br.com.teste.capitulo.resource.utils.MapperUtil;
import br.com.teste.capitulo.service.exceptions.DBException;
import br.com.teste.capitulo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Page<Product> index(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Product findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Product create(Product product){
         return repository.save(product);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Product update(Long id, Product product){
        try{
            Product product01 = repository.getReferenceById(id);
            product01.setName(product.getName()); product01.setDescription(product.getDescription());
            product01.setPrice(product.getPrice());
            return repository.save(product01);
        }catch(Exception message){
            throw new DBException("Error Database");
        }
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(Exception message){
            throw new DBException("Error Database");
        }
    }

}
