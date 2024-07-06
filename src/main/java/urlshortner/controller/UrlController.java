package urlshortner.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import urlshortner.model.Url;
import urlshortner.repository.UrlRepository;

@RestController
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String longUrl) {
        String shortUrl = generateShortUrl();
        LocalDateTime expiresAt = LocalDateTime.now().plusMonths(10);

        Url url = new Url(shortUrl, longUrl, LocalDateTime.now(), expiresAt);
        urlRepository.save(url);

        return ResponseEntity.ok("http://localhost:8080/" + shortUrl);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateShortUrl(@RequestBody Map<String, String> request) {
        String shortUrl = request.get("shortUrl");
        String newLongUrl = request.get("longUrl");

        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            url.setLongUrl(newLongUrl);
            urlRepository.save(url);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @GetMapping("/{shortenString}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString) throws IOException {
        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortenString);
        if (urlOptional.isPresent()) {
            response.sendRedirect(urlOptional.get().getLongUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping("/update_expiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestBody Map<String, Object> request) {
        String shortUrl = (String) request.get("shortUrl");
        int daysToAdd = (Integer) request.get("daysToAdd");

        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            url.setExpiresAt(url.getExpiresAt().plusDays(daysToAdd));
            urlRepository.save(url);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    private String generateShortUrl() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
