package com.github.logbook.gateway;

import com.github.logbook.entities.GitHubProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LogbookGateway {

    private static final String URL_PROFILE = "https://api.github.com/users/franklaercio";

    private final RestTemplate restTemplate;

    public LogbookGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GitHubProfile findProfile() {
        return restTemplate
                .getForObject(URL_PROFILE, GitHubProfile.class);
    }
}
