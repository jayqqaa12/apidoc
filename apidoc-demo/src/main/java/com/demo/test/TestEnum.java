package com.demo.test;

/**
 * @author: 12
 * @create: 2018-11-07 15:00
 **/
public class TestEnum {


    private static String getEnumDesc(String description, Class tclass) {

        StringBuilder sb= new StringBuilder();
        sb.append(description).append("\n");

        for (Object e : tclass.getEnumConstants()) {

            if (e instanceof Enum) {
                sb.append(((Enum) e).name()+":"+((Enum) e).ordinal()+"\n");
            }
        }

        return sb.toString();
    }




}
