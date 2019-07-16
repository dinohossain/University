package com.dating.app.idateu.MatchingAlgorithm;

import com.dating.app.idateu.UserDetail;
import com.dating.app.idateu.Homepage.DBConnector.MatchingAlgorithm;

import org.junit.Test;
import static junit.framework.TestCase.*;

public class MatchingAlgorithmTest {


    @Test
    public void testSM() { //straight men
        UserDetail.setGender("M");
        UserDetail.setOrientation("S");

        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "G"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "B"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "S"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "L"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "B"), true);
    }

    @Test
    public void testGM(){ //gay men
        UserDetail.setGender("M");
        UserDetail.setOrientation("G");

        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "G"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "B"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "L"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "B"), false);}



    @Test
    public void testMB(){ //bi men
        UserDetail.setGender("M");
        UserDetail.setOrientation("B");

        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "G"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "B"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "S"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "L"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "B"), true);}

    @Test
    public void testFS(){ //straight women
        UserDetail.setGender("F");
        UserDetail.setOrientation("S");

        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "S"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "G"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "B"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "L"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "B"), false);}

    @Test
    public void testFL(){//lesbian women
        UserDetail.setGender("F");
        UserDetail.setOrientation("L");

        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "G"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "B"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "L"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "B"), true);}

    @Test
    public void testFB(){ //bi women
        UserDetail.setGender("F");
        UserDetail.setOrientation("B");

        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "S"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "G"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("M", "B"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "S"), false);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "L"), true);
        assertEquals(MatchingAlgorithm.isSuitableMatch("F", "B"), true);}
}