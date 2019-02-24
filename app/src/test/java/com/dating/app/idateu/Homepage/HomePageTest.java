package com.dating.app.idateu.Homepage;

import android.graphics.Bitmap;
import android.util.Base64;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class HomePageTest {

    String imageData;


    @Test
    public void onCreate() {
    }

    @Test
    public void loadImageforIndex() {
    }

    @Test
    public void onBackPressed() {
    }

    @Before
    public void setup()
        {
        File f = new File("tespPic.jpeg");
        try {
            byte[] fileContent = Files.readAllBytes(f.toPath());
            imageData = new String(Base64.encodeToString(fileContent,Base64.DEFAULT));
        }
        catch (Exception e)
            {

            }
        }

    @Test
    public void testImage()
        {
        //homePage.
        }
}