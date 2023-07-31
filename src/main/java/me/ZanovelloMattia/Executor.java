package me.ZanovelloMattia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executor {

    final private List<String> expression;
    final private List<Integer> brImpPointer;
    final private List<String> opImpPointer;

    public Executor(String expression, List<Integer> brImpPointer, List<String> opImpPointer) {
        this.expression = new ArrayList<>(Arrays.asList(expression.split("")));
        this.brImpPointer = brImpPointer;
        this.opImpPointer = opImpPointer;
    }

    public void resolver(){
        int indexMax = 0;
        double prevNum;
        double afterNum;
        double result;

        indexMax = searchMaxIndex();
        while (indexMax != -1) {
            removeUnusedBr();
            prevNum = getPrevNum(indexMax);
            afterNum = getAfterNum(indexMax);
            result = calc(indexMax, prevNum, afterNum);
            replaceOldNumWhit(Double.toString(result), indexMax, Double.toString(prevNum), Double.toString(afterNum));
            indexMax = searchMaxIndex();
        }
        for (String str: expression) {
            System.out.print(str);
        }
        System.out.println();
    }

    private void replaceOldNumWhit(String result, int indexMax, String prevNum, String afterNum) {

        String[] tmp = prevNum.split("");
        if(tmp[tmp.length-1].equals("0") && tmp[tmp.length-2].equals(".")){
            tmp[tmp.length-1] = "";
            tmp[tmp.length-2] = "";
        }
        prevNum = String.join("", tmp);

        tmp = afterNum.split("");
        if(tmp[tmp.length-1].equals("0") && tmp[tmp.length-2].equals(".")){
            tmp[tmp.length-1] = "";
            tmp[tmp.length-2] = "";
        }
        afterNum = String.join("", tmp);

        tmp = result.split("");
        if(tmp[tmp.length-1].equals("0") && tmp[tmp.length-2].equals(".")){
            tmp[tmp.length-1] = "";
            tmp[tmp.length-2] = "";
        }
        result = String.join("", tmp);

        for (int i = indexMax-prevNum.length(); i < indexMax+afterNum.length()+1; i++) {
            expression.remove(indexMax-prevNum.length());
            brImpPointer.remove(indexMax-prevNum.length());
            opImpPointer.remove(indexMax-prevNum.length());
        }

        for(int i = indexMax-prevNum.length(); i < indexMax+result.length()-prevNum.length(); i++){
            expression.add(i, result.split("")[i-(indexMax-prevNum.length())]);
            brImpPointer.add(i, 0);
            opImpPointer.add(i, null);
        }
    }

    private double calc(int indexMax, double prevNum, double afterNum) {
        String operator = expression.get(indexMax);
        if(operator.equals("+")){
            return prevNum+afterNum;
        } else if (operator.equals("-")) {
            return prevNum-afterNum;
        } else if (operator.equals("*")) {
            return prevNum*afterNum;
        } else if (operator.equals("/")) {
            return prevNum/afterNum;
        } else if (operator.equals("%")) {
            return prevNum%afterNum;
        }
        return Double.MIN_VALUE;
    }

    private void removeUnusedBr() {
        boolean toDelete = false;
        int toDeleteIndex = 0;

        for(int i = 0; i < expression.size()-1; i++){
            if(toDelete && opImpPointer.get(i) != null){
                toDelete = false;
            }
            if(brImpPointer.get(i) != 0){
                if(Constants.oBrackets.contains(expression.get(i))){
                   toDelete = true;
                   toDeleteIndex = i;
                }
                if(toDelete && Constants.cBrackets.contains(expression.get(i))){
                    brImpPointer.remove(i);
                    brImpPointer.remove(toDeleteIndex);
                    opImpPointer.remove(i);
                    opImpPointer.remove(toDeleteIndex);
                    expression.remove(i);
                    expression.remove(toDeleteIndex);
                }
            }
        }
    }

    private int searchMaxIndex() {
        int max = -1;
        int indexMax = -1;
        int imp;

        for (int i = 0; i < opImpPointer.size(); i++) {
            if (opImpPointer.get(i) == null) continue;
            imp = Integer.parseInt(opImpPointer.get(i));
            if (max < imp) {
                max = imp;
                indexMax = i;
            }
        }
        return indexMax;
    }

    private double getPrevNum(int indexMax) {
        String sNum = "";

        for (int i = indexMax-1; i >= 0; i--){
            if(i == indexMax-1 && Constants.brackets.contains(expression.get(i))){
                continue;
            } else if(i != indexMax-1 && Constants.brackets.contains(expression.get(i))){
                break;
            } else if(Constants.operation.contains(expression.get(i)) && opImpPointer.get(i) != null){
                break;
            }

            sNum = expression.get(i) + sNum;
        }

        return Double.parseDouble(sNum);
    }

    private double getAfterNum(int indexMax) {
        String sNum = "";

        for (int i = indexMax+1; i < expression.size(); i++){
            if(i == indexMax+1 && Constants.brackets.contains(expression.get(i))){
                continue;
            } else if(i != indexMax+1 && Constants.brackets.contains(expression.get(i))){
                break;
            } else if(Constants.operation.contains(expression.get(i)) && opImpPointer.get(i) != null){
                break;
            }
            sNum = sNum + expression.get(i);
        }

        return Double.parseDouble(sNum);
    }

}
