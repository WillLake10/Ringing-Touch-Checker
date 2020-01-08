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
}
