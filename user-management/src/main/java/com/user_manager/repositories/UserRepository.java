package com.user_manager.repositories;

import com.user_manager.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// TODO 7 ~ Make QUERY to Save Data in Database MYSQL
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USERS(firstName, lastName, email, password) VALUES(:firstName, :lastName, :email, :password)", nativeQuery = true)
    int registerNewuser(@Param("firstName") String firstName,
                        @Param("lastName") String lastName,
                        @Param("email") String email,
                        @Param("password") String password
    );
}
