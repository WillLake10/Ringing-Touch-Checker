package com.williamlake.main;

import com.williamlake.main.data.Method;

public class Main {

    public static void main(String[] args) {
        Method workingMethod = Method.getInstance();
        System.out.println(workingMethod.toString());

    }

}
