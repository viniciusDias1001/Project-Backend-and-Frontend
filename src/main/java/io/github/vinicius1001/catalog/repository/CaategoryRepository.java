package io.github.vinicius1001.catalog.repository;

import io.github.vinicius1001.catalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaategoryRepository extends JpaRepository<Category,Long> {

}
