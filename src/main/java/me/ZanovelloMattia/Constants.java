package me.ZanovelloMattia;

import java.util.*;

public class Constants {

    static private String oBracketsS = "( [ {";
    static public List<String> oBrackets = new ArrayList<>(Arrays.asList(oBracketsS.split(" ")));

    static private String cBracketsS = ") ] }";
    static public List<String> cBrackets = new ArrayList<>(Arrays.asList(cBracketsS.split(" ")));

    static private String bracketsS = "( ) [ ] { }";
    static public List<String> brackets = new ArrayList<>(Arrays.asList(bracketsS.split(" ")));

    static private String operationSLess = "+ -";
    static public List<String> operationLess = new ArrayList<>(Arrays.asList(operationSLess.split(" ")));

    static private String operationSMore = "* / %";
    static public List<String> operationMore = new ArrayList<>(Arrays.asList(operationSMore.split(" ")));

    static private String operationS = "+ - * / %";
    static public List<String> operation = new ArrayList<>(Arrays.asList(operationS.split(" ")));

}
