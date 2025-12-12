package com.pot.weather.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserLocationData {
    private int id;
    private final String name;
    private final int userId;
    private final double latitude;
    private final double longitude;
}
