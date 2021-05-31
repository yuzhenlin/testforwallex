package com.test;


import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


import com.sun.javafx.binding.StringFormatter;
import com.test.operator.Add;
import com.test.operator.Div;
import com.test.operator.InputNum;
import com.test.operator.Minus;
import com.test.operator.Muti;
import com.test.operator.Operator;
import com.test.operator.Sqrt;

public class Cal {

    //cal stack
    private Stack<Double> stack = new Stack<>();

    //ops step record for undo
    private Stack<Operator> stackOps =new Stack<>();


    //storge/manager operators
    private Set<Operator> operators =new HashSet<>();



    //init or factory
    private Add add = new Add(this);
    private Muti muti =new Muti(this);
    private Sqrt sqrt = new Sqrt(this);
    private Div div = new Div(this);
    private Minus minus = new Minus(this);
    private InputNum inputNum = new InputNum(this);





    public static void main(String[] args) {

        Cal cal = new Cal();

        cal.read();
    }


    private void read() {
        Scanner scan = new Scanner(System.in);

        System.out.println("input：");


        String read =null;
        while(!"".equals(read=scan.nextLine())){

            if(stackOps.size()>10){
                stackOps.remove(0);
            }
            String  out =readInput(read);
            if(null!=out){
                System.out.println(out);
            }

            System.out.println(getPrintData());

        }

        scan.close();
    }

    public String getPrintData() {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(10);
        nf.setRoundingMode(RoundingMode.DOWN);



        StringBuilder sb=new StringBuilder();
        sb.append("stack:");

        for(Double d:stack){

            sb.append(" ");
            if(d>=0){
             sb.append(nf.format(d));
            }else{
                //for format
                sb.append("- ").append(nf.format(d/-1));
            }
        }


      return sb.toString();
    }

    public String readInput(String read) {

        String arr[] = read.split("");

        int i = 0;
        try {
            StringBuilder sb= new StringBuilder();

            for(;i<read.length();i++){
                if(arr[i].trim().equals("")){
                    //new word and valid.
                    String s= sb.toString();

                    if (!s.equals("")){
                        dealInput(s);
                        sb =new StringBuilder();
                    }
                }else{
                    sb.append(arr[i]);
                }
            }

            String s= sb.toString();
            if (!s.equals("")){
                dealInput(s);

            }

            return null;
        }catch (RuntimeException e){

          return StringFormatter.format("operator %s (position: %d): insucient parameters",
                arr[i-1],i).getValue();
        }
    }

    private void dealInput(String s) {

        if(isNumeric(s)){
            stack.push(addNum(s));
            return;
        }


            switch (s) {
                case "+":
                    add();
                    break;
                case "-":
                    minus();
                    break;
                case "*":
                    muti();
                    break;
                case "/":
                    div();
                    break;
                case "sqrt":
                    sqrt();
                    break;
                case "undo":
                    undo();
                    break;
                case "clear":
                    clear();
                    break;

                default:
                    throw new RuntimeException(s);




            }

    }

    private void clear() {
        stack.clear();
        stackOps.clear();
        operators.forEach(Operator::clear);
    }

    private Double addNum(String s) {
        return inputNum.ops(Double.parseDouble(s),null);
    }

    private void undo() {
        stack.pop();
        stack.addAll(stackOps.pop().undo());
    }




    private boolean isNumeric(String str){
       return  str.matches("^(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    private void sqrt() {
        if(stack.size()<1){
            throw new RuntimeException();
        }
        stack.push(sqrt.ops(stack.pop(),null));
    }

    private void add() {
        if(stack.size()<2){
            throw new RuntimeException();
        }
        stack.push(add.ops(stack.pop(), stack.pop()));

    }

    private void div() {
        if(stack.size()<2){
            throw new RuntimeException();
        }
        stack.push(div.ops(stack.pop(), stack.pop()));

    }
    private void muti() {
        if(stack.size()<2){
            throw new RuntimeException();
        }
        stack.push(muti.ops(stack.pop(), stack.pop()));

    }

    private void minus() {
        if(stack.size()<2){
            throw new RuntimeException();
        }
        stack.push(minus.ops(stack.pop(), stack.pop()));

    }

    public void registerOpe(Operator operator) {
        operators.add(operator);
    }


    public void addAction(Operator operator) {
        if(stackOps.size()>10){
            stackOps.remove(0);
        }
        stackOps.add(operator);
    }
}