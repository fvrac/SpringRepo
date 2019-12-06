package calculator.entity;

public class Operations{

  private String op1;
  private String op2;
  private String op;
  private String ans;

  public Operations(String op1, String op2, String op, String ans){
    this.op1 = op1;
    this.op2 = op2;
    this.op = op;
    this.ans = ans;
  }

  public String getOp1(){
    return op1;
  }

  public String getOp2(){
    return op2;
  }

  public String getOp(){
    return op;
  }

  public String getAns(){
    return ans;
  }

}
