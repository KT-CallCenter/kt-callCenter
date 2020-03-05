package esdp.ktcallcenter.domain.language;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
public class LanguageDTO {

        private Integer id;
        @NotBlank
        private String name;


        static LanguageDTO from(Language language){
            return builder()
                    .id(language.getId())
                    .name(language.getName())
                    .build();

        }
}
