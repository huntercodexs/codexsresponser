package huntercodexs.postalcode.controller;

import huntercodexs.postalcode.dto.PostalCodeRequestDto;
import huntercodexs.postalcode.dto.PostalCodeResponseDto;
import huntercodexs.postalcode.service.PostalCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class PostalCodeController {

    @Autowired
    PostalCodeService postalCodeService;

    @PostMapping(path = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PostalCodeResponseDto> getAddress(
            @Valid @RequestBody(required = true) PostalCodeRequestDto addressRequestDto
    ) throws Exception {
        return postalCodeService.getAddress(addressRequestDto);
    }

}
