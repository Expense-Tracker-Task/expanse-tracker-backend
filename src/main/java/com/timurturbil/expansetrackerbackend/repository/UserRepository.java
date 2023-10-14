package com.timurturbil.expansetrackerbackend.repository;
import com.timurturbil.expansetrackerbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findById(long id);

    User findByUsername(String username);

    void deleteById(long id);

}
