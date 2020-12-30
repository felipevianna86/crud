package com.spring.cloud.crud.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.cloud.crud.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@JsonPropertyOrder({"id", "nome", "estoque", "preco"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVO implements Serializable {

    private static final long serialVersionUID = -7294470821111409371L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private Double preco;

    public static ProdutoVO convertProduto(Produto produto){
        return new ModelMapper().map(produto, ProdutoVO.class);
    }
}
