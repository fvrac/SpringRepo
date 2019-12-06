package calculator.service;

import calculator.entity.Operations;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CalcService implements CalcInterface{

  private final List<Operations> content = new LinkedList<>();

  public CalcService() {
    content.add(new Operations("1", "2", "plus", "3"));
    content.add(new Operations("6", "4", "minus", "10"));
  }

  @Override
  public List<Operations> get(){
    return content;
  }

  @Override
  public void add(String op1, String op2, String op, String ans) {
    content.add(new Operations(op1, op2, op, ans));
  }

}
