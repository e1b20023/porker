package team6.porker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team6.porker.model.DeckMapper;
import team6.porker.model.Player;
import team6.porker.model.Room;
import team6.porker.model.Deck;

@Controller
public class PokerController {
  Player user1 = new Player();
  Player user2 = new Player();
  Player user3 = new Player();
  Player user4 = new Player();
  ArrayList<Deck> ids;

  String loginname;

  @Autowired
  DeckMapper DeckMapper;

  @Autowired
  private Room room;

  @GetMapping("/start")
  public String lobby() {
    return "start.html";
  }

  @PostMapping("/start")
  public String lobby2(@RequestParam String name, ModelMap model) {

    loginname = name;
    this.room.addUser(name);
    model.addAttribute("room", this.room);
    model.addAttribute("name", name);
    return "start.html";
  }

  @GetMapping("/poker4")
  public String poker41(ModelMap model) {
    ids = DeckMapper.selectAllByDeckid();
    System.out.printf("%d\n", ids.size());
    // user1.getHand().clear();
    for (int i = 0; i < ids.size(); ++i) {
      int rnd = (int) (Math.random() * (double) ids.size());
      int w = ids.get(i).getDeckid();
      ids.get(i).setDeckid(ids.get(rnd).getDeckid());
      ids.get(rnd).setDeckid(w);
    }

    switch (loginname) {
      case "user1":
        user1.Distribute(ids);
        model.addAttribute("Hand1", user1.getHand().get(0));
        model.addAttribute("Hand2", user1.getHand().get(1));
        model.addAttribute("Hand3", user1.getHand().get(2));
        model.addAttribute("Hand4", user1.getHand().get(3));
        model.addAttribute("Hand5", user1.getHand().get(4));

      case "user2":
        user2.Distribute(ids);
        model.addAttribute("Hand1", user2.getHand().get(0));
        model.addAttribute("Hand2", user2.getHand().get(1));
        model.addAttribute("Hand3", user2.getHand().get(2));
        model.addAttribute("Hand4", user2.getHand().get(3));
        model.addAttribute("Hand5", user2.getHand().get(4));

      case "user3":
        user3.Distribute(ids);
        model.addAttribute("Hand1", user3.getHand().get(0));
        model.addAttribute("Hand2", user3.getHand().get(1));
        model.addAttribute("Hand3", user3.getHand().get(2));
        model.addAttribute("Hand4", user3.getHand().get(3));
        model.addAttribute("Hand5", user3.getHand().get(4));

      case "user4":
        user4.Distribute(ids);
        model.addAttribute("Hand1", user4.getHand().get(0));
        model.addAttribute("Hand2", user4.getHand().get(1));
        model.addAttribute("Hand3", user4.getHand().get(2));
        model.addAttribute("Hand4", user4.getHand().get(3));
        model.addAttribute("Hand5", user4.getHand().get(4));

      default:
    }

    return "poker4.html";
  }

  @PostMapping("/poker4")
  public String poker42(ModelMap model) {
    int result1,result2,result3,result4;
    int score1,score2,score3,score4;

    result1 = user1.getPokerHand(user1.getHand());
    result2 = user2.getPokerHand(user2.getHand());
    result3 = user3.getPokerHand(user3.getHand());
    result4 = user4.getPokerHand(user4.getHand());

    String handname1 = user1.HandName(result1);
    String handname2 = user2.HandName(result2);
    String handname3 = user3.HandName(result3);
    String handname4 = user4.HandName(result4);

    model.addAttribute("handname1", handname1);
    model.addAttribute("handname2", handname2);
    model.addAttribute("handname3", handname3);
    model.addAttribute("handname4", handname4);

    score1 = user1.getScore();
    score2 = user2.getScore();
    score3 = user3.getScore();
    score4 = user4.getScore();

    model.addAttribute("score1", score1);
    model.addAttribute("score2", score2);
    model.addAttribute("score3", score3);
    model.addAttribute("score4", score4);
    return "poker4.html";
  }

  @RequestMapping("/exchange")
  public String exchange(ModelMap model,
      @RequestParam(value = "h1", required = false) boolean h1,
      @RequestParam(value = "h2", required = false) boolean h2,
      @RequestParam(value = "h3", required = false) boolean h3,
      @RequestParam(value = "h4", required = false) boolean h4,
      @RequestParam(value = "h5", required = false) boolean h5) {

    if (h1) {
      user1.Exchange(0, ids);
    }
    if (h2) {
      user1.Exchange(1, ids);
    }
    if (h3) {
      user1.Exchange(2, ids);
    }
    if (h4) {
      user1.Exchange(3, ids);
    }
    if (h5) {
      user1.Exchange(4, ids);
    }

    user1.sort(user1.getHand());

    model.addAttribute("Hand1", user1.getHand().get(0));
    model.addAttribute("Hand2", user1.getHand().get(1));
    model.addAttribute("Hand3", user1.getHand().get(2));
    model.addAttribute("Hand4", user1.getHand().get(3));
    model.addAttribute("Hand5", user1.getHand().get(4));
    return "poker4.html";
  }
}
