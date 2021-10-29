package com.restdemo.domain.mapper;

/**
 * Mapper base class.
 * @param <D> domain object
 * @param <A> adapter object
 */
public interface MapperBase<D, A> {

  /**
   * Map adapter to domain object.
   * @param adapterObject adapter object
   * @return domain object
   */
  D toDomain(A adapterObject);

  /**
   * Map domain to adapter object.
   * @param domainEntity domain object
   * @return infrastructure object
   */
  A toAdapter(D domainEntity);
}
