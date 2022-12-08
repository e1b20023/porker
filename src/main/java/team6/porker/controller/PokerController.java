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
  public String poker4(ModelMap model) {
    ArrayList<Deck> ids = DeckMapper.selectAllByDeckid();
    for (int i = 0; i < ids.size(); ++i) {
      int rnd = (int) (Math.random() * (double) ids.size());
      int w = ids.get(i).getDeckid();
      ids.get(i).setDeckid(ids.get(rnd).getDeckid());
      ids.get(rnd).setDeckid(w);
    }
    Player user1 = new Player();
    user1.Distribute(ids);
    System.out.println(user1.getHand());
    model.addAttribute("Hands", user1);
    return "poker4.html";
  }

}
