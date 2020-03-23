package com.humane.repository;

import com.humane.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ashish on 23/03/20.
 */
@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
}
