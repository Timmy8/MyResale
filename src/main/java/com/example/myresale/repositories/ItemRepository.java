package com.example.myresale.repositories;


import com.example.myresale.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteItemById(long id);

    @Query("SELECT i FROM Item i WHERE i.name LIKE %?1% OR i.author LIKE %?1%")
    List<Item> findALlItemsByKeyword(String keyword);
}
