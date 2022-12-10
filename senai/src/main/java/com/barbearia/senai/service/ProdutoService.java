package com.barbearia.senai.service;

import com.barbearia.senai.entity.Produto;
import com.barbearia.senai.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> list(){
        return produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "data", "hora"));
    }

    public Optional<Produto> getOne(int id){
        return produtoRepository.findById(id);
    }

    public Optional<Produto> getBynome(String nome){
        return produtoRepository.findByNome(nome);
    }



    public void save(Produto produto){
        produtoRepository.save(produto);
    }

    public void delete(int id){
        produtoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return produtoRepository.existsById(id);
    }

    public boolean existsByNome(String nome){
        return produtoRepository.existsByNome(nome);
    }

    public boolean existsByData(String data){
        return produtoRepository.existsByData(data);
    }

    public boolean existsByHora (String hora){
        return produtoRepository.existsByHora(hora);
    }





}
