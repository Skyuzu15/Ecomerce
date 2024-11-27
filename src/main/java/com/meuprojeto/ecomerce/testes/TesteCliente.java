package com.meuprojeto.ecomerce.testes;

import com.meuprojeto.ecomerce.model.Cliente;
import com.meuprojeto.ecomerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TesteCliente implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void run(String... args) throws Exception {
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "123456789");
        clienteService.salvar(cliente);

        System.out.println("Clientes cadastrados:");
        clienteService.listarTodos().forEach(System.out::println);
    }
}
