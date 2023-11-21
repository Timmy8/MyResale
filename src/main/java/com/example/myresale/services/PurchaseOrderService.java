package com.example.myresale.services;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.entities.DeliveryAddress;
import com.example.myresale.entities.Item;
import com.example.myresale.entities.PurchaseOrder;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {
    private PurchaseOrderRepository repository;
    private ItemService itemService;
    private DeliveryAddressService deliveryAddressService;

    public PurchaseOrderService(PurchaseOrderRepository repository, ItemService itemService, DeliveryAddressService deliveryAddressService) {
        this.repository = repository;
        this.itemService = itemService;
        this.deliveryAddressService = deliveryAddressService;
    }

    public List<PurchaseOrder> findAllOrdersByUserId(Long userInfoId){
        return repository.findAllPurchaseOrderByUserInfoId(userInfoId);
    }
    @Transactional
    public Long saveOrder(Long itemId, DeliveryAddressCreateDTO dto, UserInfo user){
        Item item = itemService.findItemById(itemId);
        item.setAvailable(false);
        DeliveryAddress address = deliveryAddressService.addDeliveryAddress(dto, user);

        PurchaseOrder order = PurchaseOrder.builder()
                .item(item)
                .deliveryAddress(address)
                .userInfo(user)
                .build();

        repository.save(order);

        return order.getId();
    }
}
