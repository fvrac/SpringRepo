package calculator.controller;

import calculator.entity.Operations;
import calculator.service.CalcInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CalcController{


  private Optional<Integer> convert_i(String param) {
    try {
      return Optional.of(Integer.parseInt(param));
    } catch (Exception ignored) {
      return Optional.empty();
    }
  }

  private Optional<String> wrap(String param) {
    return Optional.ofNullable(param);
  }

  private Optional<Integer> divide(int x, int y) {
    if (y == 0) return Optional.empty();
    return Optional.of(x / y);
  }

  private Optional<Integer> do_op(String p1, String p2, String op) {
    Optional<Integer> p1o = convert_i(p1);
    Optional<Integer> p2o = convert_i(p2);
    Optional<String> opo = wrap(op);

    return opo.flatMap(o -> p1o.flatMap(i1 -> p2o.flatMap(i2 -> {
      switch (o) {
        case "plus":
          return Optional.of(i1 + i2);
        case "minus":
          return Optional.of(i1 - i2);
        case "mult":
          return Optional.of(i1 * i2);
        case "divide":
          return divide(i1, i2);
      }
      return Optional.empty();
    })));
  }

  private String calc(String p1, String p2, String op) {
    return do_op(p1, p2, op)
            .map(r -> String.format("%d", r))
            .orElse("Smth went wrong with parameters");
  }

  private CalcInterface calcService;

  public CalcController(@Autowired CalcInterface service) {
    this.calcService = service;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/add")
  public List<Operations> add(@RequestParam("op1") String op1, @RequestParam("op2") String op2,@RequestParam("op") String op) {
    String ans = calc(op1, op2, op);
    calcService.add(op1, op2, op, ans);
    return calcService.get();
  }

  @GetMapping("/history")
  public List<Operations> get_all_users() {
    return calcService.get();
  }

}
