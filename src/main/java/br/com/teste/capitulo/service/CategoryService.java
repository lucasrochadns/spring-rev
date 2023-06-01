package br.com.teste.capitulo.service;

import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public List<Category> getCategory(){
        return repository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Category create(Category category){
        return repository.save(category);
    }

}
