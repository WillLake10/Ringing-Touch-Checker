package com.williamlake.main.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MethordTest {

    @Test
    public void canGetStage(){
        String stage;
        stage = Methord.getStage(6);
        assertEquals("Minor", stage);

        stage = Methord.getStage(12);
        assertEquals("Maximus", stage);

        stage = Methord.getStage(7);
        assertEquals("Triples", stage);
    }

    @Test
    public void canCorrectlyGenerateName(){
        String name;
        name = Methord.getName(8, "Plain Bob");
        assertEquals("Plain Bob Major", name);
    }

    @Test
    public void canCreateAndRetrieveMethord(){
        Methord methord = new Methord("Plain Bob", "x,16,x,16,x,16,x,16,x,16,x,12", 6, "x,14,x", "x,1234,x");
        methord.toString();

        assertEquals("Plain Bob Minor", methord.getName());
        assertEquals("x,16,x,16,x,16,x,16,x,16,x,12", methord.getNotation());
        assertEquals("x,14,x", methord.getBob_notation());
        assertEquals("x,1234,x", methord.getSingle_notation());


    }
}
