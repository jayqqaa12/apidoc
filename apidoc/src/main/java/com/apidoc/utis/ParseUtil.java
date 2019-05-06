package com.apidoc.utis;

import cn.hutool.core.util.ReUtil;
import com.apidoc.common.Const;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 字符串工具类
 */
public class ParseUtil {

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(String[] value) {
        return !isEmpty(value);
    }

    private static boolean isEmpty(String[] value) {
        return (value == null || value.length == 0);
    }

    private static Map<String, String> params = new HashMap<>();

    public static synchronized String getFieldsNotes(Integer actionId, String paramName) {
        String key = actionId + "-" + paramName;
        return params.get(key);
    }


    public synchronized static Map<String, String> getFieldsComment(Class clazz) {




        Map<String, String> map = getFieldsNotes(clazz.getName());
        if(map==null)return map;
        //获取父类的注解
        String pName = null;
        do {
            pName = clazz.getSuperclass().getName();
            Map<String, String> m=  getFieldsNotes(pName);
            if(m==null)return map;
            map.putAll(m);
        } while (pName.equals("java.lang.Object"));

        return map;


    }

 
    /**
     * 得到java类文件的字段和注释
     *
     * @param className 类全名
     * @return Map
     */
    public synchronized static Map<String, String> getFieldsNotes(String className) {

        String path = getClassPath(className);
        if (path == null) return null;
        return JparseUtil.parseField(path);


        //1.行注释 //
        //2.多行注释/*  */
        //3.文档注释 /**  */
        //文档注释可以合并为多行注释
//        try (BufferedReader bufferedReader = new BufferedReader(
//                new InputStreamReader(new FileInputStream(path), Const.charSet), Const.bufferSize)
//        ) {
//
//            Map<String, String> noteMap = new HashMap<>();//存放字段的注释 key为字段名 value为注释
//            String line;
//            StringBuilder sb = new StringBuilder();
//            String valueUp = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                ////读取行注释
//                boolean match = ReUtil.isMatch(".*//.*", line);//正则解释： 任意多个任意字符//任意多个任意字符
//                if (line.contains("*")) {
//                    valueUp = null;
//                }
//                if (match) {
//                    valueUp = line.replaceAll(".*//", "").trim();//正则解释： 任意多个任意字符//
//                    //行注释只能是在代码行的上边或右边
//                    String str = line.replaceAll("//.*", "");//正则解释： //任意多个任意字符
//                    //行注释位于代码行右边
//                    if (isNotEmpty(str.trim())) {
//                        String[] split = str.split("\\s");//正则解释： 空白字符
//                        if (split.length > 0) {
//                            //key
//                            String key = split[split.length - 1].replace(";", "");
//                            //value
//                            String value = line.replaceAll(".*//", "");//正则解释： 任意多个任意字符//
//                            noteMap.put(key, value);
//                        }
//                    }
//                } else {//行注释位于代码行上方
//                    if (isNotEmpty(valueUp) && line.contains(";") && line.contains("private")) {
//                        //key
//                        String[] split = line.split("\\s");
//                        String key = split[split.length - 1].replace(";", "");
//                        String value = valueUp;
//                        valueUp = null;
//                        noteMap.put(key, value);
//                    }
//                }
//                sb.append(line);
//            }
//            String str = sb.toString();//java文件的内容
//            ////读取多行注释
//            Pattern p = Pattern.compile("/\\*{1,2}[^{]*?\\*/\\s*private.+?;");//正则解释 /+1或2个* +任意个除{之外的字符非贪婪式匹配 +*/+人一个空白字符+private+任意个任意字符+;
//            Matcher m = p.matcher(str);
//            while (m.find()) {
//                String group = m.group();
//                Pattern pkey = Pattern.compile("/\\*{1,2}.*?\\*/");
//                Matcher mkey = pkey.matcher(group);
//                mkey.find();
//                String value = mkey.group();
//                value = value.replaceAll("/|\\*", "").trim();
//
//                Pattern pvalue = Pattern.compile("private.+?;");
//                Matcher mvalue = pvalue.matcher(group);
//                mvalue.find();
//                String key = mvalue.group();
//                String[] keys = key.split("\\s");
//                key = keys[keys.length - 1].replaceAll(";", "");
//
//                noteMap.put(key, value);
//            }
//            return noteMap;
//        } catch (IOException e) {
//            return null;
//        }
    }

