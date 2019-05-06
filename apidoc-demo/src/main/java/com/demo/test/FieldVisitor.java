package com.demo.test;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.collect.Maps;

import java.util.Map;

public class FieldVisitor extends VoidVisitorAdapter<Object> {

    private static final Map<String, String> fieldMap = Maps.newHashMap();


    /**
     * 变量的注解 解析jsr303
     */
    @Override
    public void visit(FieldDeclaration n, Object arg) {

        n.getComment().ifPresent(c ->
                fieldMap.put(n.getVariables().get(0).getName().toString(), c.getContent())
        );


        super.visit(n, arg);
    }

}
