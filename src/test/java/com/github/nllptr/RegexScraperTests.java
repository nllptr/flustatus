package com.github.nllptr;

import com.github.nllptr.service.RegexScrapeService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexScraperTests {

    private static final String goodUrl = "http://google.com";
    private static final String badUrl = "ftp://thisurlwillfailso.hard";

    @Test
    public void fetchTestSuccess() {
        RegexScrapeService scraper = new RegexScrapeService(goodUrl, "");
        assertThat(scraper.fetch()).isEqualTo(true);
    }

    @Test
    public void fetchTestFail() {
//        RegexScrapeService scraper = new RegexScrapeService(badUrl, "");
//        assertThat(scraper.fetch()).isEqualTo(false);
    }

    //TODO: Can I test scrape() with mocking?
}
