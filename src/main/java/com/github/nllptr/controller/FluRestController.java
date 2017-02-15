package com.github.nllptr.controller;

import com.github.nllptr.model.FluStatus;
import com.github.nllptr.service.RegexScrapeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FluRestController {

    static Logger logger = LoggerFactory.getLogger(FluRestController.class);
    private RegexScrapeService scraper;
    private FluStatus status;

    @Autowired
    public FluRestController(RegexScrapeService scraper) {
        this.scraper = scraper;
    }

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public FluStatus getStatus() {
        return scraper.getStatus();
    }

    @RequestMapping(value="/clear", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void clearCache() {
        status = null;
    }
}
