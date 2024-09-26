package com.sds.newbie.sns.repository;

import com.sds.newbie.sns.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
}
