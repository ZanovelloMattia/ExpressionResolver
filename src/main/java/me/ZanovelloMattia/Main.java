package me.ZanovelloMattia;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String expression = scanner.nextLine();

        ImportanceFinder impFinder = new ImportanceFinder(expression);
        impFinder.bracketsImportanceFinder();
        impFinder.operationImportanceFinder();

        Executor executor = new Executor(expression, impFinder.getBrImpPointer(), impFinder.getOpImpPointer());
        executor.resolver();

    }
}
