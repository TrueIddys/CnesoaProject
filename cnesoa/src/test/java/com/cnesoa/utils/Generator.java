package com.cnesoa.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Maxime on 10/05/2016.
 */
public class Generator {

    public static String createName() {
        String name = RandomStringUtils.randomAlphabetic(ThreadLocalRandom.current().nextInt(3, 10));
        return name;
    }

    public static String createMail(){
        String firstPart = RandomStringUtils.randomAlphabetic(ThreadLocalRandom.current().nextInt(10,15));
        String secondPart = RandomStringUtils.randomAlphabetic(ThreadLocalRandom.current().nextInt(5,10));
        String finalMail = firstPart+"@"+secondPart+".com";
        return finalMail;
    }

    public static String createTel(){
        String tel = RandomStringUtils.randomNumeric(ThreadLocalRandom.current().nextInt(10));
        return tel;
    }
}
