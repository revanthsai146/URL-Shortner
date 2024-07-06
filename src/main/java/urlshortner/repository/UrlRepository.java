package urlshortner.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import urlshortner.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
}

