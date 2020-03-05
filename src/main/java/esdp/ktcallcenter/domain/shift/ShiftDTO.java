package esdp.ktcallcenter.domain.shift;

import lombok.*;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
public class ShiftDTO {

        private Integer id;
        @NotBlank
        private String name;


        static ShiftDTO from(Shift shift) {
            return builder()
                    .id(shift.getId())
                    .name(shift.getName())
                    .build();

        }
}
