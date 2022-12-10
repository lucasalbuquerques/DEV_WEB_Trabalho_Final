package com.barbearia.senai.repository;

import com.barbearia.senai.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByNome(String nome);
    boolean existsByNome(String nome);
    boolean existsByData(String data);
    boolean existsByHora(String hora);
}
