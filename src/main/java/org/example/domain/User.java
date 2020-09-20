package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String avatar_url;
    private String banner_url;
    private String profile_url;
    private String username;
    private String display_name;
}
