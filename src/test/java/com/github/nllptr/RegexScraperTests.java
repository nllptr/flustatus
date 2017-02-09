package com.github.nllptr;

import com.github.nllptr.scraper.RegexScraper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexScraperTests {

    private static final String goodUrl = "http://google.com";
    private static final String badUrl = "ftp://thisurlwillfailso.hard";

    @Test
    public void fetchTestSuccess() {
        RegexScraper scraper = new RegexScraper(goodUrl, "");
        assertThat(scraper.fetch()).isEqualTo(true);
    }

    @Test
    public void fetchTestFail() {
        RegexScraper scraper = new RegexScraper(badUrl, "");
        assertThat(scraper.fetch()).isEqualTo(false);
    }

    //TODO: Can I test scrape() with mocking?
}
