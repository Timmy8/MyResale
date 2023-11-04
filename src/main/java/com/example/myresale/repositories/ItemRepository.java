package com.example.myresale.repositories;


import com.example.myresale.entities.Item;
import com.example.myresale.entities.RequestItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteItemById(long id);
}
