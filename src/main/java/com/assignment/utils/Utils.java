package com.assignment.utils;

import java.util.HashMap;
import java.util.Map;


public class Utils {

    public static Map<String, Integer> mapIndex = new HashMap<String, Integer>() {{
        put("a", 0);
        put("b", 1);
        put("c", 2);
        put("d", 3);
        put("e", 4);
        put("f", 5);
        put("g", 6);
        put("h", 7);
        put("i", 8);
        put("j", 9);
        put("k", 10);
        put("l", 11);
        put("m", 12);
    }};

    private Utils() {

    }

    public static BattlePoint getBattlePointFromString(String pos) {
        String[] position = pos.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

        int pointX = mapIndex.get((position[0]).toLowerCase());
        int pointY = Integer.parseInt(position[1]);

        if(pointY > 0) {
            pointY -= 1;
        }
        return new BattlePoint(pos, pointX, pointY);
    }


    public static boolean isPointBetween(BattlePoint point, Position position) {
        BattlePoint from = position.getFrom();
        BattlePoint to = position.getTo();

        return from.getY() <= point.getX()
                && to.getY() >= point.getX()
                && from.getX() <= point.getY()
                && to.getX() >= point.getY();
    }

}