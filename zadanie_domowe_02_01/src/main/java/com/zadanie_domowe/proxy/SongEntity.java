package com.zadanie_domowe.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongEntity(String song, String artist) {
}
