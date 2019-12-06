package calculator.service;

import calculator.entity.Operations;

import java.util.List;

public interface CalcInterface{
  List<Operations> get();
  void add(String op1, String op2, String op, String ans);
}
