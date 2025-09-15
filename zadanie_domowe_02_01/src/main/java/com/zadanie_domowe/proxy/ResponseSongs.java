package com.zadanie_domowe.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseSongs(Map<Integer, SongEntity> songs) {
}
