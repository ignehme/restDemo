package com.restdemo.ports.out;

import java.util.Optional;

/**
 * RepositoryBase interface.
 * @param <T> entity
 * @param <K> identifier
 */
public interface PersistenceBase<T, K> {

  /**
   * find by identifier.
   * @param id identifier
   * @return entity
   */
  Optional<T> findById(K id);

  /**
   * find all.
   * @param <S> iterable
   * @return list of entities
   */
  <S extends T> Iterable<S> findAll();

  /**
   * save entity.
   * @param element entity
   * @param <S> entity
   * @return entity
   */
  <S extends T> S save(S element);

  /**
   * save list of entities.
   * @param elements entities
   * @param <S> iterable
   * @return list of entities
   */
  <S extends T> Iterable<S> save(Iterable<S> elements);

  /**
   * delete entity.
   * @param element entity
   */
  void delete(T element);

  /**
   * delete by identifier.
   * @param id identifier
   */
  void deleteById(K id);

  /**
   * detele all entities.
   * @param elements entities
   */
  void deleteAll(Iterable<T> elements);

  /**
   * check entity exists by identifier.
   * @param id identifier
   * @return boolean
   */
  boolean existsById(K id);

  /**
   * check entity exists.
   * @param element entity
   * @return boolean
   */
  boolean exists(T element);
}
