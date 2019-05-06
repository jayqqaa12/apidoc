package com.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: 12
 * @create: 2018-11-29 10:53
 * @desc 测试一下
 **/
public class Test {


    public static void main(String[] args) {

        String text = "/**     *     * 接口测试     * @param a 参数1     * @param b 参数2     *     * @desc 描述的内容     *     */    " +
                "@GetMapping(value = \"/action2\")    public void action3(";


        Pattern pattern = Pattern.compile("(?<=@param)[\\s\\w\\u4e00-\\u9fa5]+");

        Matcher matcher = pattern.matcher(text);

        matcher.find();


        System.out.println(matcher.group(0).trim());


    }
}
