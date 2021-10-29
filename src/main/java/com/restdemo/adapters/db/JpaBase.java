package com.restdemo.adapters.db;

import com.restdemo.adapters.db.dao.SpringDataGenericRepository;
import com.restdemo.domain.mapper.MapperBase;
import com.restdemo.ports.out.PersistenceBase;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public abstract class JpaBase<D, A, K> implements PersistenceBase<D, K> {

  protected abstract SpringDataGenericRepository<A, K> getRepository();

  protected abstract MapperBase<D, A> getMapper();

  @Override
  public Optional<D> findById(K id) {
    return getRepository().findById(id).map(getMapper()::toDomain);
  }

  @Override
  public <S extends D> Iterable<S> findAll() {
    return mapInfrastructureIterableToDomain(getRepository().findAll());
  }

  @Override
  public <S extends D> Iterable<S> save(Iterable<S> elements) {
    return (Iterable<S>) StreamSupport.stream(elements.spliterator(), false)
        .map(element -> getMapper().toDomain(getRepository().save(getMapper().toAdapter(element)))).collect(
            Collectors.toList());
  }

  @Override
  public <S extends D> S save(S element) {
    return (S) getMapper().toDomain(getRepository().saveAndFlush(getMapper().toAdapter(element)));
  }

  @Override
  public void deleteById(K id) {
    getRepository().deleteById(id);
  }

  @Override
  public void delete(D element) {
    getRepository().delete(getMapper().toAdapter(element));
  }

  @Override
  public void deleteAll(Iterable<D> elements) {
    StreamSupport.stream(elements.spliterator(), false)
        .forEach(element -> getRepository().save(getMapper().toAdapter(element)));
  }

  @Override
  public boolean existsById(K id) {
    return getRepository().existsById(id);
  }

  @Override
  public boolean exists(D element) {
    return getRepository().exists(Example.of(getMapper().toAdapter(element)));
  }

  private <S extends D> Iterable<S> mapInfrastructureIterableToDomain(Iterable<A> iterable) {
    return (Iterable<S>) StreamSupport.stream(iterable.spliterator(), false)
        .map(getMapper()::toDomain).collect(Collectors.toList());
  }

}
