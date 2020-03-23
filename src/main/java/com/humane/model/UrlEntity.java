package com.humane.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.humane.enums.VisitedStatus;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by ashish on 14/03/20.
 */
@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlString;
    @Enumerated(EnumType.STRING)
    private VisitedStatus isVisited = VisitedStatus.NOT_VISITED;


    @Override
    public String toString() {
        return String.format("UrlEntity{urlString=%s, isVisited=%s}", getUrlString(), getIsVisited());
    }
}
