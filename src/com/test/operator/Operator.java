package com.test.operator;


import java.util.List;

/**
 * @author yuzhen_lin
 * @version Id: Operator, v 0.1 2021-05-30 10:39 PM yuzhen_lin Exp $
 */
public interface Operator {


     List<Double> undo() ;

     Double ops(Double a, Double b);


     void clear() ;

     Double getMethod(Double a, Double b);


}
