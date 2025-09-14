package com.zadanie_domowe.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongInfo(String song, String artist) {
}
