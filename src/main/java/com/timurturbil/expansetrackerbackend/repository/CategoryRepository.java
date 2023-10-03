package com.timurturbil.expansetrackerbackend.repository;
import com.timurturbil.expansetrackerbackend.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findById(long id);
}
