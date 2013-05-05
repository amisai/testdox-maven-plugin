package com.codecrate.testdox;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.ArrayList;
import java.util.List;

public class TestDoxFormatter {
    private static final String CLASS_SUFFIX = "Test";
    private static List<String> methodPrefixesToRemove = null;
    private static List<String> methodPrefixesToKeep = null;

    {
        methodPrefixesToRemove = new ArrayList<String>();
        methodPrefixesToKeep = new ArrayList<String>();
        methodPrefixesToRemove.add("test");
        methodPrefixesToKeep.add("should");
    }

    public String prettifyTestClassName(String className) {
        className = trimPackagePrefix(className);
        className = trimTestSuffix(className);
        return toHumaneString(className);
    }

    public String prettifyTestMethodName(String testMethod) {
        testMethod = trimTestPrefix(testMethod);
        return toHumaneString(testMethod);
    }

    private String toHumaneString(String input) {
        StringBuffer buffer = new StringBuffer();
        for (int x = 0; x < input.length(); x++) {
            char ch = input.charAt(x);
            if (x != 0 && Character.isUpperCase(ch)) {
                buffer.append(" ");
                buffer.append(Character.toLowerCase(ch));
            } else {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    private String trimTestPrefix(String testMethod) {
        for (String prefix : methodPrefixesToRemove) {
            if (testMethod.startsWith(prefix)) {
                testMethod = testMethod.substring(prefix.length());
                break;
            }
        }
        return testMethod;
    }

    private String trimTestSuffix(String className) {
        if (className.endsWith(CLASS_SUFFIX)) {
            className = className.substring(0, className.lastIndexOf(CLASS_SUFFIX));
        }
        return className;
    }

    private String trimPackagePrefix(String className) {
        if (className.lastIndexOf(".") != -1) {
            className = className.substring(className.lastIndexOf(".") + 1);
        }
        return className;
    }

    public boolean isATestMethod(JavaMethod method) {
        String name = method.getName();
        boolean isTestMethod = false;
        List<String> methodPrefixes = new ArrayList<String>();
        methodPrefixes.addAll(methodPrefixesToKeep);
        methodPrefixes.addAll(methodPrefixesToRemove);

        for (String prefix : methodPrefixes) {
            if (name.startsWith(prefix)) {
                isTestMethod = true;
                break;
            }
        }
        return isTestMethod;
    }

    public boolean isTestClass(JavaClass clazz) {
        String name = clazz.getName();
        return name.endsWith(CLASS_SUFFIX);
    }
}
