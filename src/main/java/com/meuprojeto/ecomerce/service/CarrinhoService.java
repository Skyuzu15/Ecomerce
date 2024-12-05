package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.*;
import com.meuprojeto.ecomerce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public void adicionarProdutoAoCarrinho(Long idCarrinho, Long idProduto, int quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        ItemCarrinho itemCarrinho = itemCarrinhoRepository
                .findByCarrinhoAndProduto(carrinho, produto)
                .orElseGet(() -> {
                    ItemCarrinho novoItem = new ItemCarrinho();
                    novoItem.setCarrinho(carrinho);
                    novoItem.setProduto(produto);
                    novoItem.setPrecoUnitario(produto.getPreco());
                    novoItem.setQuantidade(0);
                    return novoItem;
                });

        itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + quantidade);
        itemCarrinhoRepository.save(itemCarrinho);

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        produtoRepository.save(produto);
    }

    @Transactional
    public void atualizarQuantidadeItem(Long idItemCarrinho, int novaQuantidade) {
        ItemCarrinho itemCarrinho = itemCarrinhoRepository.findById(idItemCarrinho)
                .orElseThrow(() -> new RuntimeException("Item do carrinho não encontrado"));

        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        itemCarrinhoRepository.atualizarQuantidade(idItemCarrinho, novaQuantidade);
    }

    public boolean existsItemCarrinhoById(Long id) {
        return itemCarrinhoRepository.existsById(id);
    }

    public void deleteItemCarrinhoById(Long id) {
        itemCarrinhoRepository.deleteById(id);
    }

    public List<ItemCarrinho> listarItensDoCarrinho(Long idCarrinho) {
        return itemCarrinhoRepository.findByCarrinhoIdCarrinho(idCarrinho);
    }

    @Transactional
    public Pedidos realizarCheckout(Long idCarrinho) {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        BigDecimal totalCarrinho = calcularTotalCarrinho(idCarrinho);

        Pedidos pedido = new Pedidos();
        pedido.setCliente(carrinho.getCliente());
        pedido.setValorTotal(totalCarrinho.doubleValue());
        pedidosRepository.save(pedido);

        for (ItemCarrinho itemCarrinho : carrinho.getItensCarrinho()) {
            ItemPedidos itemPedido = new ItemPedidos();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemCarrinho.getProduto());
            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            BigDecimal precoUnitario = BigDecimal.valueOf(itemCarrinho.getPrecoUnitario());
            itemPedido.setPrecoUnitario(precoUnitario);

            itemPedidoRepository.save(itemPedido);
        }


        itemCarrinhoRepository.deleteById(idCarrinho);

        return pedido;
    }

    public BigDecimal calcularTotalCarrinho(Long idCarrinho) {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCarrinho itemCarrinho : itemCarrinhoRepository.findByCarrinhoIdCarrinho(idCarrinho)) {
            BigDecimal precoUnitario = BigDecimal.valueOf(itemCarrinho.getPrecoUnitario());
            BigDecimal quantidade = new BigDecimal(itemCarrinho.getQuantidade());

            BigDecimal itemTotal = precoUnitario.multiply(quantidade);

            total = total.add(itemTotal);
        }
        return total;
    }
}