    /**
     * 根据类名获得java源文件的路径
     *
     * @param className
     * @return
     */
    private static String getClassPath(String className) {
        String path = className.replace(".", File.separator) + ".java";

        for (String base : Const.codePath) {
            String p = base + path;
            if (new File(p).exists()) return p;
        }

        return null;
    }

    /**
     * 获得类所有public方法的注释
     *
     * @param className 类的全名
     */
    public static Map<String, Map<String, String>> getMethodNotes(String className) {
        String path = getClassPath(className);

        if (path == null) return new HashMap<>();
        ;
        //1.行注释 //
        //2.多行注释/*  */
        //3.文档注释 /**  */
        //文档注释可以合并为多行注释
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), Const.charSet), Const.bufferSize)
        ) {

            Map<String, Map<String, String>> noteMap = new HashMap<>();//存放字段的注释 key为字段名 value为注释
            String line;
            StringBuilder sb = new StringBuilder();
            String valueUp = null;
            while ((line = bufferedReader.readLine()) != null) {
                ////读取行注释
                //行注释只能是在代码行的上边
                boolean match = ReUtil.isMatch("\\s*//.*", line);//正则解释： 任意多个空白字符//任意多个任意字符
                if (match) {
                    valueUp = line.replaceAll(".*//", "").trim();//正则解释： 任意多个任意字符//
                } else {
                    if (isNotEmpty(valueUp) && line.contains("public") && line.contains("(")) {
                        Pattern pvalue = Pattern.compile("public.+?\\(");
                        Matcher mvalue = pvalue.matcher(line);
                        mvalue.find();
                        String key = mvalue.group();
                        String[] keys = key.split("\\s");
                        key = keys[keys.length - 1].replaceAll("\\(|\\)", "");
                        String value = valueUp;
                        valueUp = null;

                        Map map = new HashMap();
                        map.put("name", value);

                        noteMap.put(className + "-" + key, map);
                    }
                }
                sb.append(line);
            }
            String str = sb.toString();//java文件的内容
            ////读取多行注释

            Pattern p = Pattern.compile("/\\*{1,2}[^/]*?\\*/[^**]+?public[^{]+?\\(");
            Pattern descPattern = Pattern.compile("(?<=@desc)[\\s\\w\\u4e00-\\u9fa5]+");
            Pattern methodPattern = Pattern.compile("public.+?\\(");


            Matcher m = p.matcher(str);
            while (m.find()) {
                String group = m.group();
                Pattern pkey = Pattern.compile("/\\*{1,2}.*?\\*/");
                Matcher keyMatcher = pkey.matcher(group);
                keyMatcher.find();
                String value = keyMatcher.group();
                value = value.replaceAll("/|\\*", "").trim();
                value = value.split("\\s")[0];

                Matcher nameMatcher = methodPattern.matcher(group);
                nameMatcher.find();
                String key = nameMatcher.group();
                String[] keys = key.split("\\s");
                key = keys[keys.length - 1].replaceAll("\\(|\\)", "");


                Map map = new HashMap();
                map.put("name", value);

                // 分析接口描述@desc
                Matcher descMatcher = descPattern.matcher(group);
                if (descMatcher.find()) {
                    map.put("desc", descMatcher.group());
                }
                // 分析@param 存入缓存
                map.put("params", group);
                noteMap.put(className + "-" + key, map);

            }
            return noteMap;
        } catch (IOException e) {
            return null;
        }
    }

    static Pattern paramPattern = Pattern.compile("(?<=@param)[\\s\\w\\u4e00-\\u9fa5]+");

    public static void parseParams(Integer actionId, String str) {
        if (StringUtils.isEmpty(str)) return;

        Matcher paramMatcher = paramPattern.matcher(str);
        while (paramMatcher.find()) {
            String[] param = paramMatcher.group().split("\\s");
            List<String> list = Arrays.stream(param).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
            if (list.size() > 1)
                params.put(actionId + "-" + list.get(0), list.get(1));
        }
    }


}
