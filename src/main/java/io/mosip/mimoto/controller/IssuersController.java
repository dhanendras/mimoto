package io.mosip.mimoto.controller;

import io.mosip.mimoto.core.http.ResponseWrapper;
import io.mosip.mimoto.dto.ErrorDTO;
import io.mosip.mimoto.dto.IssuerDTO;
import io.mosip.mimoto.dto.IssuersDTO;
import io.mosip.mimoto.exception.PlatformErrorMessages;
import io.mosip.mimoto.service.IssuersService;
import io.mosip.mimoto.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/issuers")
public class IssuersController {
    @Autowired
    IssuersService issuersService;

    @GetMapping()
    public ResponseEntity<Object> getAllIssuers() {
        ResponseWrapper<IssuersDTO> responseWrapper = new ResponseWrapper<>();
        //TODO: Modify id
        responseWrapper.setId("mosip.inji.properties");
        responseWrapper.setVersion("v1");
        responseWrapper.setResponsetime(DateUtils.getRequestTimeString());
        responseWrapper.setResponse(issuersService.getAllIssuers());

        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }

    @GetMapping("/{issuer-id}")
    public ResponseEntity<Object> getIssuerConfig(@PathVariable("issuer-id") String issuerId) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        //TODO: Modify id
        responseWrapper.setId("mosip.inji.properties");
        responseWrapper.setVersion("v1");
        responseWrapper.setResponsetime(DateUtils.getRequestTimeString());


        IssuerDTO issuerConfig = issuersService.getIssuerConfig(issuerId);
        responseWrapper.setResponse(issuerConfig);
        if (issuerConfig == null) {
            responseWrapper.setErrors(List.of(new ErrorDTO(PlatformErrorMessages.INVALID_ISSUER_ID_EXCEPTION.getCode(), PlatformErrorMessages.INVALID_ISSUER_ID_EXCEPTION.getMessage())));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWrapper);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }

}
