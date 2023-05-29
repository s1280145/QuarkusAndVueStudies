package com.example.resource;

import com.example.entity.Item;
import com.example.repository.ItemRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class ItemResource {
    @Inject
    ItemRepository itemRepository;

    @GET
    @Path("{id}")
    public Item getItem(@PathParam("id") Long id) {
        return itemRepository.findById(id);
    }

    @GET
    public List<Item> getItemList() {
        return itemRepository.listAll();
    }

    @POST
    public void createItem(Item item) { itemRepository.persist(item); }

    @PUT
    @Path("{id}")
    public void updateItem(@PathParam("id") Long id, Item item) {
        Item updateItem = itemRepository.findById(id);
        updateItem.setName(item.getName());
        updateItem.setPrice(item.getPrice());
        itemRepository.persist(updateItem);
    }

    @DELETE
    @Path("{id}")
    public void deleteItem(@PathParam("id") Long id) {
        itemRepository.deleteById(id);
    }
}
