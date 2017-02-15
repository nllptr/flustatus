package com.github.nllptr.service;

import com.github.nllptr.model.FluStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexScrapeService {

    static Logger logger = LoggerFactory.getLogger(RegexScrapeService.class);
    private String url;
    private Document doc;
    private Pattern pattern;
    private LocalDateTime lastFetch;
    private String currentResult;

    @Autowired
    public RegexScrapeService(@Value("${scraper.url}") String url, @Value("${scraper.regex}") String regex) {
        this.url = url;
        this.pattern = Pattern.compile(regex);
        fetch();
        scrape();
    }

    public boolean fetch() {

        try {
            this.doc = Jsoup.connect(url).get();
            this.lastFetch = LocalDateTime.now();
        } catch (IOException e) {
            logger.error("An error occurred while fetching URL", e);
            return false;
        }
        return true;
    }

    public String scrape() {
        Elements elements = doc.getElementsByClass("pagecontent");
        Optional<String> scrapings = elements.stream()
                .map(element -> element.text())
                .map(s -> {
                    Matcher m = pattern.matcher(s);
                    while(m.find()) {
                        s = m.group();
                    }
                    return s;
                })
                .findFirst();
        if (scrapings.isPresent()) {
            currentResult = scrapings.get();
        } else {
            currentResult = "";
        }
        return currentResult;
    }

    public FluStatus getStatus() {
        if(lastFetch == null || currentResult == null) {
            fetch();
            scrape();
        }
        if(lastFetch.plusHours(1).isBefore(LocalDateTime.now())) {
            logger.debug("An hour has passed since last fetch. Fetching again.");
            fetch();
            scrape();
        }
        return new FluStatus(currentResult, lastFetch);
    }

    public String getUrl() {
        return this.url;
    }

    public void clear() {
        lastFetch = null;
        currentResult = null;
    }
}
