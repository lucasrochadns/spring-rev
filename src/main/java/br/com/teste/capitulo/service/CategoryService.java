package br.com.teste.capitulo.service;

import br.com.teste.capitulo.domain.Category;
import br.com.teste.capitulo.repository.CategoryRepository;
import br.com.teste.capitulo.service.exceptions.DBException;
import br.com.teste.capitulo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Page<Category> getCategory(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Category create(Category category){
        return repository.save(category);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Category findById(Long id){
       return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found "));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch(DataIntegrityViolationException message)
        {
            throw new DBException("Data Base Error");
        } catch(Exception message){
            throw new DBException(message.getMessage());
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Category update(Long id, Category category){
       try{
           Category category01 = repository.getReferenceById(id);
           category01.setName(category.getName());
           return repository.save(category01);
       }catch(EntityNotFoundException message){
           throw new DBException("Data base error");
       }
    }
}
