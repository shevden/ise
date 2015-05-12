package com.ds.ise.facade;

import com.ds.ise.data.dao.ItemDAO;
import com.ds.ise.entity.Item;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ItemFacadeImpl implements ItemFacade {

    @EJB
    private ItemDAO itemDAO;

    public void save(Item item) {
        itemDAO.save(item);
    }

    public Item update(Item item) {
        return itemDAO.update(item);
    }

    public void delete(Item item) {
        itemDAO.delete(item);
    }

    public Item find(int entityID) {
        return itemDAO.find(entityID);
    }

    public List<Item> findAll() {
        return itemDAO.findAll();
    }
}
