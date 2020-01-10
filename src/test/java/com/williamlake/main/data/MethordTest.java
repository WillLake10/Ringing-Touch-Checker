package com.williamlake.main.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MethordTest {

    @Test
    public void canGetStage(){
        String stage;
        stage = Method.getStage(6);
        assertEquals("Minor", stage);

        stage = Method.getStage(12);
        assertEquals("Maximus", stage);

        stage = Method.getStage(7);
        assertEquals("Triples", stage);
    }

    @Test
    public void canCreateAndRetrieveMethord(){
        Method method = new Method("Plain Bob", "x,16,x,16,x,16,x,16,x,16,x,12", 6, "x,14,x", "x,1234,x");
        method.toString();

        assertEquals("Plain Bob Minor", method.getName());
        assertEquals("x,16,x,16,x,16,x,16,x,16,x,12", method.getNotation());
        assertEquals("x,14,x", method.getBob_notation());
        assertEquals("x,1234,x", method.getSingle_notation());
    }
}
