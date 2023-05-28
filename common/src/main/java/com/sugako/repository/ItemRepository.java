package com.sugako.repository;

import com.sugako.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends
        JpaRepository<Item, Long> {


}
