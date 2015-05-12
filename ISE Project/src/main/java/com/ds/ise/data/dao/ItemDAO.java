package com.ds.ise.data.dao;


import com.ds.ise.entity.Item;

import javax.ejb.Stateless;

@Stateless
public class ItemDAO extends CommonDAO<Item> {

    public static final String REQUESTS_FIELD = "requests";

    public ItemDAO() {
        super(Item.class);
    }

    public void delete(Item item) {
        super.delete(item.getId(), Item.class);
    }

    public long getTotalRequestsNumber() {
        return getSumOfColumn(REQUESTS_FIELD);
    }
}
