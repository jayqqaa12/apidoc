package com.apidoc.utis;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author: 12
 * @create: 2019-03-15 15:00
 **/
public class JparseUtil {



    public  static Map<String, String> parseField(String  path)   {

        CompilationUnit unit = null;
        try {
            unit = JavaParser.parse(new File(path));
            FieldVisitor visitor = new FieldVisitor();
            unit.accept(visitor, null);
            return visitor.getFieldMap();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    

}
