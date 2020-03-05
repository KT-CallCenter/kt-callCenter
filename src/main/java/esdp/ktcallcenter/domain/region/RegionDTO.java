package esdp.ktcallcenter.domain.region;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)

public class RegionDTO {

    private Integer id;
    @NotBlank
    private String name;


    static RegionDTO from(Region region) {
        return builder()
                .id(region.getId())
                .name(region.getName())
                .build();

    }
}
