package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GIF {
    private String type;
    private String id;
    private String slug;
    private String url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String rating;
    private User user;
    private String source_post_url;
    private String update_datetime;
    private String create_datetime;
    private String import_datetime;
    private String trending_datetime;
    private Images images;
    private String title;
}
