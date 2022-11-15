package team6.porker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PokerController {

  @GetMapping("/start")
  public String lobby() {
    return "start.html";
  }

  @PostMapping("/start")
  public String lobby2(@RequestParam String name, ModelMap model) {

    model.addAttribute("name", name);
    return "start.html";
  }

}
