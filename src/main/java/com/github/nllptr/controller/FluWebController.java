package com.github.nllptr.controller;

import com.github.nllptr.model.FluStatus;
import com.github.nllptr.service.RegexScrapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
public class FluWebController {

    RegexScrapeService scraper;

    @Autowired
    public FluWebController(RegexScrapeService scraper) {
        this.scraper = scraper;
    }

    @RequestMapping("/")
    public String index(Model model) {
        FluStatus status = scraper.getStatus();
        model.addAttribute("status", status.getStatus());
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        model.addAttribute("fetchTime", f.format(status.getFetchTime()));
        model.addAttribute("url", scraper.getUrl());
        return "index";
    }
}
