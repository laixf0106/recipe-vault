package com.olive.recipevault.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Difficulty {
    Easy,
    Medium,
    Hard;

    @JsonCreator
    public static Difficulty from(String value) {
        if (value == null) return null;
        return Difficulty.valueOf(value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase());
    }
}