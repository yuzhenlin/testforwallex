package com.test.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.test.Cal;

/**
 * @author yuzhen_lin
 * @version Id: Operator, v 0.1 2021-05-30 5:28 PM yuzhen_lin Exp $
 */
public abstract class AbsOperator implements Operator {

    Cal cal = null;



    public AbsOperator(Cal cal){
        this.cal =cal;
        cal.registerOpe(this);
    }

    Stack<List<Double>> stack = new Stack<>();


    @Override
    public List<Double> undo() {
        if(stack.size()==0){
            return new ArrayList<>();
        }
        return stack.pop();
    }

    @Override
    public Double ops(Double a, Double b) {
        if(stack.size()>10){
            stack.remove(0);
        }
        List<Double> list = new ArrayList<>();
        if(null!=b) {
            list.add(b);
        }
        if(null!=a) {
            list.add(a);
        }
        stack.push(list);

        cal.addAction(this);

        return getMethod(a,b);
    }

    @Override
    public void clear() {
        stack.clear();
    }





}
