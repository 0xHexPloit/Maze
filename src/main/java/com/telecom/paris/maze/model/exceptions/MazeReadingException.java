package com.telecom.paris.maze.model.exceptions;

import java.io.IOException;

public class MazeReadingException extends IOException {
    public MazeReadingException(String message, String fileName, int errorLine) {
        super(String.format("[%s][row: %d] Error: %s", fileName, errorLine, message));
    }
}
