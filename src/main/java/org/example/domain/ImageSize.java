package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageSize {
    private String url;
    private String width;
    private String height;
    private String size;
    private String mp4;
    private String mp4_size;
    private String webp;
    private String webp_size;
    private String frames;
}
