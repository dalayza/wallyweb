package com.b2w.tax.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.b2w.tax.model.database.Item;

public interface ItemRepository
        extends JpaRepository<Item, BigInteger>, JpaSpecificationExecutor<BigInteger> {

    public Item findByItemIdItem(BigInteger itemIdItem);

}
