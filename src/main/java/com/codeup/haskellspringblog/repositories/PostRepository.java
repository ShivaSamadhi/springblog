package com.codeup.haskellspringblog.repositories;

import com.codeup.haskellspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
