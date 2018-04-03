package com.assignment.utils;

public class Position {
    private BattlePoint from;
    private BattlePoint to;

    /**
     * Instantiates a new Position.
     *
     * @param from the from
     * @param to   the to
     */
    public Position(BattlePoint from, BattlePoint to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Gets from.
     *
     * @return the from
     */
    public BattlePoint getFrom() {
        return from;
    }

    /**
     * Gets to.
     *
     * @return the to
     */
    public BattlePoint getTo() {
        return to;
    }
}