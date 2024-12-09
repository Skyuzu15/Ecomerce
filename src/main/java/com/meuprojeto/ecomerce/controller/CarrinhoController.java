package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.ItemCarrinho;
import com.meuprojeto.ecomerce.model.Pedidos;
import com.meuprojeto.ecomerce.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {
}

