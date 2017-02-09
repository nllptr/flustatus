package com.github.nllptr.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexScraper {

    static Logger logger = LoggerFactory.getLogger(RegexScraper.class);
    private String url;
    private Document doc;
    private Pattern pattern;

    @Autowired
    public RegexScraper(@Value("${scraper.url}") String url, @Value("${scraper.regex}") String regex) {
        this.url = url;
        this.pattern = Pattern.compile(regex);
    }

    public boolean fetch() {

        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.error("An error occurred while fetching URL", e);
            return false;
        }
        return true;
    }

    public String scrape() {
        Elements elements = doc.getElementsByClass("pagecontent");
        Optional<String> scrapings = elements.stream()
//                .map(element -> element.getElementsByTag("h2"))
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
            return scrapings.get();
        } else {
            return "";
        }
    }

}
