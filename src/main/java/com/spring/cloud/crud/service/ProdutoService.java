package com.spring.cloud.crud.service;

import com.spring.cloud.crud.data.vo.ProdutoVO;
import com.spring.cloud.crud.entity.Produto;
import com.spring.cloud.crud.exception.ResourceNotFoundException;
import com.spring.cloud.crud.message.ProdutoSendMessage;
import com.spring.cloud.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoSendMessage produtoSendMessage;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage){
        this.produtoRepository = produtoRepository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoVO create(ProdutoVO produtoVO){
        Produto produto = produtoRepository.save(converterToProduto(produtoVO));
        ProdutoVO produtoVOCreated = converterToProdutoVO(produto);
        produtoSendMessage.sendMessage(produtoVOCreated);
        return produtoVOCreated;
    }

    public Page<ProdutoVO> findAll(Pageable pageable){
        var page = produtoRepository.findAll(pageable);
        return page.map(this::converterToProdutoVO);
    }

    public ProdutoVO findById(Long id){
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return converterToProdutoVO(entity);
    }

    public ProdutoVO update(ProdutoVO produtoVO){
        final Optional<Produto> optionalProduto = produtoRepository.findById(produtoVO.getId());

        if(!optionalProduto.isPresent()){
            new ResourceNotFoundException("No records found for this ID");
        }

        Produto produto = produtoRepository.save(converterToProduto(produtoVO));

        return converterToProdutoVO(produto);
    }

    public void delete(Long id){
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        produtoRepository.delete(entity);
    }

    private ProdutoVO converterToProdutoVO(Produto produto){
        return ProdutoVO.convertToProdutoVO(produto);
    }

    private Produto converterToProduto(ProdutoVO produtoVO){
        return Produto.convertToProduto(produtoVO);
    }
}
