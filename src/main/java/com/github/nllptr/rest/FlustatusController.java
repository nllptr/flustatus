package com.github.nllptr.rest;

import com.github.nllptr.model.Flustatus;
import com.github.nllptr.scraper.RegexScraper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlustatusController {

    static Logger logger = LoggerFactory.getLogger(FlustatusController.class);
    private RegexScraper scraper;
    private Flustatus status;

    @Autowired
    public FlustatusController(RegexScraper scraper) {
        this.scraper = scraper;
    }

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public Flustatus getStatus() {
        if (status != null) {
            return status;
        } else {
            if(scraper.fetch()) {
                String status = scraper.scrape();
                this.status = new Flustatus(status);
                return this.status;
            } else {
                logger.error("Scraper failed to fetch. Returning empty status.");
                return new Flustatus("");
            }
        }
    }

    @RequestMapping(value="/clear", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void clearCache() {
        status = null;
    }
}
