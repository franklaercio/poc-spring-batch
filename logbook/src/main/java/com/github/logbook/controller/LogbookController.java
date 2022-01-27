package com.github.logbook.controller;

import com.github.logbook.entities.GitHubProfile;
import com.github.logbook.gateway.LogbookGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class LogbookController {

    private final LogbookGateway logbookGateway;

    public LogbookController(LogbookGateway logbookGateway) {
        this.logbookGateway = logbookGateway;
    }

    @GetMapping
    public ResponseEntity<GitHubProfile> getGitHubProfile() {
        return new ResponseEntity<>(this.logbookGateway.findProfile(), HttpStatus.OK);
    }
}
