package com.test.operator;

import com.test.Cal;

/**
 * @author yuzhen_lin
 * @version Id: Add, v 0.1 2021-05-30 5:32 PM yuzhen_lin Exp $
 */
public class Muti extends AbsOperator {

    public Muti(Cal cal) {
        super(cal);
    }

    @Override
    public Double getMethod(Double a, Double b) {
        return b*a;
    }
}
