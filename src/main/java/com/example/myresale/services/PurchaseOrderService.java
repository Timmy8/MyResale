package com.example.myresale.services;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.entities.DeliveryAddress;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.PurchaseOrder;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ItemService itemService;
    private final DeliveryAddressService deliveryAddressService;

    public PurchaseOrderService(PurchaseOrderRepository repository, ItemService itemService, DeliveryAddressService deliveryAddressService) {
        this.repository = repository;
        this.itemService = itemService;
        this.deliveryAddressService = deliveryAddressService;
    }

    public List<PurchaseOrder> findAllOrdersByUserId(Long userInfoId){
        return repository.findAllPurchaseOrderByUserInfoId(userInfoId);
    }
    @Transactional
    public Optional<Long> saveOrder(Set<Item> items, DeliveryAddressCreateDTO dto, UserInfo user){
        Set<Item> itemsSet = new HashSet<>();
        for (Item item : items)
            if (itemService.isAvailable(item.getId())){
                itemService.setItemAvailable(false, item.getId());
                itemsSet.add(itemService.findItemById(item.getId()));
            }

        if (itemsSet.isEmpty())
            return Optional.empty();

        DeliveryAddress address = deliveryAddressService.addDeliveryAddress(dto, user);

        PurchaseOrder order = PurchaseOrder.builder()
                .items(itemsSet)
                .deliveryAddress(address)
                .userInfo(user)
                .build();

        repository.save(order);

        return Optional.of(order.getId());
    }
}
