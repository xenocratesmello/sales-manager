/**
 * 
 */
package com.baozistore.sales_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baozistore.sales_manager.model.Pedido;
import com.baozistore.sales_manager.repository.PedidoRepository;

/**
 * 
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private PedidoRepository repository;
	/**
	 * 
	 */
	public PedidoController(PedidoRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<?> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Pedido create(@RequestBody Pedido pedido) {
		return repository.save(pedido);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Pedido pedido) {
		return repository.findById(id).map(record -> {
			record.setCliente(pedido.getCliente());
			record.setProduto(pedido.getProduto());
			record.setQuantidade(pedido.getQuantidade());
			Pedido updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(_ -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
