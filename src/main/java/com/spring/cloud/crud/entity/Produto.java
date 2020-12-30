package com.spring.cloud.crud.entity;

import com.spring.cloud.crud.data.vo.ProdutoVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

    private static final long serialVersionUID = 5591572757372251665L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 10, nullable = false)
    private Integer estoque;

    @Column(length = 10, nullable = false)
    private Double preco;

    public static Produto convertToProduto(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
