package com.humane.repository;

import com.humane.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish on 14/03/20..
 */
@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    List<UrlEntity> findByUrlString(String urlString);

//    public List<UrlEntity>

}
