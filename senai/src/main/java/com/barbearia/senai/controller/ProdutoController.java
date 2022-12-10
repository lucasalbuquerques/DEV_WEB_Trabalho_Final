package com.barbearia.senai.controller;

import com.barbearia.senai.dto.Mensagem;
import com.barbearia.senai.dto.ProdutoDto;
import com.barbearia.senai.entity.Produto;
import com.barbearia.senai.service.ProdutoService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatCodePointException;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin(origins = "http://localhost:4200")


public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Produto>>List(){
        List<Produto> list = produtoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") int id){
        if(!produtoService.existsById(id))
            return new ResponseEntity(new Mensagem("Não existe"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getOne(id).get();
        return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nome}")
    public ResponseEntity<Produto> getByNome(@PathVariable("nome") String nome){
        if(!produtoService.existsByNome(nome))
            return new ResponseEntity(new Mensagem("Não existe"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getBynome(nome).get();
        return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProdutoDto produtoDto){
        if(StringUtils.isBlank(produtoDto.getNome()))
            return new ResponseEntity(new Mensagem("Nome é um campo obrigatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(produtoDto.getServico()))
            return new ResponseEntity(new Mensagem("Servico é um campo obrigatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(produtoDto.getData()))
            return new ResponseEntity(new Mensagem("Data é um campo obrigatorio"), HttpStatus.BAD_REQUEST);

        if (produtoService.existsByNome(produtoDto.getNome()))
            return new ResponseEntity(new Mensagem("Esse nome ja existe"), HttpStatus.BAD_REQUEST);
        if (produtoService.existsByData(produtoDto.getData()) && produtoService.existsByHora(produtoDto.getHora()) )
            return new ResponseEntity(new Mensagem("Ja existe um horario marcado"), HttpStatus.BAD_REQUEST);
        Produto produto = new Produto(produtoDto.getNome(), produtoDto.getServico(),produtoDto.getData(), produtoDto.getHora());
        produtoService.save(produto);
        return new ResponseEntity(new Mensagem("Agendamento salvo com sucesso"), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProdutoDto produtoDto){
        if(!produtoService.existsById(id))
            return new ResponseEntity(new Mensagem("Não existe"), HttpStatus.NOT_FOUND);
        if (produtoService.existsByNome(produtoDto.getNome()) && produtoService.getBynome(produtoDto.getNome()).get().getId() !=id)
            return new ResponseEntity(new Mensagem("Esse nome ja existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(produtoDto.getNome()))
            return new ResponseEntity(new Mensagem("Nome é um campo obrigatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(produtoDto.getServico()))
            return new ResponseEntity(new Mensagem("Servico é um campo obrigatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(produtoDto.getData()))
            return new ResponseEntity(new Mensagem("Data é um campo obrigatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(produtoDto.getHora()))
            return new ResponseEntity(new Mensagem("Hora é um campo obrigatorio"), HttpStatus.BAD_REQUEST);

        Produto produto = produtoService.getOne(id).get();
        produto.setNome(produtoDto.getNome());
        produto.setServico(produtoDto.getServico());
        produto.setData(produtoDto.getData());
        produto.setHora(produtoDto.getHora());
        produtoService.save(produto);
        return new ResponseEntity(new Mensagem("Agendamento atualizado com sucesso"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!produtoService.existsById(id))
            return new ResponseEntity(new Mensagem("Não existe"), HttpStatus.NOT_FOUND);
        produtoService.delete(id);
        return new ResponseEntity(new Mensagem("Agendamento deletado"), HttpStatus.OK);

    }

}
