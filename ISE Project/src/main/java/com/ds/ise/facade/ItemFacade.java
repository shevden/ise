package com.ds.ise.facade;

import com.ds.ise.entity.Item;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ItemFacade {

    public abstract void save(Item item);

    public abstract Item update(Item item);

    public abstract void delete(Item item);

    public abstract Item find(int entityID);

    public abstract List<Item> findAll();
}
