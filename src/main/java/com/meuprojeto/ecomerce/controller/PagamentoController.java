package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.exception.PedidoNaoEncontradoException;
import com.meuprojeto.ecomerce.model.Pagamento;
import com.meuprojeto.ecomerce.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {
}


