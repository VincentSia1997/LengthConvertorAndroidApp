package com.example.lengthconverter;


import java.text.DecimalFormat;

public class LengthConverter {

    private static final int M_TO_CM = 100;
    private static final double M_TO_IN = 39.37;
    private static final double M_TO_MM_KM = 1000; //m conversion for km and mm use same constant value
    private static final double M_TO_MILES = 1609;
    private static final double M_TO_YARD = 1.094;
    private static final double M_TO_FOOT = 3.281;
    private static final DecimalFormat df = new DecimalFormat("####0.00");


    /*Convert any unit of length to m, then return length in m*/
    public double toM(String unit, double length) {
        if (unit.equals("mm")) {
            return length / M_TO_MM_KM;
        }
        if (unit.equals("cm")) {
            return length / M_TO_CM;
        }
        if (unit.equals("inches")) {
            return length / M_TO_IN;
        }
        if (unit.equals("km")) {
            return length * M_TO_MM_KM;
        }
        if (unit.equals("miles")) {
            return length * M_TO_MILES;
        }
        if (unit.equals("yard")) {
            return length / M_TO_YARD;
        }
        if (unit.equals("foot")){
            return length / M_TO_FOOT;
        }

        return length;

    }

    /*Convert any unit from m to any unit, and return double in 2 decimal place if possible*/
    public double fromM(String unit, double length) {
        if (unit.equals("mm")) {
            length *= M_TO_MM_KM;
        }
        if (unit.equals("cm")) {
            length *= M_TO_CM;
        }
        if (unit.equals("inches")) {
            length *= M_TO_IN;
        }
        if (unit.equals("km")) {
            length /= M_TO_MM_KM;
        }
        if (unit.equals("miles")) {
            length /= M_TO_MILES;
        }
        if (unit.equals("yard")) {
            length *= M_TO_YARD;
        }
        if (unit.equals("foot")){
            length *= M_TO_FOOT;
        }

        return Double.parseDouble(df.format(length));
    }

}
