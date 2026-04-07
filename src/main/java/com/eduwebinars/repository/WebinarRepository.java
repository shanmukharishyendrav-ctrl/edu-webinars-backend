package com.eduwebinars.repository;

import com.eduwebinars.entity.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebinarRepository extends JpaRepository<Webinar, Long> {

    List<Webinar> findByCategory(String category);

    List<Webinar> findByInstructor(String instructor);

    List<Webinar> findByTitleContainingIgnoreCase(String title);

}
