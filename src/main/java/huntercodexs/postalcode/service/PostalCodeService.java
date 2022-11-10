package huntercodexs.postalcode.service;

import huntercodexs.postalcode.dto.PostalCodeRequestDto;
import huntercodexs.postalcode.dto.PostalCodeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostalCodeService {

    @Autowired
    PostalCodeHandlerService postalCodeHandlerService;

    public ResponseEntity<PostalCodeResponseDto> getAddress(PostalCodeRequestDto postalCodeRequestDto) throws Exception {
        PostalCodeResponseDto postalCodeResponseDto = postalCodeHandlerService.searchPostalCode(postalCodeRequestDto.getPostalCode());
        return ResponseEntity.status(HttpStatus.OK).body(postalCodeResponseDto);
    }

}
