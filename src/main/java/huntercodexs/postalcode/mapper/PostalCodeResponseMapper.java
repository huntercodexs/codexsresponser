package huntercodexs.postalcode.mapper;

import huntercodexs.postalcode.dto.PostalCodeResponseDto;
import huntercodexs.postalcode.database.model.PostalCodeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostalCodeResponseMapper {

    public static PostalCodeResponseDto mapperInitialResponseDto() {
        return new PostalCodeResponseDto();
    }

    public static PostalCodeResponseDto mapperFinalResponseDtoByNew(PostalCodeResponseDto addressResponseDto) {
        return new PostalCodeResponseDto();
    }

    public static PostalCodeResponseDto mapperFinalResponseDtoByEntity(PostalCodeEntity postalCodeEntity) {
        PostalCodeResponseDto addressResponseDto = new PostalCodeResponseDto();
        addressResponseDto.setCep(postalCodeEntity.getCep());
        addressResponseDto.setLogradouro(postalCodeEntity.getLogradouro());
        addressResponseDto.setComplemento(postalCodeEntity.getComplemento());
        addressResponseDto.setBairro(postalCodeEntity.getBairro());
        addressResponseDto.setLocalidade(postalCodeEntity.getLocalidade());
        addressResponseDto.setUf(postalCodeEntity.getUf());
        addressResponseDto.setIbge(postalCodeEntity.getIbge());
        addressResponseDto.setGia(postalCodeEntity.getGia());
        addressResponseDto.setDdd(postalCodeEntity.getDdd());
        addressResponseDto.setSiafi(postalCodeEntity.getSiafi());
        return addressResponseDto;
    }

}
