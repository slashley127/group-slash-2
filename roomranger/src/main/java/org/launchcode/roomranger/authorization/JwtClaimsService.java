package org.launchcode.roomranger.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JwtClaimsService {
    private final ObjectMapper objectMapper;

    public JwtClaimsService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JwtClaims parseJwtClaims(String jwtToken) throws IOException {
        String claimsJson = jwtToken.split("\\.")[1];
        String decodedClaims = new String(java.util.Base64.getUrlDecoder().decode(claimsJson));
        return objectMapper.readValue(decodedClaims, JwtClaims.class);
    }
}

