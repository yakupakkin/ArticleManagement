package com.article.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private String title;
    private String author;
    private String content;
    private LocalDateTime publishingDate;
}
