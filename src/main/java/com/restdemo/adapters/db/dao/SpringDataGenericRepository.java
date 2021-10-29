package com.restdemo.adapters.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Class SpringDataGenericRepository.
 * @param <T> entity
 * @param <K> identifier
 */
@NoRepositoryBean
public interface SpringDataGenericRepository<T, K> extends JpaRepository<T, K> {

}
