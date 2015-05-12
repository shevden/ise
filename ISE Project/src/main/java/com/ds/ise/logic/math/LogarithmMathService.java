package com.ds.ise.logic.math;

/**
 * Contains functions connected with logarithm calculations.
 *
 * @author Denys Shvchenko
 * @version 1.0
 */
public class LogarithmMathService {

    /**
     * Binary logarithm function.
     *
     * @param value
     *            for logarithm function input.
     * @return calculated binary logarithm.
     */
    public static double lb(double value) {
        return (Math.log(value) / Math.log(2.0));
    }
}
