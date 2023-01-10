package team6.porker.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import team6.porker.model.Deck;
import team6.porker.model.DeckMapper;
import team6.porker.model.PlayerMapper;
import team6.porker.model.SDeckMapper;
import team6.porker.service.AsyncPokerService;
import team6.porker.model.Player;

@Controller
public class PokerController {
  Player user1 = new Player();
  Player user2 = new Player();
  Player user3 = new Player();
  Player user4 = new Player();
  ArrayList<Deck> ids = new ArrayList<Deck>();

  @Autowired
  DeckMapper DeckMapper;

  @Autowired
  SDeckMapper sDeckMapper;

  @Autowired
  PlayerMapper playerMapper;

  @Autowired
  AsyncPokerService pDeck;

  @PostMapping("/start")
  public String lobby(@RequestParam String name, ModelMap model) {
    playerMapper.insertPlayerName(name);
    model.addAttribute("name", name);
    return "start.html";
  }

  @GetMapping("/poker4")
  public String poker41(ModelMap model) {
    Deck d = new Deck(1);
    this.ids.add(d);
    if (ids.size() == 1) {
      this.ids.remove(0);
      this.ids = DeckMapper.selectAllByDeckid();
      System.out.printf("%d\n", this.ids.size());
      // user1.getHand().clear();
      for (int i = 0; i < this.ids.size(); ++i) {
        int rnd = (int) (Math.random() * (double) this.ids.size());
        int w = this.ids.get(i).getDeckid();
        this.ids.get(i).setDeckid(this.ids.get(rnd).getDeckid());
        this.ids.get(rnd).setDeckid(w);
      }

      for (int j = 0; j < ids.size(); ++j) {
        sDeckMapper.insertsdecknumber(this.ids.get(j).getDeckid());
      }
    }

    ArrayList<Player> players = playerMapper.selectAllPlayer();
    if (players.size() == 1) {
      user1.Distribute(sDeckMapper);
      model.addAttribute("Hand1", user1.getHand().get(0));
      model.addAttribute("Hand2", user1.getHand().get(1));
      model.addAttribute("Hand3", user1.getHand().get(2));
      model.addAttribute("Hand4", user1.getHand().get(3));
      model.addAttribute("Hand5", user1.getHand().get(4));
    } else if (players.size() == 2) {
      user2.Distribute(sDeckMapper);
      model.addAttribute("Hand1", user2.getHand().get(0));
      model.addAttribute("Hand2", user2.getHand().get(1));
      model.addAttribute("Hand3", user2.getHand().get(2));
      model.addAttribute("Hand4", user2.getHand().get(3));
      model.addAttribute("Hand5", user2.getHand().get(4));
    } else if (players.size() == 3) {
      user3.Distribute(sDeckMapper);
      model.addAttribute("Hand1", user3.getHand().get(0));
      model.addAttribute("Hand2", user3.getHand().get(1));
      model.addAttribute("Hand3", user3.getHand().get(2));
      model.addAttribute("Hand4", user3.getHand().get(3));
      model.addAttribute("Hand5", user3.getHand().get(4));
    } else if (players.size() == 4) {
      user4.Distribute(sDeckMapper);
      model.addAttribute("Hand1", user4.getHand().get(0));
      model.addAttribute("Hand2", user4.getHand().get(1));
      model.addAttribute("Hand3", user4.getHand().get(2));
      model.addAttribute("Hand4", user4.getHand().get(3));
      model.addAttribute("Hand5", user4.getHand().get(4));
    }
    return "poker4.html";
  }

  @PostMapping("/poker4")
  public String poker42(ModelMap model) {
    int result;
    int score;

    String name = user1.getplayername();
    result = user1.getPokerHand(user1.getHand());
    String handname = user1.HandName(result);
    model.addAttribute("handname", handname);
    score = user1.getScore();
    playerMapper.updateResult(name, result, score);
    model.addAttribute("score", score);
    return "poker4.html";
  }

