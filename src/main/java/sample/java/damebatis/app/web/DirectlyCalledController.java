package sample.java.damebatis.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("directlyCalled")
@Slf4j
public class DirectlyCalledController {
  @GetMapping
  public String damebatis(Model model) {
    return "directlyCalled";
  }
}
