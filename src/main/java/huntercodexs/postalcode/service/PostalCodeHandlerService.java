package huntercodexs.postalcode.service;

import huntercodexs.postalcode.codexsresponser.exception.CodexsResponserException;
import huntercodexs.postalcode.client.PostalCodeClient;
import huntercodexs.postalcode.database.repository.PostalCodeRepository;
import huntercodexs.postalcode.dto.PostalCodeResponseDto;
import huntercodexs.postalcode.mapper.PostalCodeResponseMapper;
import huntercodexs.postalcode.database.model.PostalCodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static huntercodexs.postalcode.codexsresponser.settings.CodexsResponserSettings.codexsResponserExpectedErrors.SERVICE_ERROR_NOT_FOUND;

@Service
@Slf4j
public class PostalCodeHandlerService {

    @Autowired
    PostalCodeClient postalCodeClient;

    @Autowired
    PostalCodeRepository postalCodeRepository;

    public PostalCodeResponseDto searchPostalCode(String postalCode) throws Exception {

        //if (1 == 1) {
            //throw new RuntimeException("This is a test in codexs reponser");
            //throw new CodexsResponserException(SERVICE_ERROR_TEST);
        //}

        PostalCodeEntity address = postalCodeRepository.findByCep(postalCode);

        if (address != null && !address.getCep().equals("")) {
            return PostalCodeResponseMapper.mapperFinalResponseDtoByEntity(address);
        }

        ResponseEntity<PostalCodeResponseDto> result = postalCodeClient.addressSearch(postalCode);

        if (result == null || result.getBody().getCep() == null) {
            throw new CodexsResponserException(SERVICE_ERROR_NOT_FOUND);
        }

        if (!result.getStatusCode().is4xxClientError()) {
            saveAddress(result);
            return result.getBody();
        }

        throw new CodexsResponserException(SERVICE_ERROR_NOT_FOUND);
    }

    public void saveAddress(ResponseEntity<PostalCodeResponseDto> result) {
        PostalCodeEntity postalCodeEntity = new PostalCodeEntity();
        try {
            postalCodeEntity.setCep(Objects.requireNonNull(result.getBody()).getCep().replace("-", ""));
            postalCodeEntity.setLogradouro(Objects.requireNonNull(result.getBody()).getLogradouro());
            postalCodeEntity.setComplemento(Objects.requireNonNull(result.getBody()).getComplemento());
            postalCodeEntity.setBairro(Objects.requireNonNull(result.getBody()).getBairro());
            postalCodeEntity.setLocalidade(Objects.requireNonNull(result.getBody()).getLocalidade());
            postalCodeEntity.setUf(Objects.requireNonNull(result.getBody()).getUf());
            postalCodeEntity.setIbge(Objects.requireNonNull(result.getBody()).getIbge());
            postalCodeEntity.setGia(Objects.requireNonNull(result.getBody()).getGia());
            postalCodeEntity.setDdd(Objects.requireNonNull(result.getBody()).getDdd());
            postalCodeEntity.setSiafi(Objects.requireNonNull(result.getBody()).getSiafi());
            postalCodeRepository.save(postalCodeEntity);
        } catch (RuntimeException re) {
            System.out.println("saveAddress EXCEPTION: " + re.getMessage());
        }
    }
}
