/**
 * 
 */
package com.baozistore.sales_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baozistore.sales_manager.model.Produto;

/**
 * 
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
