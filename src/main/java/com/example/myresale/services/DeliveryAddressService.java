package com.example.myresale.services;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.entities.DeliveryAddress;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.repositories.DeliveryAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryAddressService {
    private DeliveryAddressRepository repository;

    public DeliveryAddressService(DeliveryAddressRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DeliveryAddress addDeliveryAddress(DeliveryAddressCreateDTO dto, UserInfo user){
        DeliveryAddress address = DeliveryAddress.builder()
                .fullName(dto.getFullName())
                .city(dto.getCity())
                .street(dto.getStreet())
                .houseNumber(dto.getHouseNumber())
                .zip(dto.getZip())
                .phoneNumber(dto.getPhoneNumber())
                .createdBy(user)
                .build();


        return repository.save(address);
    }

    public List<DeliveryAddress> findAllById(long id){
        return repository.findAllById(List.of(id));
    }
}
