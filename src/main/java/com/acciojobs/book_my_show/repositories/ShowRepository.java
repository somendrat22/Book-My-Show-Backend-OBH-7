package com.acciojobs.book_my_show.repositories;

import com.acciojobs.book_my_show.models.Hall;
import com.acciojobs.book_my_show.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {

    @Query(value = "select * from shows where hall_sys_id =:hallSysId", nativeQuery = true)
    public List<Show> getAllShowsByHall(String hallSysId);

    public List<Show> findByHall(Hall hall);

}
