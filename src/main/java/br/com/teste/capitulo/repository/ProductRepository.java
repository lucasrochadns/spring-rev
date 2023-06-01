package br.com.teste.capitulo.repository;

import br.com.teste.capitulo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
