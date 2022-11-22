package com.codeup.haskellspringblog.repositories;

import com.codeup.haskellspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiitory extends JpaRepository<User, Long> {
}
