package com.blinov;

import com.blinov.entity.Instagram;
import com.blinov.service.InstagramGetFollowers;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        Instagram instagram = new Instagram("AiJacoby","9zRmmK8EAB");
        InstagramGetFollowers service = new InstagramGetFollowers(instagram);
        List<String> followersIds = service.getFollowersId("4663052");
        for (String id : followersIds) {
            print(id);
        }
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
