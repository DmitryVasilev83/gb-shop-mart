package ru.gb.gbshopmart.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopmart.entity.Manufacturer;
import ru.gb.gbshopmart.entity.Category;

import java.util.Optional;


public interface CategoryDao extends JpaRepository<Category, Long> {
    Optional<Manufacturer> findByName(String name);
}