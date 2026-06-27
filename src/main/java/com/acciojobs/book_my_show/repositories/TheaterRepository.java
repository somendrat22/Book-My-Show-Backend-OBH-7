package com.acciojobs.book_my_show.repositories;

import com.acciojobs.book_my_show.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, UUID> {
}