  @RequestMapping("/exchange")
  public String exchange(ModelMap model, Principal prin,
      @RequestParam(value = "h1", required = false) boolean h1,
      @RequestParam(value = "h2", required = false) boolean h2,
      @RequestParam(value = "h3", required = false) boolean h3,
      @RequestParam(value = "h4", required = false) boolean h4,
      @RequestParam(value = "h5", required = false) boolean h5) {

    String name = prin.getName();

    if (name.equals(user1.getplayername())) {
      if (h1) {
        user1.Exchange(0, sDeckMapper);
      }
      if (h2) {
        user1.Exchange(1, sDeckMapper);
      }
      if (h3) {
        user1.Exchange(2, sDeckMapper);
      }
      if (h4) {
        user1.Exchange(3, sDeckMapper);
      }
      if (h5) {
        user1.Exchange(4, sDeckMapper);
      }
      user1.sort(user1.getHand());

      model.addAttribute("Hand1", user1.getHand().get(0));
      model.addAttribute("Hand2", user1.getHand().get(1));
      model.addAttribute("Hand3", user1.getHand().get(2));
      model.addAttribute("Hand4", user1.getHand().get(3));
      model.addAttribute("Hand5", user1.getHand().get(4));
    }
    if (name.equals(user2.getplayername())) {
      if (h1) {
        user2.Exchange(0, sDeckMapper);
      }
      if (h2) {
        user2.Exchange(1, sDeckMapper);
      }
      if (h3) {
        user2.Exchange(2, sDeckMapper);
      }
      if (h4) {
        user2.Exchange(3, sDeckMapper);
      }
      if (h5) {
        user2.Exchange(4, sDeckMapper);
      }
      user2.sort(user2.getHand());

      model.addAttribute("Hand1", user2.getHand().get(0));
      model.addAttribute("Hand2", user2.getHand().get(1));
      model.addAttribute("Hand3", user2.getHand().get(2));
      model.addAttribute("Hand4", user2.getHand().get(3));
      model.addAttribute("Hand5", user2.getHand().get(4));
    }
    if (name.equals(user3.getplayername())) {
      if (h1) {
        user3.Exchange(0, sDeckMapper);
      }
      if (h2) {
        user3.Exchange(1, sDeckMapper);
      }
      if (h3) {
        user3.Exchange(2, sDeckMapper);
      }
      if (h4) {
        user3.Exchange(3, sDeckMapper);
      }
      if (h5) {
        user3.Exchange(4, sDeckMapper);
      }
      user3.sort(user3.getHand());

      model.addAttribute("Hand1", user3.getHand().get(0));
      model.addAttribute("Hand2", user3.getHand().get(1));
      model.addAttribute("Hand3", user3.getHand().get(2));
      model.addAttribute("Hand4", user3.getHand().get(3));
      model.addAttribute("Hand5", user3.getHand().get(4));
    }
    if (name.equals(user4.getplayername())) {
      if (h1) {
        user4.Exchange(0, sDeckMapper);
      }
      if (h2) {
        user4.Exchange(1, sDeckMapper);
      }
      if (h3) {
        user4.Exchange(2, sDeckMapper);
      }
      if (h4) {
        user4.Exchange(3, sDeckMapper);
      }
      if (h5) {
        user4.Exchange(4, sDeckMapper);
      }
      user4.sort(user4.getHand());

      model.addAttribute("Hand1", user4.getHand().get(0));
      model.addAttribute("Hand2", user4.getHand().get(1));
      model.addAttribute("Hand3", user4.getHand().get(2));
      model.addAttribute("Hand4", user4.getHand().get(3));
      model.addAttribute("Hand5", user4.getHand().get(4));
    }
    return "poker4.html";
  }

  @GetMapping("/push")
  public SseEmitter sample59() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.pDeck.asyncShowSDeckList(sseEmitter);
    return sseEmitter;
  }
}
