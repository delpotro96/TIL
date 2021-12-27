package com.example.oembedtest.controller;

import com.example.oembedtest.service.OEmbedService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public class OEmbedController {

  private final OEmbedService oEmbedService;

  @GetMapping("/")
  public String temp(@RequestParam String paramUrl, Model model) throws Exception {

    JSONParser parser = new JSONParser();
    Object obj = parser.parse(oEmbedService.callEmbedProcess(paramUrl));
    JSONObject jsonObject = (JSONObject) obj;

    String domain = OEmbedService.getDomainName(paramUrl);

    if (domain.equals("twitter")) {
      model.addAttribute("result", jsonObject);
      return "resultOnlyForTwitter";
    } else {
      model.addAttribute("result", jsonObject);
      return "result";
    }
  }
}
