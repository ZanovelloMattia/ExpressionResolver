package me.ZanovelloMattia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportanceFinder {

    final private List<String> expression;
    final private List<Integer> brImpPointer;

    final private List<String> opImpPointer;

    public ImportanceFinder(String expression) {
        this.expression = new ArrayList<>(Arrays.asList(expression.split("")));
        brImpPointer = new ArrayList<>();
        opImpPointer = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            brImpPointer.add(0);
            opImpPointer.add(null);
        }

    }

    public void bracketsImportanceFinder(){
        int importanceCounter = 1;

        for(int i = 0; i < expression.size(); i++){
            if(Constants.oBrackets.contains(expression.get(i))){
                importanceCounter++;
                brImpPointer.set(i, importanceCounter);
            }
            if(Constants.cBrackets.contains(expression.get(i))){
                if(importanceCounter == 1){
                    throw new RuntimeException("Expression missing brackets");
                }
                importanceCounter--;
                brImpPointer.set(i, importanceCounter);
            }
        }

        if(importanceCounter != 1){
            throw new RuntimeException("Expression missing brackets number: "+ importanceCounter);
        }
    }

    public void operationImportanceFinder(){
        int lastBr = 1;
        for(int i = 0; i < expression.size(); i++){
            if(brImpPointer.get(i) != 0){
                lastBr = brImpPointer.get(i);
            }
            if(Constants.operationLess.contains(expression.get(i))){
                opImpPointer.set(i, lastBr + "1");
            }
            if(Constants.operationMore.contains(expression.get(i))){
                opImpPointer.set(i, lastBr + "2");
            }
        }
    }

    public List<String> getOpImpPointer() {
        return opImpPointer;
    }

    public List<Integer> getBrImpPointer() {
        return brImpPointer;
    }

}



//123*323*(313-4234*343*{432-43+3/43}*342+43)*321-321+32