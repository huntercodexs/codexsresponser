package huntercodexs.postalcode.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostalCodeRequestDto {
    @NotNull @NotEmpty @NotBlank
    String rulesCode;

    @NotNull @NotEmpty @NotBlank
    String postalCode;

    String webhook;
}
