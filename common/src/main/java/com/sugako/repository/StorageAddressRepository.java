package com.sugako.repository;


import com.sugako.domain.StorageAddress;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


@Cacheable("storage_address")
public interface StorageAddressRepository extends
        JpaRepository<StorageAddress, Long> {
}
