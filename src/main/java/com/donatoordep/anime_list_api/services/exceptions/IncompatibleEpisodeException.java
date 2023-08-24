package com.donatoordep.anime_list_api.services.exceptions;

public class IncompatibleEpisodeException extends RuntimeException {

    private static final String ERROR = "the chosen episode exceeds the number of valid episodes";

    public IncompatibleEpisodeException() {
        super(ERROR);
    }
}
