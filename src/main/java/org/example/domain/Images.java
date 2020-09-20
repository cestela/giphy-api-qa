package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    private ImageSize fixed_height;
    private ImageSize fixed_height_still;
    private ImageSize fixed_height_downsampled;
    private ImageSize fixed_width;
    private ImageSize fixed_width_still;
    private ImageSize fixed_width_downsampled;
    private ImageSize fixed_height_small;
    private ImageSize fixed_height_small_still;
    private ImageSize fixed_width_small;
    private ImageSize fixed_width_small_still;
    private ImageSize downsized;
    private ImageSize downsized_still;
    private ImageSize downsized_large;
    private ImageSize downsized_medium;
    private ImageSize downsized_small;
    private ImageSize original;
    private ImageSize original_still;
    private ImageSize looping;
    private ImageSize preview;
    private ImageSize preview_gif;

}
