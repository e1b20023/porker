package team6.porker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team6.porker.model.DeckMapper;
import team6.porker.model.Player;
import team6.porker.model.Deck;

@Controller
public class PokerController {
  Player user1 = new Player();

  @Autowired
  DeckMapper DeckMapper;

  @GetMapping("/start")
  public String lobby() {
    return "start.html";
  }

  @PostMapping("/start")
  public String lobby2(@RequestParam String name, ModelMap model) {

    model.addAttribute("name", name);
    return "start.html";
  }

  @GetMapping("/poker4")
  public String poker41(ModelMap model) {
    ArrayList<Deck> ids = DeckMapper.selectAllByDeckid();
    // user1.getHand().clear();
    for (int i = 0; i < ids.size(); ++i) {
      int rnd = (int) (Math.random() * (double) ids.size());
      int w = ids.get(i).getDeckid();
      ids.get(i).setDeckid(ids.get(rnd).getDeckid());
      ids.get(rnd).setDeckid(w);
    }
    // Player user1 = new Player();
    user1.Distribute(ids);
    for (int i = 0; i < 5; i++) {
      // user1copy.add(user1.get(i).getHand());
    }

    model.addAttribute("Hands", user1);
    return "poker4.html";
  }

  @PostMapping("/poker4")
  public String poker42(ModelMap model) {
    int result;
    int score;

    result = user1.getPokerHand(user1.getHand());
    String handname = user1.HandName(result);
    model.addAttribute("handname", handname);
    score = user1.getScore();
    model.addAttribute("score", score);
    return "poker4.html";
  }
}
